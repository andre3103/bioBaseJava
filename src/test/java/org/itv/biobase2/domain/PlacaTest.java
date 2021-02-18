package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class PlacaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Placa.class);
        Placa placa1 = new Placa();
        placa1.setId(1L);
        Placa placa2 = new Placa();
        placa2.setId(placa1.getId());
        assertThat(placa1).isEqualTo(placa2);
        placa2.setId(2L);
        assertThat(placa1).isNotEqualTo(placa2);
        placa1.setId(null);
        assertThat(placa1).isNotEqualTo(placa2);
    }
}
