package controle;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Usuario;

public class UsuarioControle {
	
	private HashMap<String, Usuario> usuarios;

	public UsuarioControle() {
		usuarios = new HashMap<String, Usuario>();
	}
	
	public ArrayList<String> getVideosFromIp(String ip) {
		
		if ( !usuarios.containsKey(ip) ) {
			criaUsuario(ip);
		}
		
		return usuarios.get(ip).getVideosGostados();
		
	}
	
	public void criaUsuario(String ip) {
		usuarios.put(ip, new Usuario(ip));
	}
	
	public void insereVideoGostado(String ip, String video) {
		if ( !usuarios.containsKey(ip) ) {
			criaUsuario(ip);
		}
		
		usuarios.get(ip).acrescenteVideoGostado(video);
	}
}
