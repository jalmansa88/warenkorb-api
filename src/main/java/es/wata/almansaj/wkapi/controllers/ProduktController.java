package es.wata.almansaj.wkapi.controllers;

import java.util.Arrays;
import java.util.List;

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

import es.wata.almansaj.wkapi.model.entities.Produkt;
import es.wata.almansaj.wkapi.model.entities.Rabatt;
import es.wata.almansaj.wkapi.response.ApiResponse;
import es.wata.almansaj.wkapi.services.interfaces.ProduktService;

@RestController
@RequestMapping("/produkt")
public class ProduktController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProduktController.class);
	
	@Autowired
	ProduktService service;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> all() { 
		List<Produkt> produkte = service.getAll();
		
		LOG.info("all produkt gefunden: " + produkte);
		
		return ResponseEntity.ok(new ApiResponse(produkte, "alles gut"));
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> create(@RequestBody Produkt produkt) { 
		LOG.info("Neue Produkt : " + produkt);
		
		return ResponseEntity.ok(new ApiResponse(Arrays.asList(service.addProdukt(produkt)), "alles gut"));
	}
	
	@PostMapping("/{id}/rabatt")
	public ResponseEntity<ApiResponse> addRabatt(@PathVariable Long id, @RequestBody Rabatt rabatt) { 
		LOG.info("Adding Rabatt : " + rabatt + " to produkt id : " + id);
		
//		Rabatt rabatt = new Rabatt(rabattDto);
		
		service.addRabattToProdukt(id, rabatt);
		
		return ResponseEntity.ok(new ApiResponse("alles gut"));
	}
	
	
}
