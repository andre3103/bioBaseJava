package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.Placa;
import org.itv.biobase2.repository.PlacaRepository;
import org.itv.biobase2.service.PlacaService;

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
 * Integration tests for the {@link PlacaResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlacaResourceIT {

    private static final LocalDate DEFAULT_DATA_MONTAGEM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_MONTAGEM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MARCADOR = "AAAAAAAAAA";
    private static final String UPDATED_MARCADOR = "BBBBBBBBBB";

    private static final String DEFAULT_SEQUENCIA = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCIA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 0;

    @Autowired
    private PlacaRepository placaRepository;

    @Autowired
    private PlacaService placaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlacaMockMvc;

    private Placa placa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Placa createEntity(EntityManager em) {
        Placa placa = new Placa()
            .dataMontagem(DEFAULT_DATA_MONTAGEM)
            .marcador(DEFAULT_MARCADOR)
            .sequencia(DEFAULT_SEQUENCIA)
            .data(DEFAULT_DATA)
            .status(DEFAULT_STATUS);
        return placa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Placa createUpdatedEntity(EntityManager em) {
        Placa placa = new Placa()
            .dataMontagem(UPDATED_DATA_MONTAGEM)
            .marcador(UPDATED_MARCADOR)
            .sequencia(UPDATED_SEQUENCIA)
            .data(UPDATED_DATA)
            .status(UPDATED_STATUS);
        return placa;
    }

    @BeforeEach
    public void initTest() {
        placa = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlaca() throws Exception {
        int databaseSizeBeforeCreate = placaRepository.findAll().size();
        // Create the Placa
        restPlacaMockMvc.perform(post("/api/placas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(placa)))
            .andExpect(status().isCreated());

        // Validate the Placa in the database
        List<Placa> placaList = placaRepository.findAll();
        assertThat(placaList).hasSize(databaseSizeBeforeCreate + 1);
        Placa testPlaca = placaList.get(placaList.size() - 1);
        assertThat(testPlaca.getDataMontagem()).isEqualTo(DEFAULT_DATA_MONTAGEM);
        assertThat(testPlaca.getMarcador()).isEqualTo(DEFAULT_MARCADOR);
        assertThat(testPlaca.getSequencia()).isEqualTo(DEFAULT_SEQUENCIA);
        assertThat(testPlaca.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testPlaca.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPlacaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = placaRepository.findAll().size();

        // Create the Placa with an existing ID
        placa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlacaMockMvc.perform(post("/api/placas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(placa)))
            .andExpect(status().isBadRequest());

        // Validate the Placa in the database
        List<Placa> placaList = placaRepository.findAll();
        assertThat(placaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlacas() throws Exception {
        // Initialize the database
        placaRepository.saveAndFlush(placa);

        // Get all the placaList
        restPlacaMockMvc.perform(get("/api/placas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(placa.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataMontagem").value(hasItem(DEFAULT_DATA_MONTAGEM.toString())))
            .andExpect(jsonPath("$.[*].marcador").value(hasItem(DEFAULT_MARCADOR)))
            .andExpect(jsonPath("$.[*].sequencia").value(hasItem(DEFAULT_SEQUENCIA)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getPlaca() throws Exception {
        // Initialize the database
        placaRepository.saveAndFlush(placa);

        // Get the placa
        restPlacaMockMvc.perform(get("/api/placas/{id}", placa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(placa.getId().intValue()))
            .andExpect(jsonPath("$.dataMontagem").value(DEFAULT_DATA_MONTAGEM.toString()))
            .andExpect(jsonPath("$.marcador").value(DEFAULT_MARCADOR))
            .andExpect(jsonPath("$.sequencia").value(DEFAULT_SEQUENCIA))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingPlaca() throws Exception {
        // Get the placa
        restPlacaMockMvc.perform(get("/api/placas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlaca() throws Exception {
        // Initialize the database
        placaService.save(placa);

        int databaseSizeBeforeUpdate = placaRepository.findAll().size();

        // Update the placa
        Placa updatedPlaca = placaRepository.findById(placa.getId()).get();
        // Disconnect from session so that the updates on updatedPlaca are not directly saved in db
        em.detach(updatedPlaca);
        updatedPlaca
            .dataMontagem(UPDATED_DATA_MONTAGEM)
            .marcador(UPDATED_MARCADOR)
            .sequencia(UPDATED_SEQUENCIA)
            .data(UPDATED_DATA)
            .status(UPDATED_STATUS);

        restPlacaMockMvc.perform(put("/api/placas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlaca)))
            .andExpect(status().isOk());

        // Validate the Placa in the database
        List<Placa> placaList = placaRepository.findAll();
        assertThat(placaList).hasSize(databaseSizeBeforeUpdate);
        Placa testPlaca = placaList.get(placaList.size() - 1);
        assertThat(testPlaca.getDataMontagem()).isEqualTo(UPDATED_DATA_MONTAGEM);
        assertThat(testPlaca.getMarcador()).isEqualTo(UPDATED_MARCADOR);
        assertThat(testPlaca.getSequencia()).isEqualTo(UPDATED_SEQUENCIA);
        assertThat(testPlaca.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testPlaca.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPlaca() throws Exception {
        int databaseSizeBeforeUpdate = placaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlacaMockMvc.perform(put("/api/placas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(placa)))
            .andExpect(status().isBadRequest());

        // Validate the Placa in the database
        List<Placa> placaList = placaRepository.findAll();
        assertThat(placaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlaca() throws Exception {
        // Initialize the database
        placaService.save(placa);

        int databaseSizeBeforeDelete = placaRepository.findAll().size();

        // Delete the placa
        restPlacaMockMvc.perform(delete("/api/placas/{id}", placa.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Placa> placaList = placaRepository.findAll();
        assertThat(placaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
