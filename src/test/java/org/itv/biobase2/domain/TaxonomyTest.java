package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class TaxonomyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Taxonomy.class);
        Taxonomy taxonomy1 = new Taxonomy();
        taxonomy1.setId(1L);
        Taxonomy taxonomy2 = new Taxonomy();
        taxonomy2.setId(taxonomy1.getId());
        assertThat(taxonomy1).isEqualTo(taxonomy2);
        taxonomy2.setId(2L);
        assertThat(taxonomy1).isNotEqualTo(taxonomy2);
        taxonomy1.setId(null);
        assertThat(taxonomy1).isNotEqualTo(taxonomy2);
    }
}
