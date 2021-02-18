package org.itv.biobase2.service;

import org.itv.biobase2.domain.TraceFile;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TraceFile}.
 */
public interface TraceFileService {

    /**
     * Save a traceFile.
     *
     * @param traceFile the entity to save.
     * @return the persisted entity.
     */
    TraceFile save(TraceFile traceFile);

    /**
     * Get all the traceFiles.
     *
     * @return the list of entities.
     */
    List<TraceFile> findAll();


    /**
     * Get the "id" traceFile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TraceFile> findOne(Long id);

    /**
     * Delete the "id" traceFile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
