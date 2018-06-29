package es.wata.almansaj.wkapi.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import es.wata.almansaj.wkapi.exceptions.ElementNotFoundInDBException;
import es.wata.almansaj.wkapi.model.entities.Produkt;
import es.wata.almansaj.wkapi.model.entities.Rabatt;
import es.wata.almansaj.wkapi.repositories.ProdukteRepository;
import es.wata.almansaj.wkapi.repositories.RabatteRepository;
import es.wata.almansaj.wkapi.services.interfaces.ProduktService;

@Service
public class ProduktServiceImpl implements ProduktService {

	private static final Logger LOG = LoggerFactory.getLogger(ProduktServiceImpl.class);
	
	@Autowired
	private ProdukteRepository produktRepo;

	@Autowired
	private RabatteRepository rabattRepo;

	@Override
	public List<Produkt> getAll() {
		return produktRepo.findAll();
	}

	@Override
	public Produkt addProdukt(Produkt produkt) {
		return produktRepo.save(produkt);
	}

	@Override
	public void addRabattToProdukt(Long id, Rabatt rabatt) {
		
		LOG.info("addRabattToProdukt - init");
		
		Optional<Produkt> produktOpt = produktRepo.findById(id);
		
		if (!produktOpt.isPresent()) {
			throw new ElementNotFoundInDBException("Produkt with Id " + id + " does not exist");
		}
		
		Example<Rabatt> rabattExample = Example.of(rabatt);
		Optional<Rabatt> rabattOpt = rabattRepo.findOne(rabattExample);
		Rabatt rabattApplied;

		if (rabattOpt.isPresent()) {
			rabattApplied = rabattOpt.get();
			LOG.info("Rabatt " + rabatt + " already exist in the system. Assigning it to Produkt Id " + id);

		} else {
			rabattApplied = rabattRepo.save(rabatt);
			LOG.info("Adding new Rabatt " + rabatt + " to DB");
		}

		Produkt produkt = produktOpt.get();
		
		if (produkt.getRabatt() != null) {
			LOG.warn("Produkt Id " + id + " already has a Rabatt " + produkt.getRabatt() + ". Updating it to " + rabattApplied);
		}
		
		produkt.setRabatt(rabattApplied);
		
		produktRepo.save(produkt);
		
		LOG.info("addRabattToProdukt - end");
	}
	
	

}
