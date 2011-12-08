package controle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.TreeSet;

import modelo.Usuario;

public class UsuarioControle {
	private HashMap<Integer, Usuario> usuarios;
	private int ultimoId;

	private File arquivo;

	private static UsuarioControle instanciaUnica = null;

	private UsuarioControle() {		
		arquivo = new File("./userData.dat");
		usuarios = new HashMap<Integer, Usuario>();
		ultimoId = 0;
		
		try {
			if (arquivo.exists()) {
				FileInputStream fstream = new FileInputStream(arquivo);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String linha;
				
				linha = br.readLine();
				ultimoId = Integer.parseInt(linha);
				
				linha = br.readLine();
				int nUsers = Integer.parseInt(linha);
				
				for (int i = 0; i < nUsers; ++i) {
					linha = br.readLine();
					int id = Integer.parseInt(linha);
					
					criaUsuario(id);
					
					linha = br.readLine();
					int nVideos = Integer.parseInt(linha);
					
					for (int j = 0; j < nVideos; ++j) {
						linha = br.readLine();
						insereVideoGostado(id, linha, false);
					}
					
					linha = br.readLine();
					nVideos = Integer.parseInt(linha);
					
					for (int j = 0; j < nVideos; ++j) {
						linha = br.readLine();
						insereVideoNaoGostado(id, linha, false);
					}
				}

				in.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static UsuarioControle getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new UsuarioControle();
		}
		return instanciaUnica;
	}

	public TreeSet<String> getVideosRecomendadosFromId(int id) {
		if (!existeCliente(id)) {
			criaUsuario(id);
		}

		return usuarios.get(id).getVideosRecomendados();
	}

	public TreeSet<String> getVideosFromId(int id) {
		if (!existeCliente(id)) {
			criaUsuario(id);
		}

		return usuarios.get(id).getVideosGostados();
	}

	public void criaUsuario(int id) {
		usuarios.put(id, new Usuario(id));
		ultimoId = Math.max(ultimoId, id);
	}

	public void insereVideoNaoGostado(int id, String video, boolean grava) {
		if (!existeCliente(id)) {
			criaUsuario(id);
		}

		usuarios.get(id).acrescentaVideoNaoGostado(video);
		
		if (grava) {
			gravaDados();
		}
	}

	public void insereVideoGostado(int id, String video, boolean grava) {
		if (!existeCliente(id)) {
			criaUsuario(id);
		}

		usuarios.get(id).acrescentaVideoGostado(video);
		
		if (grava) {
			gravaDados();
		}
	}

	public int geraId() {
		ultimoId++;
		return ultimoId;
	}

	public boolean existeCliente(int id) {
		if (!usuarios.containsKey(id)) {
			return false;
		}
		return true;
	}

	public void recalculaRatings() {
		for (Integer id : usuarios.keySet()) {
			
			usuarios.get(id).setTopId(0);
			usuarios.get(id).setTopRating(-100);
			TreeSet<Integer> ids = new TreeSet<Integer>();
			ids.addAll(usuarios.keySet());

			for (Integer idOutro : ids) {
				if (idOutro != id) {
					usuarios.get(id).calculaRating(usuarios.get(idOutro));
				}
			}
		}
	}

	public void metodoDaThread() {
		System.out.println("Recalculando distâncias.");
		recalculaRatings();
	}
	
	public void gravaDados() {
		System.out.println("Gravando dados.");
		try {
			FileWriter outFile = new FileWriter(arquivo);
			PrintWriter out = new PrintWriter(outFile);
			
			out.println(ultimoId);
			out.println(usuarios.size());
			for (Integer id1 : usuarios.keySet()) {
				out.println(id1);
				out.println(usuarios.get(id1).getVideosGostados().size());
				for (String str : usuarios.get(id1).getVideosGostados()) {
					out.println(str);
				}
				out.println(usuarios.get(id1).getVideosNaoGostados().size());
				for (String str : usuarios.get(id1).getVideosNaoGostados()) {
					out.println(str);
				}
			}
			
			out.flush();
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
