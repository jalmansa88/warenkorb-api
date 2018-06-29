package es.wata.almansaj.wkapi.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.wata.almansaj.wkapi.model.pojo.RabattArt;

@Entity
@Table(name = "rabatte")
public class Rabatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private RabattArt art;
	private BigDecimal wert;

	public Rabatt() {
		super();
	}
	
	public Rabatt(RabattArt art, BigDecimal wert) {
		super();
		this.art = art;
		this.wert = wert;
	}
	
//	public RabattArt getArt() {
//		return RabattArt.of(art);
//	}
	
	public RabattArt getArt() {
		return art;
	}

	public BigDecimal getWert() {
		return wert;
	}

	public BigDecimal calculateFinalPrice(BigDecimal preis) {

		if (getArt() == RabattArt.ABSOLUT) {
			return preis.subtract(wert).max(BigDecimal.ZERO);

		} else {
			return preis.multiply((BigDecimal.ONE.subtract(wert.divide(BigDecimal.valueOf(100))))).max(BigDecimal.ZERO);
		}
	}

	public BigDecimal calculateDiscount(BigDecimal preis) {
		return preis.subtract(calculateFinalPrice(preis));
	}

	@Override
	public String toString() {
		return "Rabatt [id=" + id + ", art=" + getArt() + ", wert=" + wert + "]";
	}

}
