package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.Barcode;
import org.itv.biobase2.service.BarcodeService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.Barcode}.
 */
@RestController
@RequestMapping("/api")
public class BarcodeResource {

    private final Logger log = LoggerFactory.getLogger(BarcodeResource.class);

    private static final String ENTITY_NAME = "barcode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BarcodeService barcodeService;

    public BarcodeResource(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    /**
     * {@code POST  /barcodes} : Create a new barcode.
     *
     * @param barcode the barcode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new barcode, or with status {@code 400 (Bad Request)} if the barcode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/barcodes")
    public ResponseEntity<Barcode> createBarcode(@RequestBody Barcode barcode) throws URISyntaxException {
        log.debug("REST request to save Barcode : {}", barcode);
        if (barcode.getId() != null) {
            throw new BadRequestAlertException("A new barcode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Barcode result = barcodeService.save(barcode);
        return ResponseEntity.created(new URI("/api/barcodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /barcodes} : Updates an existing barcode.
     *
     * @param barcode the barcode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated barcode,
     * or with status {@code 400 (Bad Request)} if the barcode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the barcode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/barcodes")
    public ResponseEntity<Barcode> updateBarcode(@RequestBody Barcode barcode) throws URISyntaxException {
        log.debug("REST request to update Barcode : {}", barcode);
        if (barcode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Barcode result = barcodeService.save(barcode);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, barcode.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /barcodes} : get all the barcodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of barcodes in body.
     */
    @GetMapping("/barcodes")
    public List<Barcode> getAllBarcodes() {
        log.debug("REST request to get all Barcodes");
        return barcodeService.findAll();
    }

    /**
     * {@code GET  /barcodes/:id} : get the "id" barcode.
     *
     * @param id the id of the barcode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the barcode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/barcodes/{id}")
    public ResponseEntity<Barcode> getBarcode(@PathVariable Long id) {
        log.debug("REST request to get Barcode : {}", id);
        Optional<Barcode> barcode = barcodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(barcode);
    }

    /**
     * {@code DELETE  /barcodes/:id} : delete the "id" barcode.
     *
     * @param id the id of the barcode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/barcodes/{id}")
    public ResponseEntity<Void> deleteBarcode(@PathVariable Long id) {
        log.debug("REST request to delete Barcode : {}", id);
        barcodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
