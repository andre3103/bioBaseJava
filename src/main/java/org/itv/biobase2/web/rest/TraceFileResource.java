package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.TraceFile;
import org.itv.biobase2.service.TraceFileService;
import org.itv.biobase2.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.itv.biobase2.domain.TraceFile}.
 */
@RestController
@RequestMapping("/api")
public class TraceFileResource {

    private final Logger log = LoggerFactory.getLogger(TraceFileResource.class);

    private static final String ENTITY_NAME = "traceFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TraceFileService traceFileService;

    public TraceFileResource(TraceFileService traceFileService) {
        this.traceFileService = traceFileService;
    }

    /**
     * {@code POST  /trace-files} : Create a new traceFile.
     *
     * @param traceFile the traceFile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new traceFile, or with status {@code 400 (Bad Request)} if the traceFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trace-files")
    public ResponseEntity<TraceFile> createTraceFile(@RequestBody TraceFile traceFile) throws URISyntaxException {
        log.debug("REST request to save TraceFile : {}", traceFile);
        if (traceFile.getId() != null) {
            throw new BadRequestAlertException("A new traceFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TraceFile result = traceFileService.save(traceFile);
        return ResponseEntity.created(new URI("/api/trace-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trace-files} : Updates an existing traceFile.
     *
     * @param traceFile the traceFile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated traceFile,
     * or with status {@code 400 (Bad Request)} if the traceFile is not valid,
     * or with status {@code 500 (Internal Server Error)} if the traceFile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trace-files")
    public ResponseEntity<TraceFile> updateTraceFile(@RequestBody TraceFile traceFile) throws URISyntaxException {
        log.debug("REST request to update TraceFile : {}", traceFile);
        if (traceFile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TraceFile result = traceFileService.save(traceFile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, traceFile.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trace-files} : get all the traceFiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of traceFiles in body.
     */
    @GetMapping("/trace-files")
    public List<TraceFile> getAllTraceFiles() {
        log.debug("REST request to get all TraceFiles");
        return traceFileService.findAll();
    }

    /**
     * {@code GET  /trace-files/:id} : get the "id" traceFile.
     *
     * @param id the id of the traceFile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the traceFile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trace-files/{id}")
    public ResponseEntity<TraceFile> getTraceFile(@PathVariable Long id) {
        log.debug("REST request to get TraceFile : {}", id);
        Optional<TraceFile> traceFile = traceFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(traceFile);
    }

    /**
     * {@code DELETE  /trace-files/:id} : delete the "id" traceFile.
     *
     * @param id the id of the traceFile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trace-files/{id}")
    public ResponseEntity<Void> deleteTraceFile(@PathVariable Long id) {
        log.debug("REST request to delete TraceFile : {}", id);
        traceFileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
