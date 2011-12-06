package modelo;

import java.util.ArrayList;


public class Usuario {

	private int id;
	private ArrayList<String> videosGostados;
	
	public Usuario(int id) {
		this.id = id;
		videosGostados = new ArrayList<String>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<String> getVideosGostados() {
		return this.videosGostados;
	}
	
	public void acrescenteVideoGostado(String link) {
		videosGostados.add(link);
	}
	
}
