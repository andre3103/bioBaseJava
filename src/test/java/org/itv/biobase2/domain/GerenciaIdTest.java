package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class GerenciaIdTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GerenciaId.class);
        GerenciaId gerenciaId1 = new GerenciaId();
        gerenciaId1.setId(1L);
        GerenciaId gerenciaId2 = new GerenciaId();
        gerenciaId2.setId(gerenciaId1.getId());
        assertThat(gerenciaId1).isEqualTo(gerenciaId2);
        gerenciaId2.setId(2L);
        assertThat(gerenciaId1).isNotEqualTo(gerenciaId2);
        gerenciaId1.setId(null);
        assertThat(gerenciaId1).isNotEqualTo(gerenciaId2);
    }
}
