package org.itv.biobase2.repository;

import org.itv.biobase2.domain.Taxonomy;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Taxonomy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxonomyRepository extends JpaRepository<Taxonomy, Long> {
}
