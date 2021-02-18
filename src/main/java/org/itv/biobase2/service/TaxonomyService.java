package org.itv.biobase2.service;

import org.itv.biobase2.domain.Taxonomy;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Taxonomy}.
 */
public interface TaxonomyService {

    /**
     * Save a taxonomy.
     *
     * @param taxonomy the entity to save.
     * @return the persisted entity.
     */
    Taxonomy save(Taxonomy taxonomy);

    /**
     * Get all the taxonomies.
     *
     * @return the list of entities.
     */
    List<Taxonomy> findAll();


    /**
     * Get the "id" taxonomy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Taxonomy> findOne(Long id);

    /**
     * Delete the "id" taxonomy.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
