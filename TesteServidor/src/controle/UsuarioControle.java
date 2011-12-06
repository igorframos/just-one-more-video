package controle;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public ArrayList<String> getVideosRecomendadosFromId(int id) {
		// TODO ISSO É UM STUB!
		if ( !usuarios.containsKey(id) ) {
			criaUsuario(id);
		}
		
		return usuarios.get(id).getVideosGostados();
		
	}
	
	public ArrayList<String> getVideosFromId(int id) {
		// TODO ISSO É UM STUB!
		if ( !usuarios.containsKey(id) ) {
			criaUsuario(id);
		}
		
		return usuarios.get(id).getVideosGostados();
		
	}
	
	public void criaUsuario(int id) {
		usuarios.put(id, new Usuario(id));
	}
	
	public void insereVideoGostado(int id, String video) {
		if ( !usuarios.containsKey(id) ) {
			criaUsuario(id);
		}
		
		usuarios.get(id).acrescenteVideoGostado(video);
	}
	
	public int geraId() {
		ultimoId++;
		return ultimoId;
	}
}
