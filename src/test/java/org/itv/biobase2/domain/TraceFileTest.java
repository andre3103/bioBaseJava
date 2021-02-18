package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class TraceFileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TraceFile.class);
        TraceFile traceFile1 = new TraceFile();
        traceFile1.setId(1L);
        TraceFile traceFile2 = new TraceFile();
        traceFile2.setId(traceFile1.getId());
        assertThat(traceFile1).isEqualTo(traceFile2);
        traceFile2.setId(2L);
        assertThat(traceFile1).isNotEqualTo(traceFile2);
        traceFile1.setId(null);
        assertThat(traceFile1).isNotEqualTo(traceFile2);
    }
}
