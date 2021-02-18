package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.CollectionData;
import org.itv.biobase2.service.CollectionDataService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.CollectionData}.
 */
@RestController
@RequestMapping("/api")
public class CollectionDataResource {

    private final Logger log = LoggerFactory.getLogger(CollectionDataResource.class);

    private static final String ENTITY_NAME = "collectionData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CollectionDataService collectionDataService;

    public CollectionDataResource(CollectionDataService collectionDataService) {
        this.collectionDataService = collectionDataService;
    }

    /**
     * {@code POST  /collection-data} : Create a new collectionData.
     *
     * @param collectionData the collectionData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new collectionData, or with status {@code 400 (Bad Request)} if the collectionData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/collection-data")
    public ResponseEntity<CollectionData> createCollectionData(@RequestBody CollectionData collectionData) throws URISyntaxException {
        log.debug("REST request to save CollectionData : {}", collectionData);
        if (collectionData.getId() != null) {
            throw new BadRequestAlertException("A new collectionData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollectionData result = collectionDataService.save(collectionData);
        return ResponseEntity.created(new URI("/api/collection-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /collection-data} : Updates an existing collectionData.
     *
     * @param collectionData the collectionData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collectionData,
     * or with status {@code 400 (Bad Request)} if the collectionData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the collectionData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/collection-data")
    public ResponseEntity<CollectionData> updateCollectionData(@RequestBody CollectionData collectionData) throws URISyntaxException {
        log.debug("REST request to update CollectionData : {}", collectionData);
        if (collectionData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CollectionData result = collectionDataService.save(collectionData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collectionData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /collection-data} : get all the collectionData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of collectionData in body.
     */
    @GetMapping("/collection-data")
    public List<CollectionData> getAllCollectionData() {
        log.debug("REST request to get all CollectionData");
        return collectionDataService.findAll();
    }

    /**
     * {@code GET  /collection-data/:id} : get the "id" collectionData.
     *
     * @param id the id of the collectionData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the collectionData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/collection-data/{id}")
    public ResponseEntity<CollectionData> getCollectionData(@PathVariable Long id) {
        log.debug("REST request to get CollectionData : {}", id);
        Optional<CollectionData> collectionData = collectionDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collectionData);
    }

    /**
     * {@code DELETE  /collection-data/:id} : delete the "id" collectionData.
     *
     * @param id the id of the collectionData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/collection-data/{id}")
    public ResponseEntity<Void> deleteCollectionData(@PathVariable Long id) {
        log.debug("REST request to delete CollectionData : {}", id);
        collectionDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
