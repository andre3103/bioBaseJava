package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class ImageDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageData.class);
        ImageData imageData1 = new ImageData();
        imageData1.setId(1L);
        ImageData imageData2 = new ImageData();
        imageData2.setId(imageData1.getId());
        assertThat(imageData1).isEqualTo(imageData2);
        imageData2.setId(2L);
        assertThat(imageData1).isNotEqualTo(imageData2);
        imageData1.setId(null);
        assertThat(imageData1).isNotEqualTo(imageData2);
    }
}
