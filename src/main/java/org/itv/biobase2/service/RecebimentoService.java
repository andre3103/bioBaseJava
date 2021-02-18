package org.itv.biobase2.service;

import org.itv.biobase2.domain.Recebimento;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Recebimento}.
 */
public interface RecebimentoService {

    /**
     * Save a recebimento.
     *
     * @param recebimento the entity to save.
     * @return the persisted entity.
     */
    Recebimento save(Recebimento recebimento);

    /**
     * Get all the recebimentos.
     *
     * @return the list of entities.
     */
    List<Recebimento> findAll();


    /**
     * Get the "id" recebimento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Recebimento> findOne(Long id);

    /**
     * Delete the "id" recebimento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
