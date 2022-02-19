package it.film.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import it.film.entity.Film;

public class FilmDaoImp implements FilmDao {
	
	
	private EntityManager  em = null;
	
	private EntityManager getEm() {
	if(em==null) {
			em =EntityManagerHelper.getEntityManager();
		} 
	return em;
	}
	
/**salva un film nella piattaforma
 * 
 * @author Valerio Semeraro
 * @return Film salvato
	
	*/
	public void inserisciFilm(Film f){
		String incassoCriptato = BCrypt.hashpw(f.getIncasso(), BCrypt.gensalt());
		f.setIncasso(incassoCriptato);
		getEm().getTransaction().begin();
		getEm().merge(f);
		getEm().getTransaction().commit();
	}
	
	/**aggiorna un film nella piattaforma
	 * 
	 * @author Valerio Semeraro
	 * @return Film aggiornato
		
		*/
	
	public void aggiornaFilm(Film f) throws Exception {
		String incassoCriptato = BCrypt.hashpw(f.getIncasso(), BCrypt.gensalt());
		f.setIncasso(incassoCriptato);
		
		Film film = getEm().find(Film.class, f.getId());
		if(film==null) {
			throw new Exception ("Film non trovato");
			
			} 
			getEm().getTransaction().begin();
			getEm().merge(f);
			getEm().getTransaction().commit();

	}

	/**cancella un film nella piattaforma
	 * 
	 * @author Valerio Semeraro
	 * @return Film aggiornato
		
		*/
	public void cancellaFilm(int id) {
		try {
			Film f = trovaById(id);
			getEm().getTransaction().begin();
			getEm().remove(f);
		}catch ( Exception e) {
			getEm().getTransaction().rollback();
		}
		finally {
			getEm().getTransaction().commit();
		}

	}

	/**Trova un film da id
	 * 
	 * @author Valerio Semeraro
	 * @return Film by id
		
		*/
	public Film trovaById(int id) {
		
		return getEm().find(Film.class, id);
	}
	/**trova tutti i film nella piattaforma
	 * 
	 * @author Valerio Semeraro
	 * @return tutti i film nella piattaforma
		
		*/
	@SuppressWarnings("unchecked")
	public List<Film> trovaTutti() {
		
		return getEm().createNamedQuery("film.trovatutti").getResultList();
	}
	/**cancella un film nella piattaforma
	 * 
	 * @author Valerio Semeraro
	 * @return Lista di film in base al regista
		
		*/

	@SuppressWarnings("unchecked")
	public List <Film> trovaByRegista(String regista) {
		Query q= getEm().createNamedQuery("film.trovabyregista").setParameter(1,regista);
		return q.getResultList();

	}

}
