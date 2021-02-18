package org.itv.biobase2.repository;

import org.itv.biobase2.domain.Sequenciamento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sequenciamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SequenciamentoRepository extends JpaRepository<Sequenciamento, Long> {
}
