package org.itv.biobase2.repository;

import org.itv.biobase2.domain.ImageData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ImageData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
}
