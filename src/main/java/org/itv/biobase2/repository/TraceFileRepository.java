package org.itv.biobase2.repository;

import org.itv.biobase2.domain.TraceFile;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TraceFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraceFileRepository extends JpaRepository<TraceFile, Long> {
}
