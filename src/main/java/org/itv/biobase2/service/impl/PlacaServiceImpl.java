package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.PlacaService;
import org.itv.biobase2.domain.Placa;
import org.itv.biobase2.repository.PlacaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Placa}.
 */
@Service
@Transactional
public class PlacaServiceImpl implements PlacaService {

    private final Logger log = LoggerFactory.getLogger(PlacaServiceImpl.class);

    private final PlacaRepository placaRepository;

    public PlacaServiceImpl(PlacaRepository placaRepository) {
        this.placaRepository = placaRepository;
    }

    @Override
    public Placa save(Placa placa) {
        log.debug("Request to save Placa : {}", placa);
        return placaRepository.save(placa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Placa> findAll() {
        log.debug("Request to get all Placas");
        return placaRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Placa> findOne(Long id) {
        log.debug("Request to get Placa : {}", id);
        return placaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Placa : {}", id);
        placaRepository.deleteById(id);
    }
}
