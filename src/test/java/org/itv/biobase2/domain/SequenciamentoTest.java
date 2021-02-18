package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class SequenciamentoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sequenciamento.class);
        Sequenciamento sequenciamento1 = new Sequenciamento();
        sequenciamento1.setId(1L);
        Sequenciamento sequenciamento2 = new Sequenciamento();
        sequenciamento2.setId(sequenciamento1.getId());
        assertThat(sequenciamento1).isEqualTo(sequenciamento2);
        sequenciamento2.setId(2L);
        assertThat(sequenciamento1).isNotEqualTo(sequenciamento2);
        sequenciamento1.setId(null);
        assertThat(sequenciamento1).isNotEqualTo(sequenciamento2);
    }
}
