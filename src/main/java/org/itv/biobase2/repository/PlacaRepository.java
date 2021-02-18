package org.itv.biobase2.repository;

import org.itv.biobase2.domain.Placa;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Placa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlacaRepository extends JpaRepository<Placa, Long> {
}
