package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.RecebimentoService;
import org.itv.biobase2.domain.Recebimento;
import org.itv.biobase2.repository.RecebimentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Recebimento}.
 */
@Service
@Transactional
public class RecebimentoServiceImpl implements RecebimentoService {

    private final Logger log = LoggerFactory.getLogger(RecebimentoServiceImpl.class);

    private final RecebimentoRepository recebimentoRepository;

    public RecebimentoServiceImpl(RecebimentoRepository recebimentoRepository) {
        this.recebimentoRepository = recebimentoRepository;
    }

    @Override
    public Recebimento save(Recebimento recebimento) {
        log.debug("Request to save Recebimento : {}", recebimento);
        return recebimentoRepository.save(recebimento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Recebimento> findAll() {
        log.debug("Request to get all Recebimentos");
        return recebimentoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Recebimento> findOne(Long id) {
        log.debug("Request to get Recebimento : {}", id);
        return recebimentoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recebimento : {}", id);
        recebimentoRepository.deleteById(id);
    }
}
