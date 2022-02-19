package it.film.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.film.dao.FilmDao;
import it.film.dao.FilmDaoImp;
import it.film.entity.Film;

@RestController
@RequestMapping("/film")
@Api(value="FilmRest", tags="Gestione film")
public class FilmRest {

	Logger logger = LoggerFactory.getLogger(getClass());
	FilmDao fd = new FilmDaoImp();


	@PostMapping
	@ApiOperation(value="inserimento Film", notes="permette di inserire un film")
	public ResponseEntity<String> inserisciFilm(@RequestBody Film f) {
		try{fd.inserisciFilm(f);
		return new ResponseEntity<String>("Film inserito con successo!", HttpStatus.OK);

		}
		catch(Exception e){
			logger.error(e.getMessage());
			return new ResponseEntity<String>("film non inserito!", HttpStatus.BAD_REQUEST);

		}
	}
			@PutMapping ("/aggiorna")
	@ApiOperation(value="aggiornamento film", notes="permette di aggiornare il film")
	public ResponseEntity<String> aggiornaFilm(@RequestBody Film f, @PathVariable int id) {
		f.setId(id);
		try { fd.aggiornaFilm(f);
		return new ResponseEntity<String>("Film aggiornato!", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Film non aggiornato!", HttpStatus.BAD_REQUEST);

		}

	}

	@DeleteMapping ("/elimina")
	@ApiOperation(value="eliminazione Film", notes = "cancellazione dati film")
	public ResponseEntity<String> cancellaFilm (@PathVariable int id) {

		try { fd.cancellaFilm(id);
		return new ResponseEntity<String>("film cancellato!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("film non cancellato!", HttpStatus.BAD_REQUEST);

		}
	}
	@GetMapping ("/{id}")
	@ApiOperation(value="trova film", notes ="trova un film per id")
	public ResponseEntity<Film> trovaById(@PathVariable int id) {
		try {
			return new ResponseEntity<Film>(fd.trovaById(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Film>((Film)null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/lista")
	@ApiOperation(value="lista film", notes= "permette di visualizzare tutti i film")
	public ResponseEntity<List<Film>> getAllFilm() {
		try {
			return new ResponseEntity<List<Film>>(fd.trovaTutti(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return new ResponseEntity<List<Film>>((List<Film>)null, HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping ("/regista/{regista}")
		@ApiOperation(value="trova per regista", notes ="trova tutti i film per regista ")
		public ResponseEntity<List<Film>> trovaByRegista(@PathVariable String regista) {
			try {
				return new ResponseEntity<List<Film>>(fd.trovaByRegista(regista),HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity <List<Film>>((List<Film>)null, HttpStatus.BAD_REQUEST);
			}
	}

}
