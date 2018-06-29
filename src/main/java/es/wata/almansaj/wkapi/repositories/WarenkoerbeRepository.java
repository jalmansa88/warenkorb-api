package es.wata.almansaj.wkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.wata.almansaj.wkapi.model.entities.Warenkorb;

@Repository
public interface WarenkoerbeRepository extends JpaRepository<Warenkorb, Long>{

}
