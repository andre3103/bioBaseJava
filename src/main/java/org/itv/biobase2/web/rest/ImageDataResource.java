package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.ImageData;
import org.itv.biobase2.service.ImageDataService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.ImageData}.
 */
@RestController
@RequestMapping("/api")
public class ImageDataResource {

    private final Logger log = LoggerFactory.getLogger(ImageDataResource.class);

    private static final String ENTITY_NAME = "imageData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImageDataService imageDataService;

    public ImageDataResource(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    /**
     * {@code POST  /image-data} : Create a new imageData.
     *
     * @param imageData the imageData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageData, or with status {@code 400 (Bad Request)} if the imageData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/image-data")
    public ResponseEntity<ImageData> createImageData(@RequestBody ImageData imageData) throws URISyntaxException {
        log.debug("REST request to save ImageData : {}", imageData);
        if (imageData.getId() != null) {
            throw new BadRequestAlertException("A new imageData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageData result = imageDataService.save(imageData);
        return ResponseEntity.created(new URI("/api/image-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /image-data} : Updates an existing imageData.
     *
     * @param imageData the imageData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageData,
     * or with status {@code 400 (Bad Request)} if the imageData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the imageData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/image-data")
    public ResponseEntity<ImageData> updateImageData(@RequestBody ImageData imageData) throws URISyntaxException {
        log.debug("REST request to update ImageData : {}", imageData);
        if (imageData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageData result = imageDataService.save(imageData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /image-data} : get all the imageData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imageData in body.
     */
    @GetMapping("/image-data")
    public List<ImageData> getAllImageData() {
        log.debug("REST request to get all ImageData");
        return imageDataService.findAll();
    }

    /**
     * {@code GET  /image-data/:id} : get the "id" imageData.
     *
     * @param id the id of the imageData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/image-data/{id}")
    public ResponseEntity<ImageData> getImageData(@PathVariable Long id) {
        log.debug("REST request to get ImageData : {}", id);
        Optional<ImageData> imageData = imageDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageData);
    }

    /**
     * {@code DELETE  /image-data/:id} : delete the "id" imageData.
     *
     * @param id the id of the imageData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/image-data/{id}")
    public ResponseEntity<Void> deleteImageData(@PathVariable Long id) {
        log.debug("REST request to delete ImageData : {}", id);
        imageDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
