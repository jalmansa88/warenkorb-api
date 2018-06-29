package es.wata.almansaj.wkapi.controllers;

import java.text.MessageFormat;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.wata.almansaj.wkapi.model.dto.BerechnungDto;
import es.wata.almansaj.wkapi.model.entities.Rabatt;
import es.wata.almansaj.wkapi.response.ApiResponse;
import es.wata.almansaj.wkapi.services.interfaces.WarenkorbService;

@RestController
@RequestMapping("/warenkorb")
public class WarenkorbController {

	private static final Logger LOG = LoggerFactory.getLogger(WarenkorbController.class);

	private static final String ADDING_PRODUKT = "Adding produktId {0} to Warenkorb Id: {1}";
	private static final String ADDED_PRODUKT_TO_WARENKORB = "Added produktId {0} to Warenkorb Id: {1} successfuly";
	private static final String ADDED_DISCOUNT_TO_WK = "Discount added to Warenkorb Id {0}";

	@Autowired
	private WarenkorbService service;

	@GetMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody Long kundeId) {

		LOG.info("Registering Warenkorb for Kunde id: " + kundeId);

		return ResponseEntity.ok(new ApiResponse(Arrays.asList(service.register(kundeId)), "alles gut"));
	}

	@GetMapping("/{warenkorbId}/berechnung")
	public ResponseEntity<ApiResponse> berechung(@PathVariable Long warenkorbId) {

		LOG.info("Warenkorb berechnung for WK id: " + warenkorbId);

		return ResponseEntity.ok(new ApiResponse(Arrays.asList(service.berechnung(warenkorbId)), "todo ok"));
	}

	@PostMapping("/{warenkorbId}/addproduct/{produktId}")
	public ResponseEntity<ApiResponse> addProdukt(@PathVariable Long warenkorbId, @PathVariable Long produktId) {

		LOG.info(MessageFormat.format(ADDING_PRODUKT, produktId, warenkorbId));

		service.addProdukt(warenkorbId, produktId);

		return ResponseEntity
				.ok(new ApiResponse(MessageFormat.format(ADDED_PRODUKT_TO_WARENKORB, produktId, warenkorbId)));
	}

	@PostMapping("/{warenkorbId}/discount")
	public ResponseEntity<ApiResponse> addDiscount(@PathVariable Long warenkorbId, @RequestBody Rabatt rabatt) {
		
		service.addRabatt(warenkorbId, rabatt);
		
		return ResponseEntity.ok(new ApiResponse(MessageFormat.format(ADDED_DISCOUNT_TO_WK, warenkorbId)));
	}

}
