package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.Placa;
import org.itv.biobase2.service.PlacaService;
import org.itv.biobase2.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.itv.biobase2.domain.Placa}.
 */
@RestController
@RequestMapping("/api")
public class PlacaResource {

    private final Logger log = LoggerFactory.getLogger(PlacaResource.class);

    private static final String ENTITY_NAME = "placa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlacaService placaService;

    public PlacaResource(PlacaService placaService) {
        this.placaService = placaService;
    }

    /**
     * {@code POST  /placas} : Create a new placa.
     *
     * @param placa the placa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new placa, or with status {@code 400 (Bad Request)} if the placa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/placas")
    public ResponseEntity<Placa> createPlaca(@Valid @RequestBody Placa placa) throws URISyntaxException {
        log.debug("REST request to save Placa : {}", placa);
        if (placa.getId() != null) {
            throw new BadRequestAlertException("A new placa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Placa result = placaService.save(placa);
        return ResponseEntity.created(new URI("/api/placas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /placas} : Updates an existing placa.
     *
     * @param placa the placa to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated placa,
     * or with status {@code 400 (Bad Request)} if the placa is not valid,
     * or with status {@code 500 (Internal Server Error)} if the placa couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/placas")
    public ResponseEntity<Placa> updatePlaca(@Valid @RequestBody Placa placa) throws URISyntaxException {
        log.debug("REST request to update Placa : {}", placa);
        if (placa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Placa result = placaService.save(placa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, placa.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /placas} : get all the placas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of placas in body.
     */
    @GetMapping("/placas")
    public List<Placa> getAllPlacas() {
        log.debug("REST request to get all Placas");
        return placaService.findAll();
    }

    /**
     * {@code GET  /placas/:id} : get the "id" placa.
     *
     * @param id the id of the placa to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the placa, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/placas/{id}")
    public ResponseEntity<Placa> getPlaca(@PathVariable Long id) {
        log.debug("REST request to get Placa : {}", id);
        Optional<Placa> placa = placaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(placa);
    }

    /**
     * {@code DELETE  /placas/:id} : delete the "id" placa.
     *
     * @param id the id of the placa to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/placas/{id}")
    public ResponseEntity<Void> deletePlaca(@PathVariable Long id) {
        log.debug("REST request to delete Placa : {}", id);
        placaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
