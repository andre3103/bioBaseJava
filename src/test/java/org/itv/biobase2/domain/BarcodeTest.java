package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class BarcodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Barcode.class);
        Barcode barcode1 = new Barcode();
        barcode1.setId(1L);
        Barcode barcode2 = new Barcode();
        barcode2.setId(barcode1.getId());
        assertThat(barcode1).isEqualTo(barcode2);
        barcode2.setId(2L);
        assertThat(barcode1).isNotEqualTo(barcode2);
        barcode1.setId(null);
        assertThat(barcode1).isNotEqualTo(barcode2);
    }
}
