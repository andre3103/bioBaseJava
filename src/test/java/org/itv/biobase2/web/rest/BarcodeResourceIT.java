package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.Barcode;
import org.itv.biobase2.repository.BarcodeRepository;
import org.itv.biobase2.service.BarcodeService;

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
 * Integration tests for the {@link BarcodeResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BarcodeResourceIT {

    private static final String DEFAULT_MARCADOR = "AAAAAAAAAA";
    private static final String UPDATED_MARCADOR = "BBBBBBBBBB";

    private static final String DEFAULT_SEQUENCIA = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCIA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BarcodeRepository barcodeRepository;

    @Autowired
    private BarcodeService barcodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBarcodeMockMvc;

    private Barcode barcode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Barcode createEntity(EntityManager em) {
        Barcode barcode = new Barcode()
            .marcador(DEFAULT_MARCADOR)
            .sequencia(DEFAULT_SEQUENCIA)
            .data(DEFAULT_DATA);
        return barcode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Barcode createUpdatedEntity(EntityManager em) {
        Barcode barcode = new Barcode()
            .marcador(UPDATED_MARCADOR)
            .sequencia(UPDATED_SEQUENCIA)
            .data(UPDATED_DATA);
        return barcode;
    }

    @BeforeEach
    public void initTest() {
        barcode = createEntity(em);
    }

    @Test
    @Transactional
    public void createBarcode() throws Exception {
        int databaseSizeBeforeCreate = barcodeRepository.findAll().size();
        // Create the Barcode
        restBarcodeMockMvc.perform(post("/api/barcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(barcode)))
            .andExpect(status().isCreated());

        // Validate the Barcode in the database
        List<Barcode> barcodeList = barcodeRepository.findAll();
        assertThat(barcodeList).hasSize(databaseSizeBeforeCreate + 1);
        Barcode testBarcode = barcodeList.get(barcodeList.size() - 1);
        assertThat(testBarcode.getMarcador()).isEqualTo(DEFAULT_MARCADOR);
        assertThat(testBarcode.getSequencia()).isEqualTo(DEFAULT_SEQUENCIA);
        assertThat(testBarcode.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createBarcodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = barcodeRepository.findAll().size();

        // Create the Barcode with an existing ID
        barcode.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBarcodeMockMvc.perform(post("/api/barcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(barcode)))
            .andExpect(status().isBadRequest());

        // Validate the Barcode in the database
        List<Barcode> barcodeList = barcodeRepository.findAll();
        assertThat(barcodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBarcodes() throws Exception {
        // Initialize the database
        barcodeRepository.saveAndFlush(barcode);

        // Get all the barcodeList
        restBarcodeMockMvc.perform(get("/api/barcodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(barcode.getId().intValue())))
            .andExpect(jsonPath("$.[*].marcador").value(hasItem(DEFAULT_MARCADOR)))
            .andExpect(jsonPath("$.[*].sequencia").value(hasItem(DEFAULT_SEQUENCIA)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getBarcode() throws Exception {
        // Initialize the database
        barcodeRepository.saveAndFlush(barcode);

        // Get the barcode
        restBarcodeMockMvc.perform(get("/api/barcodes/{id}", barcode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(barcode.getId().intValue()))
            .andExpect(jsonPath("$.marcador").value(DEFAULT_MARCADOR))
            .andExpect(jsonPath("$.sequencia").value(DEFAULT_SEQUENCIA))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBarcode() throws Exception {
        // Get the barcode
        restBarcodeMockMvc.perform(get("/api/barcodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBarcode() throws Exception {
        // Initialize the database
        barcodeService.save(barcode);

        int databaseSizeBeforeUpdate = barcodeRepository.findAll().size();

        // Update the barcode
        Barcode updatedBarcode = barcodeRepository.findById(barcode.getId()).get();
        // Disconnect from session so that the updates on updatedBarcode are not directly saved in db
        em.detach(updatedBarcode);
        updatedBarcode
            .marcador(UPDATED_MARCADOR)
            .sequencia(UPDATED_SEQUENCIA)
            .data(UPDATED_DATA);

        restBarcodeMockMvc.perform(put("/api/barcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBarcode)))
            .andExpect(status().isOk());

        // Validate the Barcode in the database
        List<Barcode> barcodeList = barcodeRepository.findAll();
        assertThat(barcodeList).hasSize(databaseSizeBeforeUpdate);
        Barcode testBarcode = barcodeList.get(barcodeList.size() - 1);
        assertThat(testBarcode.getMarcador()).isEqualTo(UPDATED_MARCADOR);
        assertThat(testBarcode.getSequencia()).isEqualTo(UPDATED_SEQUENCIA);
        assertThat(testBarcode.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingBarcode() throws Exception {
        int databaseSizeBeforeUpdate = barcodeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBarcodeMockMvc.perform(put("/api/barcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(barcode)))
            .andExpect(status().isBadRequest());

        // Validate the Barcode in the database
        List<Barcode> barcodeList = barcodeRepository.findAll();
        assertThat(barcodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBarcode() throws Exception {
        // Initialize the database
        barcodeService.save(barcode);

        int databaseSizeBeforeDelete = barcodeRepository.findAll().size();

        // Delete the barcode
        restBarcodeMockMvc.perform(delete("/api/barcodes/{id}", barcode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Barcode> barcodeList = barcodeRepository.findAll();
        assertThat(barcodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
