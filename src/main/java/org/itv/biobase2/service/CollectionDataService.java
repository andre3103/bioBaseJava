package org.itv.biobase2.service;

import org.itv.biobase2.domain.CollectionData;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CollectionData}.
 */
public interface CollectionDataService {

    /**
     * Save a collectionData.
     *
     * @param collectionData the entity to save.
     * @return the persisted entity.
     */
    CollectionData save(CollectionData collectionData);

    /**
     * Get all the collectionData.
     *
     * @return the list of entities.
     */
    List<CollectionData> findAll();


    /**
     * Get the "id" collectionData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CollectionData> findOne(Long id);

    /**
     * Delete the "id" collectionData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
