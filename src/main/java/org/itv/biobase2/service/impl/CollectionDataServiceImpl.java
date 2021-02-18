package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.CollectionDataService;
import org.itv.biobase2.domain.CollectionData;
import org.itv.biobase2.repository.CollectionDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CollectionData}.
 */
@Service
@Transactional
public class CollectionDataServiceImpl implements CollectionDataService {

    private final Logger log = LoggerFactory.getLogger(CollectionDataServiceImpl.class);

    private final CollectionDataRepository collectionDataRepository;

    public CollectionDataServiceImpl(CollectionDataRepository collectionDataRepository) {
        this.collectionDataRepository = collectionDataRepository;
    }

    @Override
    public CollectionData save(CollectionData collectionData) {
        log.debug("Request to save CollectionData : {}", collectionData);
        return collectionDataRepository.save(collectionData);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionData> findAll() {
        log.debug("Request to get all CollectionData");
        return collectionDataRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CollectionData> findOne(Long id) {
        log.debug("Request to get CollectionData : {}", id);
        return collectionDataRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CollectionData : {}", id);
        collectionDataRepository.deleteById(id);
    }
}
