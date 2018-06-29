package es.wata.almansaj.wkapi.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kunden")
public class Kunde {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	
	@OneToOne
	@JoinColumn(name = "gruppe_id")
	private KundenGruppe gruppe;
	
	@OneToMany
	private List<Warenkorb> warenkoerbList;

	public Kunde() {
	}

	public Kunde(String name, String email, KundenGruppe gruppe) {
		super();
		this.name = name;
		this.email = email;
		this.gruppe = gruppe;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public KundenGruppe getGruppe() {
		return gruppe;
	}

}
