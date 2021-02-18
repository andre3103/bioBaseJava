package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.TraceFileService;
import org.itv.biobase2.domain.TraceFile;
import org.itv.biobase2.repository.TraceFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TraceFile}.
 */
@Service
@Transactional
public class TraceFileServiceImpl implements TraceFileService {

    private final Logger log = LoggerFactory.getLogger(TraceFileServiceImpl.class);

    private final TraceFileRepository traceFileRepository;

    public TraceFileServiceImpl(TraceFileRepository traceFileRepository) {
        this.traceFileRepository = traceFileRepository;
    }

    @Override
    public TraceFile save(TraceFile traceFile) {
        log.debug("Request to save TraceFile : {}", traceFile);
        return traceFileRepository.save(traceFile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TraceFile> findAll() {
        log.debug("Request to get all TraceFiles");
        return traceFileRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TraceFile> findOne(Long id) {
        log.debug("Request to get TraceFile : {}", id);
        return traceFileRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TraceFile : {}", id);
        traceFileRepository.deleteById(id);
    }
}
