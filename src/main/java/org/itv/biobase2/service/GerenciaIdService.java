package org.itv.biobase2.service;

import org.itv.biobase2.domain.GerenciaId;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link GerenciaId}.
 */
public interface GerenciaIdService {

    /**
     * Save a gerenciaId.
     *
     * @param gerenciaId the entity to save.
     * @return the persisted entity.
     */
    GerenciaId save(GerenciaId gerenciaId);

    /**
     * Get all the gerenciaIds.
     *
     * @return the list of entities.
     */
    List<GerenciaId> findAll();


    /**
     * Get the "id" gerenciaId.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GerenciaId> findOne(Long id);

    /**
     * Delete the "id" gerenciaId.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
