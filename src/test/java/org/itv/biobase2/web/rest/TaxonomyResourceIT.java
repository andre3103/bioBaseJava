package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.Taxonomy;
import org.itv.biobase2.repository.TaxonomyRepository;
import org.itv.biobase2.service.TaxonomyService;

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
 * Integration tests for the {@link TaxonomyResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TaxonomyResourceIT {

    private static final String DEFAULT_PHYLUM = "AAAAAAAAAA";
    private static final String UPDATED_PHYLUM = "BBBBBBBBBB";

    private static final String DEFAULT_TCLASS = "AAAAAAAAAA";
    private static final String UPDATED_TCLASS = "BBBBBBBBBB";

    private static final String DEFAULT_TORDER = "AAAAAAAAAA";
    private static final String UPDATED_TORDER = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY = "BBBBBBBBBB";

    private static final String DEFAULT_SUBFAMILY = "AAAAAAAAAA";
    private static final String UPDATED_SUBFAMILY = "BBBBBBBBBB";

    private static final String DEFAULT_GENUS = "AAAAAAAAAA";
    private static final String UPDATED_GENUS = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIE = "AAAAAAAAAA";
    private static final String UPDATED_SPECIE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIF = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIF = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER_INSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER_INSTITUTION = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFICATION_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICATION_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_NOTES = "BBBBBBBBBB";

    @Autowired
    private TaxonomyRepository taxonomyRepository;

    @Autowired
    private TaxonomyService taxonomyService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxonomyMockMvc;

    private Taxonomy taxonomy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taxonomy createEntity(EntityManager em) {
        Taxonomy taxonomy = new Taxonomy()
            .phylum(DEFAULT_PHYLUM)
            .tclass(DEFAULT_TCLASS)
            .torder(DEFAULT_TORDER)
            .family(DEFAULT_FAMILY)
            .subfamily(DEFAULT_SUBFAMILY)
            .genus(DEFAULT_GENUS)
            .specie(DEFAULT_SPECIE)
            .identif(DEFAULT_IDENTIF)
            .identifierEmail(DEFAULT_IDENTIFIER_EMAIL)
            .identifierInstitution(DEFAULT_IDENTIFIER_INSTITUTION)
            .identificationMethod(DEFAULT_IDENTIFICATION_METHOD)
            .taxonomyNotes(DEFAULT_TAXONOMY_NOTES);
        return taxonomy;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taxonomy createUpdatedEntity(EntityManager em) {
        Taxonomy taxonomy = new Taxonomy()
            .phylum(UPDATED_PHYLUM)
            .tclass(UPDATED_TCLASS)
            .torder(UPDATED_TORDER)
            .family(UPDATED_FAMILY)
            .subfamily(UPDATED_SUBFAMILY)
            .genus(UPDATED_GENUS)
            .specie(UPDATED_SPECIE)
            .identif(UPDATED_IDENTIF)
            .identifierEmail(UPDATED_IDENTIFIER_EMAIL)
            .identifierInstitution(UPDATED_IDENTIFIER_INSTITUTION)
            .identificationMethod(UPDATED_IDENTIFICATION_METHOD)
            .taxonomyNotes(UPDATED_TAXONOMY_NOTES);
        return taxonomy;
    }

    @BeforeEach
    public void initTest() {
        taxonomy = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaxonomy() throws Exception {
        int databaseSizeBeforeCreate = taxonomyRepository.findAll().size();
        // Create the Taxonomy
        restTaxonomyMockMvc.perform(post("/api/taxonomies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxonomy)))
            .andExpect(status().isCreated());

        // Validate the Taxonomy in the database
        List<Taxonomy> taxonomyList = taxonomyRepository.findAll();
        assertThat(taxonomyList).hasSize(databaseSizeBeforeCreate + 1);
        Taxonomy testTaxonomy = taxonomyList.get(taxonomyList.size() - 1);
        assertThat(testTaxonomy.getPhylum()).isEqualTo(DEFAULT_PHYLUM);
        assertThat(testTaxonomy.getTclass()).isEqualTo(DEFAULT_TCLASS);
        assertThat(testTaxonomy.getTorder()).isEqualTo(DEFAULT_TORDER);
        assertThat(testTaxonomy.getFamily()).isEqualTo(DEFAULT_FAMILY);
        assertThat(testTaxonomy.getSubfamily()).isEqualTo(DEFAULT_SUBFAMILY);
        assertThat(testTaxonomy.getGenus()).isEqualTo(DEFAULT_GENUS);
        assertThat(testTaxonomy.getSpecie()).isEqualTo(DEFAULT_SPECIE);
        assertThat(testTaxonomy.getIdentif()).isEqualTo(DEFAULT_IDENTIF);
        assertThat(testTaxonomy.getIdentifierEmail()).isEqualTo(DEFAULT_IDENTIFIER_EMAIL);
        assertThat(testTaxonomy.getIdentifierInstitution()).isEqualTo(DEFAULT_IDENTIFIER_INSTITUTION);
        assertThat(testTaxonomy.getIdentificationMethod()).isEqualTo(DEFAULT_IDENTIFICATION_METHOD);
        assertThat(testTaxonomy.getTaxonomyNotes()).isEqualTo(DEFAULT_TAXONOMY_NOTES);
    }

    @Test
    @Transactional
    public void createTaxonomyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taxonomyRepository.findAll().size();

        // Create the Taxonomy with an existing ID
        taxonomy.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxonomyMockMvc.perform(post("/api/taxonomies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxonomy)))
            .andExpect(status().isBadRequest());

        // Validate the Taxonomy in the database
        List<Taxonomy> taxonomyList = taxonomyRepository.findAll();
        assertThat(taxonomyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTaxonomies() throws Exception {
        // Initialize the database
        taxonomyRepository.saveAndFlush(taxonomy);

        // Get all the taxonomyList
        restTaxonomyMockMvc.perform(get("/api/taxonomies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxonomy.getId().intValue())))
            .andExpect(jsonPath("$.[*].phylum").value(hasItem(DEFAULT_PHYLUM)))
            .andExpect(jsonPath("$.[*].tclass").value(hasItem(DEFAULT_TCLASS)))
            .andExpect(jsonPath("$.[*].torder").value(hasItem(DEFAULT_TORDER)))
            .andExpect(jsonPath("$.[*].family").value(hasItem(DEFAULT_FAMILY)))
            .andExpect(jsonPath("$.[*].subfamily").value(hasItem(DEFAULT_SUBFAMILY)))
            .andExpect(jsonPath("$.[*].genus").value(hasItem(DEFAULT_GENUS)))
            .andExpect(jsonPath("$.[*].specie").value(hasItem(DEFAULT_SPECIE)))
            .andExpect(jsonPath("$.[*].identif").value(hasItem(DEFAULT_IDENTIF)))
            .andExpect(jsonPath("$.[*].identifierEmail").value(hasItem(DEFAULT_IDENTIFIER_EMAIL)))
            .andExpect(jsonPath("$.[*].identifierInstitution").value(hasItem(DEFAULT_IDENTIFIER_INSTITUTION)))
            .andExpect(jsonPath("$.[*].identificationMethod").value(hasItem(DEFAULT_IDENTIFICATION_METHOD)))
            .andExpect(jsonPath("$.[*].taxonomyNotes").value(hasItem(DEFAULT_TAXONOMY_NOTES)));
    }
    
    @Test
    @Transactional
    public void getTaxonomy() throws Exception {
        // Initialize the database
        taxonomyRepository.saveAndFlush(taxonomy);

        // Get the taxonomy
        restTaxonomyMockMvc.perform(get("/api/taxonomies/{id}", taxonomy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taxonomy.getId().intValue()))
            .andExpect(jsonPath("$.phylum").value(DEFAULT_PHYLUM))
            .andExpect(jsonPath("$.tclass").value(DEFAULT_TCLASS))
            .andExpect(jsonPath("$.torder").value(DEFAULT_TORDER))
            .andExpect(jsonPath("$.family").value(DEFAULT_FAMILY))
            .andExpect(jsonPath("$.subfamily").value(DEFAULT_SUBFAMILY))
            .andExpect(jsonPath("$.genus").value(DEFAULT_GENUS))
            .andExpect(jsonPath("$.specie").value(DEFAULT_SPECIE))
            .andExpect(jsonPath("$.identif").value(DEFAULT_IDENTIF))
            .andExpect(jsonPath("$.identifierEmail").value(DEFAULT_IDENTIFIER_EMAIL))
            .andExpect(jsonPath("$.identifierInstitution").value(DEFAULT_IDENTIFIER_INSTITUTION))
            .andExpect(jsonPath("$.identificationMethod").value(DEFAULT_IDENTIFICATION_METHOD))
            .andExpect(jsonPath("$.taxonomyNotes").value(DEFAULT_TAXONOMY_NOTES));
    }
    @Test
    @Transactional
    public void getNonExistingTaxonomy() throws Exception {
        // Get the taxonomy
        restTaxonomyMockMvc.perform(get("/api/taxonomies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaxonomy() throws Exception {
        // Initialize the database
        taxonomyService.save(taxonomy);

        int databaseSizeBeforeUpdate = taxonomyRepository.findAll().size();

        // Update the taxonomy
        Taxonomy updatedTaxonomy = taxonomyRepository.findById(taxonomy.getId()).get();
        // Disconnect from session so that the updates on updatedTaxonomy are not directly saved in db
        em.detach(updatedTaxonomy);
        updatedTaxonomy
            .phylum(UPDATED_PHYLUM)
            .tclass(UPDATED_TCLASS)
            .torder(UPDATED_TORDER)
            .family(UPDATED_FAMILY)
            .subfamily(UPDATED_SUBFAMILY)
            .genus(UPDATED_GENUS)
            .specie(UPDATED_SPECIE)
            .identif(UPDATED_IDENTIF)
            .identifierEmail(UPDATED_IDENTIFIER_EMAIL)
            .identifierInstitution(UPDATED_IDENTIFIER_INSTITUTION)
            .identificationMethod(UPDATED_IDENTIFICATION_METHOD)
            .taxonomyNotes(UPDATED_TAXONOMY_NOTES);

        restTaxonomyMockMvc.perform(put("/api/taxonomies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTaxonomy)))
            .andExpect(status().isOk());

        // Validate the Taxonomy in the database
        List<Taxonomy> taxonomyList = taxonomyRepository.findAll();
        assertThat(taxonomyList).hasSize(databaseSizeBeforeUpdate);
        Taxonomy testTaxonomy = taxonomyList.get(taxonomyList.size() - 1);
        assertThat(testTaxonomy.getPhylum()).isEqualTo(UPDATED_PHYLUM);
        assertThat(testTaxonomy.getTclass()).isEqualTo(UPDATED_TCLASS);
        assertThat(testTaxonomy.getTorder()).isEqualTo(UPDATED_TORDER);
        assertThat(testTaxonomy.getFamily()).isEqualTo(UPDATED_FAMILY);
        assertThat(testTaxonomy.getSubfamily()).isEqualTo(UPDATED_SUBFAMILY);
        assertThat(testTaxonomy.getGenus()).isEqualTo(UPDATED_GENUS);
        assertThat(testTaxonomy.getSpecie()).isEqualTo(UPDATED_SPECIE);
        assertThat(testTaxonomy.getIdentif()).isEqualTo(UPDATED_IDENTIF);
        assertThat(testTaxonomy.getIdentifierEmail()).isEqualTo(UPDATED_IDENTIFIER_EMAIL);
        assertThat(testTaxonomy.getIdentifierInstitution()).isEqualTo(UPDATED_IDENTIFIER_INSTITUTION);
        assertThat(testTaxonomy.getIdentificationMethod()).isEqualTo(UPDATED_IDENTIFICATION_METHOD);
        assertThat(testTaxonomy.getTaxonomyNotes()).isEqualTo(UPDATED_TAXONOMY_NOTES);
    }

    @Test
    @Transactional
    public void updateNonExistingTaxonomy() throws Exception {
        int databaseSizeBeforeUpdate = taxonomyRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxonomyMockMvc.perform(put("/api/taxonomies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxonomy)))
            .andExpect(status().isBadRequest());

        // Validate the Taxonomy in the database
        List<Taxonomy> taxonomyList = taxonomyRepository.findAll();
        assertThat(taxonomyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaxonomy() throws Exception {
        // Initialize the database
        taxonomyService.save(taxonomy);

        int databaseSizeBeforeDelete = taxonomyRepository.findAll().size();

        // Delete the taxonomy
        restTaxonomyMockMvc.perform(delete("/api/taxonomies/{id}", taxonomy.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Taxonomy> taxonomyList = taxonomyRepository.findAll();
        assertThat(taxonomyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
