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
		// TODO ISSO É UM STUB!
		if ( !existeCliente(id) ) {
			criaUsuario(id);
		}
		
		return usuarios.get(id).getVideosGostados();		
	}
	
	public TreeSet<String> getVideosFromId(int id) {
		// TODO ISSO É UM STUB!
		if ( !existeCliente(id) ) {
			criaUsuario(id);
		}
		
		return usuarios.get(id).getVideosGostados();
		
	}
	
	public void criaUsuario(int id) {
		usuarios.put(id, new Usuario(id));
	}
	
	public void insereVideoGostado(int id, String video) {
		if ( !existeCliente(id) ) {
			criaUsuario(id);
		}
		
		usuarios.get(id).acrescenteVideoGostado(video);
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
}
