package it.film.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name ="film.trovabyregista",query="SELECT f FROM Film f WHERE f.regista=?1")
@NamedQuery(name ="film.trovatutti",query="SELECT f FROM Film f")

public class Film {
	
	
	//Attributi
	private int id;
	private String titolo;
	private int anno;
	private String regista;
	private String tipo;
	private String incasso;
	
// Getter

	
	@Id
	@Column
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public int getId() {return id;}
	
	@Column
	public String getTitolo() {return titolo;}
	
	@Column
	public int getAnno() {return anno;}
	
	@Column
	public String getRegista() {return regista;}
	
	@Column
	public String getIncasso() {return incasso;}
	
	@Column
	public String getTipo() {return tipo;}
	
	// Setter
	
	public void setId(int id) {this.id = id;}
	
	public void setTitolo(String titolo) {this.titolo = titolo;}
	
	public void setAnno(int anno) {this.anno = anno;}
	
	public void setRegista(String regista) {this.regista = regista;}

	public void setTipo(String tipo) {this.tipo = tipo;}
	
	public void setIncasso(String incasso) {this.incasso = incasso;}
	
	
	
	
}
