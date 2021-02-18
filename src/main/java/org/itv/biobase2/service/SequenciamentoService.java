package org.itv.biobase2.service;

import org.itv.biobase2.domain.Sequenciamento;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Sequenciamento}.
 */
public interface SequenciamentoService {

    /**
     * Save a sequenciamento.
     *
     * @param sequenciamento the entity to save.
     * @return the persisted entity.
     */
    Sequenciamento save(Sequenciamento sequenciamento);

    /**
     * Get all the sequenciamentos.
     *
     * @return the list of entities.
     */
    List<Sequenciamento> findAll();


    /**
     * Get the "id" sequenciamento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Sequenciamento> findOne(Long id);

    /**
     * Delete the "id" sequenciamento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
