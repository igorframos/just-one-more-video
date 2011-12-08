package controle;

import java.util.HashMap;
import java.util.TreeSet;

import modelo.Usuario;

public class UsuarioControle {
	private HashMap<Integer, Usuario> usuarios;
	private int ultimoId;
	
	private static UsuarioControle instanciaUnica = null;
	
	private UsuarioControle() {
		usuarios = new HashMap<Integer, Usuario>();
		ultimoId = 0;
	}
	
	public static UsuarioControle getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new UsuarioControle();
		}
		return instanciaUnica;
	}
	
	public TreeSet<String> getVideosRecomendadosFromId(int id) {
		if ( !existeCliente(id) ) {
			criaUsuario(id);
		}
		
		return usuarios.get(id).getVideosRecomendados();		
	}
	
	public TreeSet<String> getVideosFromId(int id) {
		if ( !existeCliente(id) ) {
			criaUsuario(id);
		}
		
		return usuarios.get(id).getVideosGostados();		
	}
	
	public void criaUsuario(int id) {
		usuarios.put(id, new Usuario(id));
	}
	
	public void insereVideoNaoGostado(int id, String video) {
		if ( !existeCliente(id) ) {
			criaUsuario(id);
		}
		
		usuarios.get(id).acrescentaVideoNaoGostado(video);
	}
	
	public void insereVideoGostado(int id, String video) {
		if ( !existeCliente(id) ) {
			criaUsuario(id);
		}
		
		boolean recalcula = usuarios.get(id).acrescenteVideoGostado(video);
		
		if(recalcula) {
			recalculaRatings(id);
		}
	}
	
	public int geraId() {
		ultimoId++;
		return ultimoId;
	}
	
	public boolean existeCliente(int id) {
		if ( !usuarios.containsKey(id) ) {
			return false;
		}
		return true;
	}
	
	public void recalculaRatings(int id) {
		TreeSet<Integer> ids = new TreeSet<Integer>();
		ids.addAll(usuarios.keySet());
		
		Usuario recalculado = usuarios.get(id);
		
		for (Integer idOutro : ids) {
			if(idOutro != id) {
				Usuario outro = usuarios.get(idOutro);
				recalculado.calculaRating(outro);
			}
		}
	}
}
