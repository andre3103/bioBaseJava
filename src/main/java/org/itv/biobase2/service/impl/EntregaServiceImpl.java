package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.EntregaService;
import org.itv.biobase2.domain.Entrega;
import org.itv.biobase2.repository.EntregaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Entrega}.
 */
@Service
@Transactional
public class EntregaServiceImpl implements EntregaService {

    private final Logger log = LoggerFactory.getLogger(EntregaServiceImpl.class);

    private final EntregaRepository entregaRepository;

    public EntregaServiceImpl(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @Override
    public Entrega save(Entrega entrega) {
        log.debug("Request to save Entrega : {}", entrega);
        return entregaRepository.save(entrega);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entrega> findAll() {
        log.debug("Request to get all Entregas");
        return entregaRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Entrega> findOne(Long id) {
        log.debug("Request to get Entrega : {}", id);
        return entregaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Entrega : {}", id);
        entregaRepository.deleteById(id);
    }
}
