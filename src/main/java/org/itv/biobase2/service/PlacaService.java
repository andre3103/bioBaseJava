package org.itv.biobase2.service;

import org.itv.biobase2.domain.Placa;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Placa}.
 */
public interface PlacaService {

    /**
     * Save a placa.
     *
     * @param placa the entity to save.
     * @return the persisted entity.
     */
    Placa save(Placa placa);

    /**
     * Get all the placas.
     *
     * @return the list of entities.
     */
    List<Placa> findAll();


    /**
     * Get the "id" placa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Placa> findOne(Long id);

    /**
     * Delete the "id" placa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
