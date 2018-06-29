package es.wata.almansaj.wkapi.services.delegates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import es.wata.almansaj.wkapi.model.dto.BerechnungDto;
import es.wata.almansaj.wkapi.model.entities.Produkt;
import es.wata.almansaj.wkapi.model.entities.Warenkorb;
import es.wata.almansaj.wkapi.model.pojo.BerechnungResponseFooter;
import es.wata.almansaj.wkapi.model.pojo.BerechnungResponseHeader;
import es.wata.almansaj.wkapi.model.pojo.BerechnungResponseProduktDetail;

@Component
public class WarenkorbDelegate {

	private static final Logger LOG = LoggerFactory.getLogger(WarenkorbDelegate.class);

	List<Produkt> wkProdukte = null;
	Warenkorb wk = null;

	public BerechnungDto buildBerechnungResponse(Warenkorb warenkorb) {

		wk = warenkorb;
		wkProdukte = warenkorb.getProdukte();

		BerechnungDto berechnungDto = new BerechnungDto();

		buildHeader(berechnungDto);
		buildDetail(berechnungDto);
		buildFooter(berechnungDto);

		LOG.info("Berechnung Response : " + berechnungDto);
		
		return berechnungDto;
	}

	private void buildHeader(BerechnungDto berechnungDto) {
		berechnungDto.setHeader(new BerechnungResponseHeader(wk.getId(), LocalDateTime.now()));
	}

	private void buildDetail(BerechnungDto berechnungDto) {

		Map<Produkt, Long> typeOfProductMap = wkProdukte.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		List<BerechnungResponseProduktDetail> produkteDetailList = new ArrayList<BerechnungResponseProduktDetail>();
		
		typeOfProductMap.keySet().forEach((p) -> {
					BerechnungResponseProduktDetail aProduktDetail = new BerechnungResponseProduktDetail();
					
					aProduktDetail.setAnzahl(typeOfProductMap.get(p).intValue());
					aProduktDetail.setName(p.getName());
					aProduktDetail.setPreis(p.getPreis());
					aProduktDetail.setRabatt(p.getRabatt());
					aProduktDetail.setPreisNachRabbat(p.getFinalPrice());
					
					produkteDetailList.add(aProduktDetail);
					
					LOG.info("adding produkt " + p.getName() + " details to Warenkorb Id " + wk.getId());
		});
		
		berechnungDto.setDetail(produkteDetailList);
	}

	private void buildFooter(BerechnungDto berechnungDto) {
		BerechnungResponseFooter footer = new BerechnungResponseFooter();
		footer.setGesamt(wk.berechnung());
		footer.setRabatt(wk.totalDiscount());

		berechnungDto.setFooter(footer);
	}
}
