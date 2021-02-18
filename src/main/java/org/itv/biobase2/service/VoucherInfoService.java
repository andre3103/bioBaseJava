package org.itv.biobase2.service;

import org.itv.biobase2.domain.VoucherInfo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link VoucherInfo}.
 */
public interface VoucherInfoService {

    /**
     * Save a voucherInfo.
     *
     * @param voucherInfo the entity to save.
     * @return the persisted entity.
     */
    VoucherInfo save(VoucherInfo voucherInfo);

    /**
     * Get all the voucherInfos.
     *
     * @return the list of entities.
     */
    List<VoucherInfo> findAll();


    /**
     * Get the "id" voucherInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VoucherInfo> findOne(Long id);

    /**
     * Delete the "id" voucherInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
