package org.itv.biobase2.service;

import org.itv.biobase2.domain.Entrega;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Entrega}.
 */
public interface EntregaService {

    /**
     * Save a entrega.
     *
     * @param entrega the entity to save.
     * @return the persisted entity.
     */
    Entrega save(Entrega entrega);

    /**
     * Get all the entregas.
     *
     * @return the list of entities.
     */
    List<Entrega> findAll();


    /**
     * Get the "id" entrega.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Entrega> findOne(Long id);

    /**
     * Delete the "id" entrega.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
