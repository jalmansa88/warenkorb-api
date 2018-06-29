package es.wata.almansaj.wkapi.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class KundenGruppe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToOne
	@JoinColumn(name = "rabatt_id")
	private Rabatt rabatt;

	public KundenGruppe() {

	}

}
