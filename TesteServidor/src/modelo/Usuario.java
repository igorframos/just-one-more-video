package modelo;

import java.util.TreeSet;


public class Usuario {

	private int id;
	private TreeSet<String> videosGostados;
	
	public Usuario(int id) {
		this.id = id;
		videosGostados = new TreeSet<String>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public TreeSet<String> getVideosGostados() {
		return this.videosGostados;
	}
	
	public void acrescenteVideoGostado(String link) {
		videosGostados.add(link);
	}
	
}
