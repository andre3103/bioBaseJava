package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.CollectionData;
import org.itv.biobase2.repository.CollectionDataRepository;
import org.itv.biobase2.service.CollectionDataService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CollectionDataResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CollectionDataResourceIT {

    private static final String DEFAULT_COLLECTOR = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTOR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_COLLECTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COLLECTION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COUNTRY_OCEAN = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OCEAN = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_EXACT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_EXACT_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIF = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIF = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_ELEVATION = "AAAAAAAAAA";
    private static final String UPDATED_ELEVATION = "BBBBBBBBBB";

    private static final String DEFAULT_DEPTH = "AAAAAAAAAA";
    private static final String UPDATED_DEPTH = "BBBBBBBBBB";

    private static final String DEFAULT_ELEVATION_PRECISION = "AAAAAAAAAA";
    private static final String UPDATED_ELEVATION_PRECISION = "BBBBBBBBBB";

    private static final String DEFAULT_DEPTH_PRECISION = "AAAAAAAAAA";
    private static final String UPDATED_DEPTH_PRECISION = "BBBBBBBBBB";

    private static final String DEFAULT_GPS_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_GPS_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_COORDINATE_ACCURACY = "AAAAAAAAAA";
    private static final String UPDATED_COORDINATE_ACCURACY = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_COLLECTION_DATE_OCCURACY = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION_DATE_OCCURACY = "BBBBBBBBBB";

    private static final String DEFAULT_HABITAT = "AAAAAAAAAA";
    private static final String UPDATED_HABITAT = "BBBBBBBBBB";

    private static final String DEFAULT_SAMPLING_PROTOCOL = "AAAAAAAAAA";
    private static final String UPDATED_SAMPLING_PROTOCOL = "BBBBBBBBBB";

    private static final String DEFAULT_COLLECTION_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SITE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COLLECTION_EVENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION_EVENT_ID = "BBBBBBBBBB";

    @Autowired
    private CollectionDataRepository collectionDataRepository;

    @Autowired
    private CollectionDataService collectionDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCollectionDataMockMvc;

    private CollectionData collectionData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionData createEntity(EntityManager em) {
        CollectionData collectionData = new CollectionData()
            .collector(DEFAULT_COLLECTOR)
            .collectionDate(DEFAULT_COLLECTION_DATE)
            .countryOcean(DEFAULT_COUNTRY_OCEAN)
            .stateProvince(DEFAULT_STATE_PROVINCE)
            .region(DEFAULT_REGION)
            .sector(DEFAULT_SECTOR)
            .exactSite(DEFAULT_EXACT_SITE)
            .identif(DEFAULT_IDENTIF)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .elevation(DEFAULT_ELEVATION)
            .depth(DEFAULT_DEPTH)
            .elevationPrecision(DEFAULT_ELEVATION_PRECISION)
            .depthPrecision(DEFAULT_DEPTH_PRECISION)
            .gpsSource(DEFAULT_GPS_SOURCE)
            .coordinateAccuracy(DEFAULT_COORDINATE_ACCURACY)
            .eventTime(DEFAULT_EVENT_TIME)
            .collectionDateOccuracy(DEFAULT_COLLECTION_DATE_OCCURACY)
            .habitat(DEFAULT_HABITAT)
            .samplingProtocol(DEFAULT_SAMPLING_PROTOCOL)
            .collectionNotes(DEFAULT_COLLECTION_NOTES)
            .siteCode(DEFAULT_SITE_CODE)
            .collectionEventId(DEFAULT_COLLECTION_EVENT_ID);
        return collectionData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionData createUpdatedEntity(EntityManager em) {
        CollectionData collectionData = new CollectionData()
            .collector(UPDATED_COLLECTOR)
            .collectionDate(UPDATED_COLLECTION_DATE)
            .countryOcean(UPDATED_COUNTRY_OCEAN)
            .stateProvince(UPDATED_STATE_PROVINCE)
            .region(UPDATED_REGION)
            .sector(UPDATED_SECTOR)
            .exactSite(UPDATED_EXACT_SITE)
            .identif(UPDATED_IDENTIF)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .elevation(UPDATED_ELEVATION)
            .depth(UPDATED_DEPTH)
            .elevationPrecision(UPDATED_ELEVATION_PRECISION)
            .depthPrecision(UPDATED_DEPTH_PRECISION)
            .gpsSource(UPDATED_GPS_SOURCE)
            .coordinateAccuracy(UPDATED_COORDINATE_ACCURACY)
            .eventTime(UPDATED_EVENT_TIME)
            .collectionDateOccuracy(UPDATED_COLLECTION_DATE_OCCURACY)
            .habitat(UPDATED_HABITAT)
            .samplingProtocol(UPDATED_SAMPLING_PROTOCOL)
            .collectionNotes(UPDATED_COLLECTION_NOTES)
            .siteCode(UPDATED_SITE_CODE)
            .collectionEventId(UPDATED_COLLECTION_EVENT_ID);
        return collectionData;
    }

    @BeforeEach
    public void initTest() {
        collectionData = createEntity(em);
    }

    @Test
    @Transactional
    public void createCollectionData() throws Exception {
        int databaseSizeBeforeCreate = collectionDataRepository.findAll().size();
        // Create the CollectionData
        restCollectionDataMockMvc.perform(post("/api/collection-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(collectionData)))
            .andExpect(status().isCreated());

        // Validate the CollectionData in the database
        List<CollectionData> collectionDataList = collectionDataRepository.findAll();
        assertThat(collectionDataList).hasSize(databaseSizeBeforeCreate + 1);
        CollectionData testCollectionData = collectionDataList.get(collectionDataList.size() - 1);
        assertThat(testCollectionData.getCollector()).isEqualTo(DEFAULT_COLLECTOR);
        assertThat(testCollectionData.getCollectionDate()).isEqualTo(DEFAULT_COLLECTION_DATE);
        assertThat(testCollectionData.getCountryOcean()).isEqualTo(DEFAULT_COUNTRY_OCEAN);
        assertThat(testCollectionData.getStateProvince()).isEqualTo(DEFAULT_STATE_PROVINCE);
        assertThat(testCollectionData.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testCollectionData.getSector()).isEqualTo(DEFAULT_SECTOR);
        assertThat(testCollectionData.getExactSite()).isEqualTo(DEFAULT_EXACT_SITE);
        assertThat(testCollectionData.getIdentif()).isEqualTo(DEFAULT_IDENTIF);
        assertThat(testCollectionData.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testCollectionData.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testCollectionData.getElevation()).isEqualTo(DEFAULT_ELEVATION);
        assertThat(testCollectionData.getDepth()).isEqualTo(DEFAULT_DEPTH);
        assertThat(testCollectionData.getElevationPrecision()).isEqualTo(DEFAULT_ELEVATION_PRECISION);
        assertThat(testCollectionData.getDepthPrecision()).isEqualTo(DEFAULT_DEPTH_PRECISION);
        assertThat(testCollectionData.getGpsSource()).isEqualTo(DEFAULT_GPS_SOURCE);
        assertThat(testCollectionData.getCoordinateAccuracy()).isEqualTo(DEFAULT_COORDINATE_ACCURACY);
        assertThat(testCollectionData.getEventTime()).isEqualTo(DEFAULT_EVENT_TIME);
        assertThat(testCollectionData.getCollectionDateOccuracy()).isEqualTo(DEFAULT_COLLECTION_DATE_OCCURACY);
        assertThat(testCollectionData.getHabitat()).isEqualTo(DEFAULT_HABITAT);
        assertThat(testCollectionData.getSamplingProtocol()).isEqualTo(DEFAULT_SAMPLING_PROTOCOL);
        assertThat(testCollectionData.getCollectionNotes()).isEqualTo(DEFAULT_COLLECTION_NOTES);
        assertThat(testCollectionData.getSiteCode()).isEqualTo(DEFAULT_SITE_CODE);
        assertThat(testCollectionData.getCollectionEventId()).isEqualTo(DEFAULT_COLLECTION_EVENT_ID);
    }

    @Test
    @Transactional
    public void createCollectionDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = collectionDataRepository.findAll().size();

        // Create the CollectionData with an existing ID
        collectionData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCollectionDataMockMvc.perform(post("/api/collection-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(collectionData)))
            .andExpect(status().isBadRequest());

        // Validate the CollectionData in the database
        List<CollectionData> collectionDataList = collectionDataRepository.findAll();
        assertThat(collectionDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCollectionData() throws Exception {
        // Initialize the database
        collectionDataRepository.saveAndFlush(collectionData);

        // Get all the collectionDataList
        restCollectionDataMockMvc.perform(get("/api/collection-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(collectionData.getId().intValue())))
            .andExpect(jsonPath("$.[*].collector").value(hasItem(DEFAULT_COLLECTOR)))
            .andExpect(jsonPath("$.[*].collectionDate").value(hasItem(DEFAULT_COLLECTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].countryOcean").value(hasItem(DEFAULT_COUNTRY_OCEAN)))
            .andExpect(jsonPath("$.[*].stateProvince").value(hasItem(DEFAULT_STATE_PROVINCE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].sector").value(hasItem(DEFAULT_SECTOR)))
            .andExpect(jsonPath("$.[*].exactSite").value(hasItem(DEFAULT_EXACT_SITE)))
            .andExpect(jsonPath("$.[*].identif").value(hasItem(DEFAULT_IDENTIF)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE)))
            .andExpect(jsonPath("$.[*].elevation").value(hasItem(DEFAULT_ELEVATION)))
            .andExpect(jsonPath("$.[*].depth").value(hasItem(DEFAULT_DEPTH)))
            .andExpect(jsonPath("$.[*].elevationPrecision").value(hasItem(DEFAULT_ELEVATION_PRECISION)))
            .andExpect(jsonPath("$.[*].depthPrecision").value(hasItem(DEFAULT_DEPTH_PRECISION)))
            .andExpect(jsonPath("$.[*].gpsSource").value(hasItem(DEFAULT_GPS_SOURCE)))
            .andExpect(jsonPath("$.[*].coordinateAccuracy").value(hasItem(DEFAULT_COORDINATE_ACCURACY)))
            .andExpect(jsonPath("$.[*].eventTime").value(hasItem(DEFAULT_EVENT_TIME)))
            .andExpect(jsonPath("$.[*].collectionDateOccuracy").value(hasItem(DEFAULT_COLLECTION_DATE_OCCURACY)))
            .andExpect(jsonPath("$.[*].habitat").value(hasItem(DEFAULT_HABITAT)))
            .andExpect(jsonPath("$.[*].samplingProtocol").value(hasItem(DEFAULT_SAMPLING_PROTOCOL)))
            .andExpect(jsonPath("$.[*].collectionNotes").value(hasItem(DEFAULT_COLLECTION_NOTES)))
            .andExpect(jsonPath("$.[*].siteCode").value(hasItem(DEFAULT_SITE_CODE)))
            .andExpect(jsonPath("$.[*].collectionEventId").value(hasItem(DEFAULT_COLLECTION_EVENT_ID)));
    }
    
    @Test
    @Transactional
    public void getCollectionData() throws Exception {
        // Initialize the database
        collectionDataRepository.saveAndFlush(collectionData);

        // Get the collectionData
        restCollectionDataMockMvc.perform(get("/api/collection-data/{id}", collectionData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(collectionData.getId().intValue()))
            .andExpect(jsonPath("$.collector").value(DEFAULT_COLLECTOR))
            .andExpect(jsonPath("$.collectionDate").value(DEFAULT_COLLECTION_DATE.toString()))
            .andExpect(jsonPath("$.countryOcean").value(DEFAULT_COUNTRY_OCEAN))
            .andExpect(jsonPath("$.stateProvince").value(DEFAULT_STATE_PROVINCE))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.sector").value(DEFAULT_SECTOR))
            .andExpect(jsonPath("$.exactSite").value(DEFAULT_EXACT_SITE))
            .andExpect(jsonPath("$.identif").value(DEFAULT_IDENTIF))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE))
            .andExpect(jsonPath("$.elevation").value(DEFAULT_ELEVATION))
            .andExpect(jsonPath("$.depth").value(DEFAULT_DEPTH))
            .andExpect(jsonPath("$.elevationPrecision").value(DEFAULT_ELEVATION_PRECISION))
            .andExpect(jsonPath("$.depthPrecision").value(DEFAULT_DEPTH_PRECISION))
            .andExpect(jsonPath("$.gpsSource").value(DEFAULT_GPS_SOURCE))
            .andExpect(jsonPath("$.coordinateAccuracy").value(DEFAULT_COORDINATE_ACCURACY))
            .andExpect(jsonPath("$.eventTime").value(DEFAULT_EVENT_TIME))
            .andExpect(jsonPath("$.collectionDateOccuracy").value(DEFAULT_COLLECTION_DATE_OCCURACY))
            .andExpect(jsonPath("$.habitat").value(DEFAULT_HABITAT))
            .andExpect(jsonPath("$.samplingProtocol").value(DEFAULT_SAMPLING_PROTOCOL))
            .andExpect(jsonPath("$.collectionNotes").value(DEFAULT_COLLECTION_NOTES))
            .andExpect(jsonPath("$.siteCode").value(DEFAULT_SITE_CODE))
            .andExpect(jsonPath("$.collectionEventId").value(DEFAULT_COLLECTION_EVENT_ID));
    }
    @Test
    @Transactional
    public void getNonExistingCollectionData() throws Exception {
        // Get the collectionData
        restCollectionDataMockMvc.perform(get("/api/collection-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCollectionData() throws Exception {
        // Initialize the database
        collectionDataService.save(collectionData);

        int databaseSizeBeforeUpdate = collectionDataRepository.findAll().size();

        // Update the collectionData
        CollectionData updatedCollectionData = collectionDataRepository.findById(collectionData.getId()).get();
        // Disconnect from session so that the updates on updatedCollectionData are not directly saved in db
        em.detach(updatedCollectionData);
        updatedCollectionData
            .collector(UPDATED_COLLECTOR)
            .collectionDate(UPDATED_COLLECTION_DATE)
            .countryOcean(UPDATED_COUNTRY_OCEAN)
            .stateProvince(UPDATED_STATE_PROVINCE)
            .region(UPDATED_REGION)
            .sector(UPDATED_SECTOR)
            .exactSite(UPDATED_EXACT_SITE)
            .identif(UPDATED_IDENTIF)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .elevation(UPDATED_ELEVATION)
            .depth(UPDATED_DEPTH)
            .elevationPrecision(UPDATED_ELEVATION_PRECISION)
            .depthPrecision(UPDATED_DEPTH_PRECISION)
            .gpsSource(UPDATED_GPS_SOURCE)
            .coordinateAccuracy(UPDATED_COORDINATE_ACCURACY)
            .eventTime(UPDATED_EVENT_TIME)
            .collectionDateOccuracy(UPDATED_COLLECTION_DATE_OCCURACY)
            .habitat(UPDATED_HABITAT)
            .samplingProtocol(UPDATED_SAMPLING_PROTOCOL)
            .collectionNotes(UPDATED_COLLECTION_NOTES)
            .siteCode(UPDATED_SITE_CODE)
            .collectionEventId(UPDATED_COLLECTION_EVENT_ID);

        restCollectionDataMockMvc.perform(put("/api/collection-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCollectionData)))
            .andExpect(status().isOk());

        // Validate the CollectionData in the database
        List<CollectionData> collectionDataList = collectionDataRepository.findAll();
        assertThat(collectionDataList).hasSize(databaseSizeBeforeUpdate);
        CollectionData testCollectionData = collectionDataList.get(collectionDataList.size() - 1);
        assertThat(testCollectionData.getCollector()).isEqualTo(UPDATED_COLLECTOR);
        assertThat(testCollectionData.getCollectionDate()).isEqualTo(UPDATED_COLLECTION_DATE);
        assertThat(testCollectionData.getCountryOcean()).isEqualTo(UPDATED_COUNTRY_OCEAN);
        assertThat(testCollectionData.getStateProvince()).isEqualTo(UPDATED_STATE_PROVINCE);
        assertThat(testCollectionData.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testCollectionData.getSector()).isEqualTo(UPDATED_SECTOR);
        assertThat(testCollectionData.getExactSite()).isEqualTo(UPDATED_EXACT_SITE);
        assertThat(testCollectionData.getIdentif()).isEqualTo(UPDATED_IDENTIF);
        assertThat(testCollectionData.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testCollectionData.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testCollectionData.getElevation()).isEqualTo(UPDATED_ELEVATION);
        assertThat(testCollectionData.getDepth()).isEqualTo(UPDATED_DEPTH);
        assertThat(testCollectionData.getElevationPrecision()).isEqualTo(UPDATED_ELEVATION_PRECISION);
        assertThat(testCollectionData.getDepthPrecision()).isEqualTo(UPDATED_DEPTH_PRECISION);
        assertThat(testCollectionData.getGpsSource()).isEqualTo(UPDATED_GPS_SOURCE);
        assertThat(testCollectionData.getCoordinateAccuracy()).isEqualTo(UPDATED_COORDINATE_ACCURACY);
        assertThat(testCollectionData.getEventTime()).isEqualTo(UPDATED_EVENT_TIME);
        assertThat(testCollectionData.getCollectionDateOccuracy()).isEqualTo(UPDATED_COLLECTION_DATE_OCCURACY);
        assertThat(testCollectionData.getHabitat()).isEqualTo(UPDATED_HABITAT);
        assertThat(testCollectionData.getSamplingProtocol()).isEqualTo(UPDATED_SAMPLING_PROTOCOL);
        assertThat(testCollectionData.getCollectionNotes()).isEqualTo(UPDATED_COLLECTION_NOTES);
        assertThat(testCollectionData.getSiteCode()).isEqualTo(UPDATED_SITE_CODE);
        assertThat(testCollectionData.getCollectionEventId()).isEqualTo(UPDATED_COLLECTION_EVENT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCollectionData() throws Exception {
        int databaseSizeBeforeUpdate = collectionDataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCollectionDataMockMvc.perform(put("/api/collection-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(collectionData)))
            .andExpect(status().isBadRequest());

        // Validate the CollectionData in the database
        List<CollectionData> collectionDataList = collectionDataRepository.findAll();
        assertThat(collectionDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCollectionData() throws Exception {
        // Initialize the database
        collectionDataService.save(collectionData);

        int databaseSizeBeforeDelete = collectionDataRepository.findAll().size();

        // Delete the collectionData
        restCollectionDataMockMvc.perform(delete("/api/collection-data/{id}", collectionData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CollectionData> collectionDataList = collectionDataRepository.findAll();
        assertThat(collectionDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
