package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.Taxonomy;
import org.itv.biobase2.service.TaxonomyService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.Taxonomy}.
 */
@RestController
@RequestMapping("/api")
public class TaxonomyResource {

    private final Logger log = LoggerFactory.getLogger(TaxonomyResource.class);

    private static final String ENTITY_NAME = "taxonomy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxonomyService taxonomyService;

    public TaxonomyResource(TaxonomyService taxonomyService) {
        this.taxonomyService = taxonomyService;
    }

    /**
     * {@code POST  /taxonomies} : Create a new taxonomy.
     *
     * @param taxonomy the taxonomy to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxonomy, or with status {@code 400 (Bad Request)} if the taxonomy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/taxonomies")
    public ResponseEntity<Taxonomy> createTaxonomy(@RequestBody Taxonomy taxonomy) throws URISyntaxException {
        log.debug("REST request to save Taxonomy : {}", taxonomy);
        if (taxonomy.getId() != null) {
            throw new BadRequestAlertException("A new taxonomy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Taxonomy result = taxonomyService.save(taxonomy);
        return ResponseEntity.created(new URI("/api/taxonomies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /taxonomies} : Updates an existing taxonomy.
     *
     * @param taxonomy the taxonomy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxonomy,
     * or with status {@code 400 (Bad Request)} if the taxonomy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxonomy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/taxonomies")
    public ResponseEntity<Taxonomy> updateTaxonomy(@RequestBody Taxonomy taxonomy) throws URISyntaxException {
        log.debug("REST request to update Taxonomy : {}", taxonomy);
        if (taxonomy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Taxonomy result = taxonomyService.save(taxonomy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taxonomy.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /taxonomies} : get all the taxonomies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxonomies in body.
     */
    @GetMapping("/taxonomies")
    public List<Taxonomy> getAllTaxonomies() {
        log.debug("REST request to get all Taxonomies");
        return taxonomyService.findAll();
    }

    /**
     * {@code GET  /taxonomies/:id} : get the "id" taxonomy.
     *
     * @param id the id of the taxonomy to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxonomy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/taxonomies/{id}")
    public ResponseEntity<Taxonomy> getTaxonomy(@PathVariable Long id) {
        log.debug("REST request to get Taxonomy : {}", id);
        Optional<Taxonomy> taxonomy = taxonomyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taxonomy);
    }

    /**
     * {@code DELETE  /taxonomies/:id} : delete the "id" taxonomy.
     *
     * @param id the id of the taxonomy to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/taxonomies/{id}")
    public ResponseEntity<Void> deleteTaxonomy(@PathVariable Long id) {
        log.debug("REST request to delete Taxonomy : {}", id);
        taxonomyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
