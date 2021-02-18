package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class EntregaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Entrega.class);
        Entrega entrega1 = new Entrega();
        entrega1.setId(1L);
        Entrega entrega2 = new Entrega();
        entrega2.setId(entrega1.getId());
        assertThat(entrega1).isEqualTo(entrega2);
        entrega2.setId(2L);
        assertThat(entrega1).isNotEqualTo(entrega2);
        entrega1.setId(null);
        assertThat(entrega1).isNotEqualTo(entrega2);
    }
}
