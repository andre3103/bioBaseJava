package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.GerenciaId;
import org.itv.biobase2.service.GerenciaIdService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.GerenciaId}.
 */
@RestController
@RequestMapping("/api")
public class GerenciaIdResource {

    private final Logger log = LoggerFactory.getLogger(GerenciaIdResource.class);

    private static final String ENTITY_NAME = "gerenciaId";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GerenciaIdService gerenciaIdService;

    public GerenciaIdResource(GerenciaIdService gerenciaIdService) {
        this.gerenciaIdService = gerenciaIdService;
    }

    /**
     * {@code POST  /gerencia-ids} : Create a new gerenciaId.
     *
     * @param gerenciaId the gerenciaId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gerenciaId, or with status {@code 400 (Bad Request)} if the gerenciaId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gerencia-ids")
    public ResponseEntity<GerenciaId> createGerenciaId(@RequestBody GerenciaId gerenciaId) throws URISyntaxException {
        log.debug("REST request to save GerenciaId : {}", gerenciaId);
        if (gerenciaId.getId() != null) {
            throw new BadRequestAlertException("A new gerenciaId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GerenciaId result = gerenciaIdService.save(gerenciaId);
        return ResponseEntity.created(new URI("/api/gerencia-ids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gerencia-ids} : Updates an existing gerenciaId.
     *
     * @param gerenciaId the gerenciaId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gerenciaId,
     * or with status {@code 400 (Bad Request)} if the gerenciaId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gerenciaId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gerencia-ids")
    public ResponseEntity<GerenciaId> updateGerenciaId(@RequestBody GerenciaId gerenciaId) throws URISyntaxException {
        log.debug("REST request to update GerenciaId : {}", gerenciaId);
        if (gerenciaId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GerenciaId result = gerenciaIdService.save(gerenciaId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gerenciaId.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gerencia-ids} : get all the gerenciaIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gerenciaIds in body.
     */
    @GetMapping("/gerencia-ids")
    public List<GerenciaId> getAllGerenciaIds() {
        log.debug("REST request to get all GerenciaIds");
        return gerenciaIdService.findAll();
    }

    /**
     * {@code GET  /gerencia-ids/:id} : get the "id" gerenciaId.
     *
     * @param id the id of the gerenciaId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gerenciaId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gerencia-ids/{id}")
    public ResponseEntity<GerenciaId> getGerenciaId(@PathVariable Long id) {
        log.debug("REST request to get GerenciaId : {}", id);
        Optional<GerenciaId> gerenciaId = gerenciaIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gerenciaId);
    }

    /**
     * {@code DELETE  /gerencia-ids/:id} : delete the "id" gerenciaId.
     *
     * @param id the id of the gerenciaId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gerencia-ids/{id}")
    public ResponseEntity<Void> deleteGerenciaId(@PathVariable Long id) {
        log.debug("REST request to delete GerenciaId : {}", id);
        gerenciaIdService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
