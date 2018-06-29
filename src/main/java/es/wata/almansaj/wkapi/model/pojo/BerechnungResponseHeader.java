package es.wata.almansaj.wkapi.model.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BerechnungResponseHeader {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	@JsonProperty("Warenkorb ID")
	private Long wkId;
	private String msg = "hola";
	private LocalDateTime datum;

	public BerechnungResponseHeader(Long wkId, LocalDateTime datum) {
		super();
		this.wkId = wkId;
		this.datum = datum;
	}

	public String getDatum() {
		return datum.format(formatter);
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public Long getWkId() {
		return wkId;
	}

	public void setWkId(Long wkId) {
		this.wkId = wkId;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
}
