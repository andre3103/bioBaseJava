package org.itv.biobase2.service.impl;

import org.itv.biobase2.service.ImageDataService;
import org.itv.biobase2.domain.ImageData;
import org.itv.biobase2.repository.ImageDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ImageData}.
 */
@Service
@Transactional
public class ImageDataServiceImpl implements ImageDataService {

    private final Logger log = LoggerFactory.getLogger(ImageDataServiceImpl.class);

    private final ImageDataRepository imageDataRepository;

    public ImageDataServiceImpl(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    @Override
    public ImageData save(ImageData imageData) {
        log.debug("Request to save ImageData : {}", imageData);
        return imageDataRepository.save(imageData);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageData> findAll() {
        log.debug("Request to get all ImageData");
        return imageDataRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ImageData> findOne(Long id) {
        log.debug("Request to get ImageData : {}", id);
        return imageDataRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageData : {}", id);
        imageDataRepository.deleteById(id);
    }
}
