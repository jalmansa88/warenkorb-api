package es.wata.almansaj.wkapi.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "warenkoerbe")
public class Warenkorb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "kunde_id")
	private Kunde kunde;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "warenkorb_produkte", joinColumns = @JoinColumn(name = "warenkorb_id"), inverseJoinColumns = @JoinColumn(name = "produkt_id"))
	private List<Produkt> produkte;

	public Warenkorb() {
		super();
	}

	public Warenkorb addProdukt(Produkt produkt) {
		produkte.add(produkt);
		return this;
	}

	public Long getId() {
		return id;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public List<Produkt> getProdukte() {
		return produkte;
	}

	public BigDecimal berechnung() {
		return produkte.stream()
				.map(Produkt::getFinalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public BigDecimal totalDiscount() {
		return produkte.stream()
				.map(Produkt::getDiscountAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String toString() {
		return "Warenkorb [id=" + id + ", kunde=" + kunde + ", produkte=" + produkte + "]";
	}

}
