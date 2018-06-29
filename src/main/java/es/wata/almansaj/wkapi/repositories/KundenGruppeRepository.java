package es.wata.almansaj.wkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.wata.almansaj.wkapi.model.entities.KundenGruppe;

@Repository
public interface KundenGruppeRepository extends JpaRepository<KundenGruppe, Long>{

}
