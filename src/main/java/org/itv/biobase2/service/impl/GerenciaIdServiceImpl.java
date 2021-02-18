package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.GerenciaIdService;
import org.itv.biobase2.domain.GerenciaId;
import org.itv.biobase2.repository.GerenciaIdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GerenciaId}.
 */
@Service
@Transactional
public class GerenciaIdServiceImpl implements GerenciaIdService {

    private final Logger log = LoggerFactory.getLogger(GerenciaIdServiceImpl.class);

    private final GerenciaIdRepository gerenciaIdRepository;

    public GerenciaIdServiceImpl(GerenciaIdRepository gerenciaIdRepository) {
        this.gerenciaIdRepository = gerenciaIdRepository;
    }

    @Override
    public GerenciaId save(GerenciaId gerenciaId) {
        log.debug("Request to save GerenciaId : {}", gerenciaId);
        return gerenciaIdRepository.save(gerenciaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GerenciaId> findAll() {
        log.debug("Request to get all GerenciaIds");
        return gerenciaIdRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GerenciaId> findOne(Long id) {
        log.debug("Request to get GerenciaId : {}", id);
        return gerenciaIdRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GerenciaId : {}", id);
        gerenciaIdRepository.deleteById(id);
    }
}
