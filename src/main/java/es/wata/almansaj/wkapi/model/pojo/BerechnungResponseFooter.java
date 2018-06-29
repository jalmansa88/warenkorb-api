package es.wata.almansaj.wkapi.model.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import es.wata.almansaj.wkapi.model.entities.Rabatt;

public class BerechnungResponseFooter {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private BigDecimal rabatt;
	private BigDecimal gesamt;

	public BerechnungResponseFooter() {
		super();
	}

	public BerechnungResponseFooter(BigDecimal rabatt, BigDecimal gesamt) {
		super();
		this.rabatt = rabatt;
		this.gesamt = gesamt;
	}

	public BigDecimal getRabatt() {
		return rabatt;
	}

	public void setRabatt(BigDecimal rabatt) {
		this.rabatt = rabatt;
	}

	public BigDecimal getGesamt() {
		return gesamt;
	}

	public void setGesamt(BigDecimal gesamt) {
		this.gesamt = gesamt;
	}

}
