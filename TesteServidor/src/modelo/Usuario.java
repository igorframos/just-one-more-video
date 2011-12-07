package modelo;

import java.util.HashMap;
import java.util.TreeSet;


public class Usuario {

	private int id;
	private TreeSet<String> videosGostados;
	private TreeSet<String> videosNaoGostados;
	private TreeSet<String> videosRecomendados;
	private HashMap<Integer, Double> ratings;
	
	private int topId;
	private double topRating;
	
	private int countVideos;
	
	public Usuario(int id) {
		this.id = id;
		videosGostados = new TreeSet<String>();
		videosNaoGostados = new TreeSet<String>();
		videosRecomendados = new TreeSet<String>();
		ratings = new HashMap<Integer, Double>();
		countVideos = 0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public TreeSet<String> getVideosGostados() {
		return this.videosGostados;
	}
	
	public boolean acrescenteVideoGostado(String link) {
		videosGostados.add(link);
		
		countVideos++;
		if (countVideos < 5) {
			return false;
		} else {
			countVideos = 0;
			topId = 0;
			topRating = 0.0;
			return true;
		}
	}
	
	public TreeSet<String> getVideosRecomendados() {
		return this.videosRecomendados;
	}
	
	public void acrescenteVideoRecomendado(String link) {
		videosRecomendados.add(link);		
	}
	
	public TreeSet<String> getVideosNaoGostados() {
		return this.videosNaoGostados;
	}
	
	public void acrescentaVideoNaoGostado(String link) {
		videosNaoGostados.add(link);
	}
	
	public void calculaRating(Usuario outro) {
		int idOutro = outro.getId();
		
		TreeSet<String> videosGostadosOutro = outro.getVideosGostados();
		int countGostados = 0;
		TreeSet<String> videosPossiveisRecomendados = new TreeSet<String>();
		int countDiferenca = 0;
		
		for (String gostado : videosGostadosOutro) {
			if ( videosGostados.contains(gostado) ) {
				countGostados++;
			} else {
				countDiferenca++;
				videosPossiveisRecomendados.add(gostado);
			}
		}
		
		TreeSet<String> videosNaoGostadosOutro = outro.getVideosNaoGostados();
		int countNaoGostados = 0;
		
		for (String naogostado : videosNaoGostadosOutro) {
			if ( videosNaoGostados.contains(naogostado) ) {
				countNaoGostados++;
			}
		}
		
		int totalVideos = videosGostados.size() + videosNaoGostados.size() + videosGostadosOutro.size() + videosNaoGostadosOutro.size();
		
		double rating = countGostados*0.4 + countNaoGostados*0.4 + countDiferenca*0.2;
		rating /= totalVideos;
		
		if (rating > topRating) {
			topRating = rating;
			topId = idOutro;
			escolheVideosRecomendados(videosPossiveisRecomendados);
		}
		
		ratings.put(idOutro, rating);
	}
	
	public int getMelhorRatingId() {
		return topId;
	}
	
	public void escolheVideosRecomendados(TreeSet<String> videosPossiveis) {
		videosRecomendados.clear();
		
		if(videosPossiveis.size() <= 5) {
			videosRecomendados.addAll(videosPossiveis);
		} else {
			String[] array = (String[]) videosPossiveis.toArray();
			
			for(int i = 0; i < 5; i++) {
				int aleatorio = (int) ( (Math.random() * array.length) );
				
				videosRecomendados.add( array[aleatorio] );
			}
		}
	}
}
