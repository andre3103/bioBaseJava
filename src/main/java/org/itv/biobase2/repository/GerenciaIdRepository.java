package org.itv.biobase2.repository;

import org.itv.biobase2.domain.GerenciaId;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GerenciaId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GerenciaIdRepository extends JpaRepository<GerenciaId, Long> {
}
