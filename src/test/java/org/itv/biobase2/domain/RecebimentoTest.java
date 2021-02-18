package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class RecebimentoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recebimento.class);
        Recebimento recebimento1 = new Recebimento();
        recebimento1.setId(1L);
        Recebimento recebimento2 = new Recebimento();
        recebimento2.setId(recebimento1.getId());
        assertThat(recebimento1).isEqualTo(recebimento2);
        recebimento2.setId(2L);
        assertThat(recebimento1).isNotEqualTo(recebimento2);
        recebimento1.setId(null);
        assertThat(recebimento1).isNotEqualTo(recebimento2);
    }
}
