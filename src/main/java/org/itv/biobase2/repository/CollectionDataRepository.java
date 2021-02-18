package org.itv.biobase2.repository;

import org.itv.biobase2.domain.CollectionData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CollectionData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollectionDataRepository extends JpaRepository<CollectionData, Long> {
}
