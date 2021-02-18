package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.Sequenciamento;
import org.itv.biobase2.service.SequenciamentoService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.Sequenciamento}.
 */
@RestController
@RequestMapping("/api")
public class SequenciamentoResource {

    private final Logger log = LoggerFactory.getLogger(SequenciamentoResource.class);

    private static final String ENTITY_NAME = "sequenciamento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SequenciamentoService sequenciamentoService;

    public SequenciamentoResource(SequenciamentoService sequenciamentoService) {
        this.sequenciamentoService = sequenciamentoService;
    }

    /**
     * {@code POST  /sequenciamentos} : Create a new sequenciamento.
     *
     * @param sequenciamento the sequenciamento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sequenciamento, or with status {@code 400 (Bad Request)} if the sequenciamento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sequenciamentos")
    public ResponseEntity<Sequenciamento> createSequenciamento(@RequestBody Sequenciamento sequenciamento) throws URISyntaxException {
        log.debug("REST request to save Sequenciamento : {}", sequenciamento);
        if (sequenciamento.getId() != null) {
            throw new BadRequestAlertException("A new sequenciamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sequenciamento result = sequenciamentoService.save(sequenciamento);
        return ResponseEntity.created(new URI("/api/sequenciamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sequenciamentos} : Updates an existing sequenciamento.
     *
     * @param sequenciamento the sequenciamento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sequenciamento,
     * or with status {@code 400 (Bad Request)} if the sequenciamento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sequenciamento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sequenciamentos")
    public ResponseEntity<Sequenciamento> updateSequenciamento(@RequestBody Sequenciamento sequenciamento) throws URISyntaxException {
        log.debug("REST request to update Sequenciamento : {}", sequenciamento);
        if (sequenciamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sequenciamento result = sequenciamentoService.save(sequenciamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sequenciamento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sequenciamentos} : get all the sequenciamentos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sequenciamentos in body.
     */
    @GetMapping("/sequenciamentos")
    public List<Sequenciamento> getAllSequenciamentos() {
        log.debug("REST request to get all Sequenciamentos");
        return sequenciamentoService.findAll();
    }

    /**
     * {@code GET  /sequenciamentos/:id} : get the "id" sequenciamento.
     *
     * @param id the id of the sequenciamento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sequenciamento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sequenciamentos/{id}")
    public ResponseEntity<Sequenciamento> getSequenciamento(@PathVariable Long id) {
        log.debug("REST request to get Sequenciamento : {}", id);
        Optional<Sequenciamento> sequenciamento = sequenciamentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sequenciamento);
    }

    /**
     * {@code DELETE  /sequenciamentos/:id} : delete the "id" sequenciamento.
     *
     * @param id the id of the sequenciamento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sequenciamentos/{id}")
    public ResponseEntity<Void> deleteSequenciamento(@PathVariable Long id) {
        log.debug("REST request to delete Sequenciamento : {}", id);
        sequenciamentoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
