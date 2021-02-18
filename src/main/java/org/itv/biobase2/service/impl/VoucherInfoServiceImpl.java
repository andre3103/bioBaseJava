package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.VoucherInfoService;
import org.itv.biobase2.domain.VoucherInfo;
import org.itv.biobase2.repository.VoucherInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link VoucherInfo}.
 */
@Service
@Transactional
public class VoucherInfoServiceImpl implements VoucherInfoService {

    private final Logger log = LoggerFactory.getLogger(VoucherInfoServiceImpl.class);

    private final VoucherInfoRepository voucherInfoRepository;

    public VoucherInfoServiceImpl(VoucherInfoRepository voucherInfoRepository) {
        this.voucherInfoRepository = voucherInfoRepository;
    }

    @Override
    public VoucherInfo save(VoucherInfo voucherInfo) {
        log.debug("Request to save VoucherInfo : {}", voucherInfo);
        return voucherInfoRepository.save(voucherInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherInfo> findAll() {
        log.debug("Request to get all VoucherInfos");
        return voucherInfoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VoucherInfo> findOne(Long id) {
        log.debug("Request to get VoucherInfo : {}", id);
        return voucherInfoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VoucherInfo : {}", id);
        voucherInfoRepository.deleteById(id);
    }
}
