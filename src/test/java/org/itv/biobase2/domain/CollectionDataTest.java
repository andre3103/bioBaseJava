package org.itv.biobase2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.itv.biobase2.web.rest.TestUtil;

public class CollectionDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollectionData.class);
        CollectionData collectionData1 = new CollectionData();
        collectionData1.setId(1L);
        CollectionData collectionData2 = new CollectionData();
        collectionData2.setId(collectionData1.getId());
        assertThat(collectionData1).isEqualTo(collectionData2);
        collectionData2.setId(2L);
        assertThat(collectionData1).isNotEqualTo(collectionData2);
        collectionData1.setId(null);
        assertThat(collectionData1).isNotEqualTo(collectionData2);
    }
}
