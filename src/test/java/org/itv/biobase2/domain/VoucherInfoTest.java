package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class VoucherInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoucherInfo.class);
        VoucherInfo voucherInfo1 = new VoucherInfo();
        voucherInfo1.setId(1L);
        VoucherInfo voucherInfo2 = new VoucherInfo();
        voucherInfo2.setId(voucherInfo1.getId());
        assertThat(voucherInfo1).isEqualTo(voucherInfo2);
        voucherInfo2.setId(2L);
        assertThat(voucherInfo1).isNotEqualTo(voucherInfo2);
        voucherInfo1.setId(null);
        assertThat(voucherInfo1).isNotEqualTo(voucherInfo2);
    }
}
