package es.wata.almansaj.wkapi.model.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import es.wata.almansaj.wkapi.model.entities.Rabatt;

public class BerechnungResponseProduktDetail {

	private int anzahl;
	private String name;
	private BigDecimal preis;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Rabatt rabatt;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private BigDecimal preisNachRabbat;

	public BerechnungResponseProduktDetail() {
		super();
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public Rabatt getRabatt() {
		return rabatt;
	}

	public void setRabatt(Rabatt rabatt) {
		this.rabatt = rabatt;
	}

	public BigDecimal getPreisNachRabbat() {
		return preisNachRabbat;
	}

	public void setPreisNachRabbat(BigDecimal preisNachRabbat) {
		this.preisNachRabbat = preisNachRabbat;
	}

}
