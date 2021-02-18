package org.itv.biobase2.service;

import org.itv.biobase2.domain.ImageData;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ImageData}.
 */
public interface ImageDataService {

    /**
     * Save a imageData.
     *
     * @param imageData the entity to save.
     * @return the persisted entity.
     */
    ImageData save(ImageData imageData);

    /**
     * Get all the imageData.
     *
     * @return the list of entities.
     */
    List<ImageData> findAll();


    /**
     * Get the "id" imageData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ImageData> findOne(Long id);

    /**
     * Delete the "id" imageData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
