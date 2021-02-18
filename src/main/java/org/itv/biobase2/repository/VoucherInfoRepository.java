package org.itv.biobase2.repository;

import org.itv.biobase2.domain.VoucherInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VoucherInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherInfoRepository extends JpaRepository<VoucherInfo, Long> {
}
