package org.itv.biobase2.web.rest;

import org.itv.biobase2.domain.VoucherInfo;
import org.itv.biobase2.service.VoucherInfoService;
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
 * REST controller for managing {@link org.itv.biobase2.domain.VoucherInfo}.
 */
@RestController
@RequestMapping("/api")
public class VoucherInfoResource {

    private final Logger log = LoggerFactory.getLogger(VoucherInfoResource.class);

    private static final String ENTITY_NAME = "voucherInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VoucherInfoService voucherInfoService;

    public VoucherInfoResource(VoucherInfoService voucherInfoService) {
        this.voucherInfoService = voucherInfoService;
    }

    /**
     * {@code POST  /voucher-infos} : Create a new voucherInfo.
     *
     * @param voucherInfo the voucherInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voucherInfo, or with status {@code 400 (Bad Request)} if the voucherInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/voucher-infos")
    public ResponseEntity<VoucherInfo> createVoucherInfo(@RequestBody VoucherInfo voucherInfo) throws URISyntaxException {
        log.debug("REST request to save VoucherInfo : {}", voucherInfo);
        if (voucherInfo.getId() != null) {
            throw new BadRequestAlertException("A new voucherInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VoucherInfo result = voucherInfoService.save(voucherInfo);
        return ResponseEntity.created(new URI("/api/voucher-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /voucher-infos} : Updates an existing voucherInfo.
     *
     * @param voucherInfo the voucherInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherInfo,
     * or with status {@code 400 (Bad Request)} if the voucherInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the voucherInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/voucher-infos")
    public ResponseEntity<VoucherInfo> updateVoucherInfo(@RequestBody VoucherInfo voucherInfo) throws URISyntaxException {
        log.debug("REST request to update VoucherInfo : {}", voucherInfo);
        if (voucherInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VoucherInfo result = voucherInfoService.save(voucherInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, voucherInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /voucher-infos} : get all the voucherInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of voucherInfos in body.
     */
    @GetMapping("/voucher-infos")
    public List<VoucherInfo> getAllVoucherInfos() {
        log.debug("REST request to get all VoucherInfos");
        return voucherInfoService.findAll();
    }

    /**
     * {@code GET  /voucher-infos/:id} : get the "id" voucherInfo.
     *
     * @param id the id of the voucherInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voucherInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/voucher-infos/{id}")
    public ResponseEntity<VoucherInfo> getVoucherInfo(@PathVariable Long id) {
        log.debug("REST request to get VoucherInfo : {}", id);
        Optional<VoucherInfo> voucherInfo = voucherInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucherInfo);
    }

    /**
     * {@code DELETE  /voucher-infos/:id} : delete the "id" voucherInfo.
     *
     * @param id the id of the voucherInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/voucher-infos/{id}")
    public ResponseEntity<Void> deleteVoucherInfo(@PathVariable Long id) {
        log.debug("REST request to delete VoucherInfo : {}", id);
        voucherInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
