package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.Recebimento;
import org.itv.biobase2.service.RecebimentoService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.Recebimento}.
 */
@RestController
@RequestMapping("/api")
public class RecebimentoResource {

    private final Logger log = LoggerFactory.getLogger(RecebimentoResource.class);

    private static final String ENTITY_NAME = "recebimento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecebimentoService recebimentoService;

    public RecebimentoResource(RecebimentoService recebimentoService) {
        this.recebimentoService = recebimentoService;
    }

    /**
     * {@code POST  /recebimentos} : Create a new recebimento.
     *
     * @param recebimento the recebimento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recebimento, or with status {@code 400 (Bad Request)} if the recebimento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recebimentos")
    public ResponseEntity<Recebimento> createRecebimento(@RequestBody Recebimento recebimento) throws URISyntaxException {
        log.debug("REST request to save Recebimento : {}", recebimento);
        if (recebimento.getId() != null) {
            throw new BadRequestAlertException("A new recebimento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Recebimento result = recebimentoService.save(recebimento);
        return ResponseEntity.created(new URI("/api/recebimentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recebimentos} : Updates an existing recebimento.
     *
     * @param recebimento the recebimento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recebimento,
     * or with status {@code 400 (Bad Request)} if the recebimento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recebimento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recebimentos")
    public ResponseEntity<Recebimento> updateRecebimento(@RequestBody Recebimento recebimento) throws URISyntaxException {
        log.debug("REST request to update Recebimento : {}", recebimento);
        if (recebimento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Recebimento result = recebimentoService.save(recebimento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recebimento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recebimentos} : get all the recebimentos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recebimentos in body.
     */
    @GetMapping("/recebimentos")
    public List<Recebimento> getAllRecebimentos() {
        log.debug("REST request to get all Recebimentos");
        return recebimentoService.findAll();
    }

    /**
     * {@code GET  /recebimentos/:id} : get the "id" recebimento.
     *
     * @param id the id of the recebimento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recebimento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recebimentos/{id}")
    public ResponseEntity<Recebimento> getRecebimento(@PathVariable Long id) {
        log.debug("REST request to get Recebimento : {}", id);
        Optional<Recebimento> recebimento = recebimentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recebimento);
    }

    /**
     * {@code DELETE  /recebimentos/:id} : delete the "id" recebimento.
     *
     * @param id the id of the recebimento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recebimentos/{id}")
    public ResponseEntity<Void> deleteRecebimento(@PathVariable Long id) {
        log.debug("REST request to delete Recebimento : {}", id);
        recebimentoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
