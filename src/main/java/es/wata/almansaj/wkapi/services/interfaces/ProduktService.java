package es.wata.almansaj.wkapi.services.interfaces;

import java.util.List;

import es.wata.almansaj.wkapi.model.entities.Produkt;
import es.wata.almansaj.wkapi.model.entities.Rabatt;

public interface ProduktService {

	public List<Produkt> getAll();
	public Produkt addProdukt(Produkt produkt);
	public void addRabattToProdukt(Long id, Rabatt rabatt);
}
