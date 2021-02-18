package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.BarcodeService;
import org.itv.biobase2.domain.Barcode;
import org.itv.biobase2.repository.BarcodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Barcode}.
 */
@Service
@Transactional
public class BarcodeServiceImpl implements BarcodeService {

    private final Logger log = LoggerFactory.getLogger(BarcodeServiceImpl.class);

    private final BarcodeRepository barcodeRepository;

    public BarcodeServiceImpl(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    @Override
    public Barcode save(Barcode barcode) {
        log.debug("Request to save Barcode : {}", barcode);
        return barcodeRepository.save(barcode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Barcode> findAll() {
        log.debug("Request to get all Barcodes");
        return barcodeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Barcode> findOne(Long id) {
        log.debug("Request to get Barcode : {}", id);
        return barcodeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Barcode : {}", id);
        barcodeRepository.deleteById(id);
    }
}
