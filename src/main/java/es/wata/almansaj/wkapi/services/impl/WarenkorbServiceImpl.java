package es.wata.almansaj.wkapi.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.wata.almansaj.wkapi.exceptions.ElementNotFoundInDBException;
import es.wata.almansaj.wkapi.model.dto.BerechnungDto;
import es.wata.almansaj.wkapi.model.entities.Produkt;
import es.wata.almansaj.wkapi.model.entities.Rabatt;
import es.wata.almansaj.wkapi.model.entities.Warenkorb;
import es.wata.almansaj.wkapi.repositories.ProdukteRepository;
import es.wata.almansaj.wkapi.repositories.WarenkoerbeRepository;
import es.wata.almansaj.wkapi.services.delegates.WarenkorbDelegate;
import es.wata.almansaj.wkapi.services.interfaces.WarenkorbService;

@Service
public class WarenkorbServiceImpl implements WarenkorbService {

	private static final Logger LOG = LoggerFactory.getLogger(WarenkorbDelegate.class);

	@Autowired
	private WarenkorbDelegate delegate;

	@Autowired
	private WarenkoerbeRepository wkRepo;

	@Autowired
	private ProdukteRepository produkteRepo;

	@Override
	public Long register(Long kundeId) {
		return null;
	}

	@Override
	public void addProdukt(Long warenkorbId, Long produktId) {

		Optional<Produkt> produktOpt = produkteRepo.findById(produktId);

		if (!produktOpt.isPresent()) {
			throw new ElementNotFoundInDBException("Product Id " + produktId + " does not exist");
		}

		Optional<Warenkorb> wkOpt = wkRepo.findById(warenkorbId);

		if (!wkOpt.isPresent()) {
			throw new ElementNotFoundInDBException("Warenkorb Id " + warenkorbId + " does not exist");
		}

		Produkt productToAdd = produktOpt.get();
		Warenkorb warenkorb = wkOpt.get();

		LOG.info("Adding " + productToAdd.getName() + " to Warenkorb Id " + warenkorb.getId());

		wkRepo.save(warenkorb.addProdukt(productToAdd));
	}

	@Override
	public BerechnungDto berechnung(Long warenkorbId) {
		Optional<Warenkorb> wkOpt = wkRepo.findById(warenkorbId);

		if (!wkOpt.isPresent()) {
			throw new ElementNotFoundInDBException("Warenkorb Id " + warenkorbId + " does not exist");
		}

		return delegate.buildBerechnungResponse(wkOpt.get());
	}

	@Override
	public void entleern(Long warenkorbId) {
		// TODO
	}

	@Override
	public void addRabatt(Long warenkorbId, Rabatt rabatt) {
		// TODO
	}

}
