package org.itv.biobase2.service;

import org.itv.biobase2.domain.Barcode;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Barcode}.
 */
public interface BarcodeService {

    /**
     * Save a barcode.
     *
     * @param barcode the entity to save.
     * @return the persisted entity.
     */
    Barcode save(Barcode barcode);

    /**
     * Get all the barcodes.
     *
     * @return the list of entities.
     */
    List<Barcode> findAll();


    /**
     * Get the "id" barcode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Barcode> findOne(Long id);

    /**
     * Delete the "id" barcode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
