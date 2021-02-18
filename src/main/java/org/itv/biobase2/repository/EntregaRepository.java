package org.itv.biobase2.repository;

import org.itv.biobase2.domain.Entrega;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Entrega entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
