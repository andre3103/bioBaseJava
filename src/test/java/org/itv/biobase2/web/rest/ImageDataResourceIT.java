package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.ImageData;
import org.itv.biobase2.repository.ImageDataRepository;
import org.itv.biobase2.service.ImageDataService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ImageDataResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImageDataResourceIT {

    private static final String DEFAULT_NAME_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINAL_SPECIMEN = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINAL_SPECIMEN = "BBBBBBBBBB";

    private static final String DEFAULT_VIEW_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_VIEW_METADATA = "BBBBBBBBBB";

    private static final String DEFAULT_CAPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MEASUREMENT = "AAAAAAAAAA";
    private static final String UPDATED_MEASUREMENT = "BBBBBBBBBB";

    private static final String DEFAULT_MEASUREMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MEASUREMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PROCESS_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LICENSE_HOLDER = "AAAAAAAAAA";
    private static final String UPDATED_LICENSE_HOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_LICENSE = "BBBBBBBBBB";

    private static final String DEFAULT_LICENSE_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_LICENSE_YEAR = "BBBBBBBBBB";

    private static final String DEFAULT_LICENSE_INSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_LICENSE_INSTITUTION = "BBBBBBBBBB";

    private static final String DEFAULT_LICENSE_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_LICENSE_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTOGRAPHER = "AAAAAAAAAA";
    private static final String UPDATED_PHOTOGRAPHER = "BBBBBBBBBB";

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Autowired
    private ImageDataService imageDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImageDataMockMvc;

    private ImageData imageData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageData createEntity(EntityManager em) {
        ImageData imageData = new ImageData()
            .nameImage(DEFAULT_NAME_IMAGE)
            .imageFile(DEFAULT_IMAGE_FILE)
            .originalSpecimen(DEFAULT_ORIGINAL_SPECIMEN)
            .viewMetadata(DEFAULT_VIEW_METADATA)
            .caption(DEFAULT_CAPTION)
            .measurement(DEFAULT_MEASUREMENT)
            .measurementType(DEFAULT_MEASUREMENT_TYPE)
            .processId(DEFAULT_PROCESS_ID)
            .licenseHolder(DEFAULT_LICENSE_HOLDER)
            .license(DEFAULT_LICENSE)
            .licenseYear(DEFAULT_LICENSE_YEAR)
            .licenseInstitution(DEFAULT_LICENSE_INSTITUTION)
            .licenseContact(DEFAULT_LICENSE_CONTACT)
            .photographer(DEFAULT_PHOTOGRAPHER);
        return imageData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageData createUpdatedEntity(EntityManager em) {
        ImageData imageData = new ImageData()
            .nameImage(UPDATED_NAME_IMAGE)
            .imageFile(UPDATED_IMAGE_FILE)
            .originalSpecimen(UPDATED_ORIGINAL_SPECIMEN)
            .viewMetadata(UPDATED_VIEW_METADATA)
            .caption(UPDATED_CAPTION)
            .measurement(UPDATED_MEASUREMENT)
            .measurementType(UPDATED_MEASUREMENT_TYPE)
            .processId(UPDATED_PROCESS_ID)
            .licenseHolder(UPDATED_LICENSE_HOLDER)
            .license(UPDATED_LICENSE)
            .licenseYear(UPDATED_LICENSE_YEAR)
            .licenseInstitution(UPDATED_LICENSE_INSTITUTION)
            .licenseContact(UPDATED_LICENSE_CONTACT)
            .photographer(UPDATED_PHOTOGRAPHER);
        return imageData;
    }

    @BeforeEach
    public void initTest() {
        imageData = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageData() throws Exception {
        int databaseSizeBeforeCreate = imageDataRepository.findAll().size();
        // Create the ImageData
        restImageDataMockMvc.perform(post("/api/image-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageData)))
            .andExpect(status().isCreated());

        // Validate the ImageData in the database
        List<ImageData> imageDataList = imageDataRepository.findAll();
        assertThat(imageDataList).hasSize(databaseSizeBeforeCreate + 1);
        ImageData testImageData = imageDataList.get(imageDataList.size() - 1);
        assertThat(testImageData.getNameImage()).isEqualTo(DEFAULT_NAME_IMAGE);
        assertThat(testImageData.getImageFile()).isEqualTo(DEFAULT_IMAGE_FILE);
        assertThat(testImageData.getOriginalSpecimen()).isEqualTo(DEFAULT_ORIGINAL_SPECIMEN);
        assertThat(testImageData.getViewMetadata()).isEqualTo(DEFAULT_VIEW_METADATA);
        assertThat(testImageData.getCaption()).isEqualTo(DEFAULT_CAPTION);
        assertThat(testImageData.getMeasurement()).isEqualTo(DEFAULT_MEASUREMENT);
        assertThat(testImageData.getMeasurementType()).isEqualTo(DEFAULT_MEASUREMENT_TYPE);
        assertThat(testImageData.getProcessId()).isEqualTo(DEFAULT_PROCESS_ID);
        assertThat(testImageData.getLicenseHolder()).isEqualTo(DEFAULT_LICENSE_HOLDER);
        assertThat(testImageData.getLicense()).isEqualTo(DEFAULT_LICENSE);
        assertThat(testImageData.getLicenseYear()).isEqualTo(DEFAULT_LICENSE_YEAR);
        assertThat(testImageData.getLicenseInstitution()).isEqualTo(DEFAULT_LICENSE_INSTITUTION);
        assertThat(testImageData.getLicenseContact()).isEqualTo(DEFAULT_LICENSE_CONTACT);
        assertThat(testImageData.getPhotographer()).isEqualTo(DEFAULT_PHOTOGRAPHER);
    }

    @Test
    @Transactional
    public void createImageDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageDataRepository.findAll().size();

        // Create the ImageData with an existing ID
        imageData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageDataMockMvc.perform(post("/api/image-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageData)))
            .andExpect(status().isBadRequest());

        // Validate the ImageData in the database
        List<ImageData> imageDataList = imageDataRepository.findAll();
        assertThat(imageDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImageData() throws Exception {
        // Initialize the database
        imageDataRepository.saveAndFlush(imageData);

        // Get all the imageDataList
        restImageDataMockMvc.perform(get("/api/image-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageData.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameImage").value(hasItem(DEFAULT_NAME_IMAGE)))
            .andExpect(jsonPath("$.[*].imageFile").value(hasItem(DEFAULT_IMAGE_FILE)))
            .andExpect(jsonPath("$.[*].originalSpecimen").value(hasItem(DEFAULT_ORIGINAL_SPECIMEN)))
            .andExpect(jsonPath("$.[*].viewMetadata").value(hasItem(DEFAULT_VIEW_METADATA)))
            .andExpect(jsonPath("$.[*].caption").value(hasItem(DEFAULT_CAPTION)))
            .andExpect(jsonPath("$.[*].measurement").value(hasItem(DEFAULT_MEASUREMENT)))
            .andExpect(jsonPath("$.[*].measurementType").value(hasItem(DEFAULT_MEASUREMENT_TYPE)))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID)))
            .andExpect(jsonPath("$.[*].licenseHolder").value(hasItem(DEFAULT_LICENSE_HOLDER)))
            .andExpect(jsonPath("$.[*].license").value(hasItem(DEFAULT_LICENSE)))
            .andExpect(jsonPath("$.[*].licenseYear").value(hasItem(DEFAULT_LICENSE_YEAR)))
            .andExpect(jsonPath("$.[*].licenseInstitution").value(hasItem(DEFAULT_LICENSE_INSTITUTION)))
            .andExpect(jsonPath("$.[*].licenseContact").value(hasItem(DEFAULT_LICENSE_CONTACT)))
            .andExpect(jsonPath("$.[*].photographer").value(hasItem(DEFAULT_PHOTOGRAPHER)));
    }
    
    @Test
    @Transactional
    public void getImageData() throws Exception {
        // Initialize the database
        imageDataRepository.saveAndFlush(imageData);

        // Get the imageData
        restImageDataMockMvc.perform(get("/api/image-data/{id}", imageData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(imageData.getId().intValue()))
            .andExpect(jsonPath("$.nameImage").value(DEFAULT_NAME_IMAGE))
            .andExpect(jsonPath("$.imageFile").value(DEFAULT_IMAGE_FILE))
            .andExpect(jsonPath("$.originalSpecimen").value(DEFAULT_ORIGINAL_SPECIMEN))
            .andExpect(jsonPath("$.viewMetadata").value(DEFAULT_VIEW_METADATA))
            .andExpect(jsonPath("$.caption").value(DEFAULT_CAPTION))
            .andExpect(jsonPath("$.measurement").value(DEFAULT_MEASUREMENT))
            .andExpect(jsonPath("$.measurementType").value(DEFAULT_MEASUREMENT_TYPE))
            .andExpect(jsonPath("$.processId").value(DEFAULT_PROCESS_ID))
            .andExpect(jsonPath("$.licenseHolder").value(DEFAULT_LICENSE_HOLDER))
            .andExpect(jsonPath("$.license").value(DEFAULT_LICENSE))
            .andExpect(jsonPath("$.licenseYear").value(DEFAULT_LICENSE_YEAR))
            .andExpect(jsonPath("$.licenseInstitution").value(DEFAULT_LICENSE_INSTITUTION))
            .andExpect(jsonPath("$.licenseContact").value(DEFAULT_LICENSE_CONTACT))
            .andExpect(jsonPath("$.photographer").value(DEFAULT_PHOTOGRAPHER));
    }
    @Test
    @Transactional
    public void getNonExistingImageData() throws Exception {
        // Get the imageData
        restImageDataMockMvc.perform(get("/api/image-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageData() throws Exception {
        // Initialize the database
        imageDataService.save(imageData);

        int databaseSizeBeforeUpdate = imageDataRepository.findAll().size();

        // Update the imageData
        ImageData updatedImageData = imageDataRepository.findById(imageData.getId()).get();
        // Disconnect from session so that the updates on updatedImageData are not directly saved in db
        em.detach(updatedImageData);
        updatedImageData
            .nameImage(UPDATED_NAME_IMAGE)
            .imageFile(UPDATED_IMAGE_FILE)
            .originalSpecimen(UPDATED_ORIGINAL_SPECIMEN)
            .viewMetadata(UPDATED_VIEW_METADATA)
            .caption(UPDATED_CAPTION)
            .measurement(UPDATED_MEASUREMENT)
            .measurementType(UPDATED_MEASUREMENT_TYPE)
            .processId(UPDATED_PROCESS_ID)
            .licenseHolder(UPDATED_LICENSE_HOLDER)
            .license(UPDATED_LICENSE)
            .licenseYear(UPDATED_LICENSE_YEAR)
            .licenseInstitution(UPDATED_LICENSE_INSTITUTION)
            .licenseContact(UPDATED_LICENSE_CONTACT)
            .photographer(UPDATED_PHOTOGRAPHER);

        restImageDataMockMvc.perform(put("/api/image-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedImageData)))
            .andExpect(status().isOk());

        // Validate the ImageData in the database
        List<ImageData> imageDataList = imageDataRepository.findAll();
        assertThat(imageDataList).hasSize(databaseSizeBeforeUpdate);
        ImageData testImageData = imageDataList.get(imageDataList.size() - 1);
        assertThat(testImageData.getNameImage()).isEqualTo(UPDATED_NAME_IMAGE);
        assertThat(testImageData.getImageFile()).isEqualTo(UPDATED_IMAGE_FILE);
        assertThat(testImageData.getOriginalSpecimen()).isEqualTo(UPDATED_ORIGINAL_SPECIMEN);
        assertThat(testImageData.getViewMetadata()).isEqualTo(UPDATED_VIEW_METADATA);
        assertThat(testImageData.getCaption()).isEqualTo(UPDATED_CAPTION);
        assertThat(testImageData.getMeasurement()).isEqualTo(UPDATED_MEASUREMENT);
        assertThat(testImageData.getMeasurementType()).isEqualTo(UPDATED_MEASUREMENT_TYPE);
        assertThat(testImageData.getProcessId()).isEqualTo(UPDATED_PROCESS_ID);
        assertThat(testImageData.getLicenseHolder()).isEqualTo(UPDATED_LICENSE_HOLDER);
        assertThat(testImageData.getLicense()).isEqualTo(UPDATED_LICENSE);
        assertThat(testImageData.getLicenseYear()).isEqualTo(UPDATED_LICENSE_YEAR);
        assertThat(testImageData.getLicenseInstitution()).isEqualTo(UPDATED_LICENSE_INSTITUTION);
        assertThat(testImageData.getLicenseContact()).isEqualTo(UPDATED_LICENSE_CONTACT);
        assertThat(testImageData.getPhotographer()).isEqualTo(UPDATED_PHOTOGRAPHER);
    }

    @Test
    @Transactional
    public void updateNonExistingImageData() throws Exception {
        int databaseSizeBeforeUpdate = imageDataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageDataMockMvc.perform(put("/api/image-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageData)))
            .andExpect(status().isBadRequest());

        // Validate the ImageData in the database
        List<ImageData> imageDataList = imageDataRepository.findAll();
        assertThat(imageDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageData() throws Exception {
        // Initialize the database
        imageDataService.save(imageData);

        int databaseSizeBeforeDelete = imageDataRepository.findAll().size();

        // Delete the imageData
        restImageDataMockMvc.perform(delete("/api/image-data/{id}", imageData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImageData> imageDataList = imageDataRepository.findAll();
        assertThat(imageDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
