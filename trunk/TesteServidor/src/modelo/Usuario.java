package modelo;

import java.util.ArrayList;


public class Usuario {

	private String ip;
	private ArrayList<String> videosGostados;
	
	public Usuario(String ip) {
		this.ip = ip;
		videosGostados = new ArrayList<String>();
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public ArrayList<String> getVideosGostados() {
		return this.videosGostados;
	}
	
	public void acrescenteVideoGostado(String link) {
		videosGostados.add(link);
	}
	
}
