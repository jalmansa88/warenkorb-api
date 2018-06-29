package es.wata.almansaj.wkapi.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "produkte")
public class Produkt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Digits(integer=5, fraction=2)
	private BigDecimal preis;

	@OneToOne
	@JoinColumn(name = "produkt_gruppe_id")
	private ProduktGruppe produktGruppe;

	@OneToOne
	@JoinColumn(name = "rabatt_id")
	private Rabatt rabatt;

	public Produkt() {
		super();
	}

	public Produkt(String name, BigDecimal preis, ProduktGruppe produktGruppe, Rabatt rabatt) {
		super();
		this.name = name;
		this.preis = preis;
		this.produktGruppe = produktGruppe;
		this.rabatt = rabatt;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public ProduktGruppe getProduktGruppe() {
		return produktGruppe;
	}

	public Rabatt getRabatt() {
		return rabatt;
	}
	
	public BigDecimal getFinalPrice() {
		return rabatt != null ? rabatt.calculateFinalPrice(preis) : preis;
	}
	
	public BigDecimal getDiscountAmount() {
		if(rabatt == null) {
			return BigDecimal.ZERO;
		}
			
		return rabatt.calculateDiscount(preis);
	}
	
	public void setRabatt(Rabatt rabatt) {
		this.rabatt = rabatt;
	}

	@Override
	public String toString() {
		return "Produkt [id=" + id + ", name=" + name + ", preis=" + preis + ", produktGruppe=" + produktGruppe
				+ ", rabatt=" + rabatt + "]";
	}

}
