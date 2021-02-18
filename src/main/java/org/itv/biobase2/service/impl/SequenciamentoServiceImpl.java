package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.SequenciamentoService;
import org.itv.biobase2.domain.Sequenciamento;
import org.itv.biobase2.repository.SequenciamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Sequenciamento}.
 */
@Service
@Transactional
public class SequenciamentoServiceImpl implements SequenciamentoService {

    private final Logger log = LoggerFactory.getLogger(SequenciamentoServiceImpl.class);

    private final SequenciamentoRepository sequenciamentoRepository;

    public SequenciamentoServiceImpl(SequenciamentoRepository sequenciamentoRepository) {
        this.sequenciamentoRepository = sequenciamentoRepository;
    }

    @Override
    public Sequenciamento save(Sequenciamento sequenciamento) {
        log.debug("Request to save Sequenciamento : {}", sequenciamento);
        return sequenciamentoRepository.save(sequenciamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sequenciamento> findAll() {
        log.debug("Request to get all Sequenciamentos");
        return sequenciamentoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Sequenciamento> findOne(Long id) {
        log.debug("Request to get Sequenciamento : {}", id);
        return sequenciamentoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sequenciamento : {}", id);
        sequenciamentoRepository.deleteById(id);
    }
}
