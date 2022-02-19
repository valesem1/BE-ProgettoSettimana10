package it.film.dao;

import java.util.List;

import it.film.entity.Film;

public interface FilmDao  {
	
		
	//Metodi
		public void inserisciFilm (Film f);
			
		public void aggiornaFilm (Film f) throws Exception;
		
		public void cancellaFilm (int id);
		
		public  Film trovaById(int id) ;
		
		public List <Film> trovaByRegista(String regista) ;
		
		public List<Film> trovaTutti();
	}		
			
	

