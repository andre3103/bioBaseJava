package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.TaxonomyService;
import org.itv.biobase2.domain.Taxonomy;
import org.itv.biobase2.repository.TaxonomyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Taxonomy}.
 */
@Service
@Transactional
public class TaxonomyServiceImpl implements TaxonomyService {

    private final Logger log = LoggerFactory.getLogger(TaxonomyServiceImpl.class);

    private final TaxonomyRepository taxonomyRepository;

    public TaxonomyServiceImpl(TaxonomyRepository taxonomyRepository) {
        this.taxonomyRepository = taxonomyRepository;
    }

    @Override
    public Taxonomy save(Taxonomy taxonomy) {
        log.debug("Request to save Taxonomy : {}", taxonomy);
        return taxonomyRepository.save(taxonomy);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Taxonomy> findAll() {
        log.debug("Request to get all Taxonomies");
        return taxonomyRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Taxonomy> findOne(Long id) {
        log.debug("Request to get Taxonomy : {}", id);
        return taxonomyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Taxonomy : {}", id);
        taxonomyRepository.deleteById(id);
    }
}
