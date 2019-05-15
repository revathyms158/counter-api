package au.com.optus.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by revathy.ms on 14/5/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceImplTest {

    @InjectMocks
    SearchServiceImpl searchService;

    @Before
    public void setUp() {
        searchService = new SearchServiceImpl();
    }


    @Test
    public void testGetWordCountMap() throws Exception {

        Map result = searchService.getWordCountMap();
        assertNotNull(result);
        assertTrue(result.containsKey("sed"));

    }

    @Test
    public void testGetTopOrderList() throws Exception {

        Map<String, Integer> stringTokenMap = new HashMap<String, Integer>();
        stringTokenMap.put("sed", 16);
        stringTokenMap.put("in", 15);
        stringTokenMap.put("eget", 17);

        assertNotNull(searchService);
        String result = searchService.getTopOrderList(stringTokenMap, 3);
        assertNotNull(result);
        assertTrue(result.contains("sed|16"));
    }

}
