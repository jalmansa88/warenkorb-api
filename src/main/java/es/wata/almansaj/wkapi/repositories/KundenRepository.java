package es.wata.almansaj.wkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.wata.almansaj.wkapi.model.entities.Kunde;

@Repository
public interface KundenRepository extends JpaRepository<Kunde, Long> {

}
