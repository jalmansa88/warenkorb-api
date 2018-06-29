package es.wata.almansaj.wkapi.model.dto;

import java.util.List;

import es.wata.almansaj.wkapi.model.pojo.BerechnungResponseFooter;
import es.wata.almansaj.wkapi.model.pojo.BerechnungResponseHeader;
import es.wata.almansaj.wkapi.model.pojo.BerechnungResponseProduktDetail;

public class BerechnungDto {
	
	private BerechnungResponseHeader header;
	private List<BerechnungResponseProduktDetail> detail;
	private BerechnungResponseFooter footer;
	
	public BerechnungDto() {
		super();
	}

	public BerechnungDto(BerechnungResponseHeader header, List<BerechnungResponseProduktDetail> detail,
			BerechnungResponseFooter footer) {
		super();
		this.header = header;
		this.detail = detail;
		this.footer = footer;
	}

	public BerechnungResponseHeader getHeader() {
		return header;
	}

	public void setHeader(BerechnungResponseHeader header) {
		this.header = header;
	}

	public List<BerechnungResponseProduktDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<BerechnungResponseProduktDetail> detail) {
		this.detail = detail;
	}

	public BerechnungResponseFooter getFooter() {
		return footer;
	}

	public void setFooter(BerechnungResponseFooter footer) {
		this.footer = footer;
	}

	@Override
	public String toString() {
		return "BerechnungDto [header=" + header + ", detail=" + detail + ", footer=" + footer + "]";
	}

}
