package es.wata.almansaj.wkapi.services.interfaces;

import es.wata.almansaj.wkapi.model.dto.BerechnungDto;
import es.wata.almansaj.wkapi.model.entities.Rabatt;

public interface WarenkorbService {
	
	public Long register(Long kundeId);
	public void addProdukt(Long warenkorbId, Long produktId);
	public void entleern(Long warenkorbId);
	public BerechnungDto berechnung(Long warenkorbId);
	public void addRabatt(Long warenkorbId, Rabatt rabatt);

}
