package org.itv.biobase2.repository;

import org.itv.biobase2.domain.Recebimento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Recebimento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {
}
