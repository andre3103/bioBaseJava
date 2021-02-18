package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.Entrega;
import org.itv.biobase2.service.EntregaService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.Entrega}.
 */
@RestController
@RequestMapping("/api")
public class EntregaResource {

    private final Logger log = LoggerFactory.getLogger(EntregaResource.class);

    private static final String ENTITY_NAME = "entrega";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntregaService entregaService;

    public EntregaResource(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    /**
     * {@code POST  /entregas} : Create a new entrega.
     *
     * @param entrega the entrega to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entrega, or with status {@code 400 (Bad Request)} if the entrega has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entregas")
    public ResponseEntity<Entrega> createEntrega(@RequestBody Entrega entrega) throws URISyntaxException {
        log.debug("REST request to save Entrega : {}", entrega);
        if (entrega.getId() != null) {
            throw new BadRequestAlertException("A new entrega cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Entrega result = entregaService.save(entrega);
        return ResponseEntity.created(new URI("/api/entregas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entregas} : Updates an existing entrega.
     *
     * @param entrega the entrega to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entrega,
     * or with status {@code 400 (Bad Request)} if the entrega is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entrega couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entregas")
    public ResponseEntity<Entrega> updateEntrega(@RequestBody Entrega entrega) throws URISyntaxException {
        log.debug("REST request to update Entrega : {}", entrega);
        if (entrega.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Entrega result = entregaService.save(entrega);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entrega.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entregas} : get all the entregas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entregas in body.
     */
    @GetMapping("/entregas")
    public List<Entrega> getAllEntregas() {
        log.debug("REST request to get all Entregas");
        return entregaService.findAll();
    }

    /**
     * {@code GET  /entregas/:id} : get the "id" entrega.
     *
     * @param id the id of the entrega to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entrega, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entregas/{id}")
    public ResponseEntity<Entrega> getEntrega(@PathVariable Long id) {
        log.debug("REST request to get Entrega : {}", id);
        Optional<Entrega> entrega = entregaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entrega);
    }

    /**
     * {@code DELETE  /entregas/:id} : delete the "id" entrega.
     *
     * @param id the id of the entrega to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entregas/{id}")
    public ResponseEntity<Void> deleteEntrega(@PathVariable Long id) {
        log.debug("REST request to delete Entrega : {}", id);
        entregaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
