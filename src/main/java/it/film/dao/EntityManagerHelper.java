package it.film.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
	
	
	private static EntityManagerFactory emf= null;
	
	public static EntityManager getEntityManager() {
		return getEmf().createEntityManager();
	
	}

	public static EntityManagerFactory getEmf() {
		if(emf ==null) {
			emf = Persistence.createEntityManagerFactory("progettosettimana10PS");
		}
		return emf;
	}

	
	

}
