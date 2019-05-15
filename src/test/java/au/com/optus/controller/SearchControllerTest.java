package au.com.optus.controller;

import au.com.optus.model.SearchData;
import au.com.optus.model.SearchResult;
import au.com.optus.service.SearchServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by revathy.ms on 14/5/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

    @Mock
    ObjectMapper mapper;
    @InjectMocks
    private SearchController controller;
    @Mock
    private SearchServiceImpl searchService;
    @Mock
    private SearchResult result;
    @Mock
    private HttpServletResponse response;


    @Test
    public void testGetSearchDataCount() throws Exception {

        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        String searchCount = "{\"counts\":[{\"Duis\":11},{\"Sed\":16}]}";

        Map<String, Integer> stringTokenMap = new HashMap<String, Integer>();
        stringTokenMap.put("duis", 11);
        stringTokenMap.put("sed", 15);

        SearchData searchData = new SearchData();
        List<String> search = new ArrayList<String>();
        search.add("Duis");
        search.add("Sed");
        searchData.setSearchDataList(search);

        when(searchService.getWordCountMap()).thenReturn(stringTokenMap);
        Mockito.doNothing().when(result).setCounts(any(List.class));

        SearchResult result = controller.getSearchDataCount(searchData);
        assertNotNull(result);
        verify(searchService, times(1)).getWordCountMap();
    }

    @Test
    public void testGetTopCount() throws Exception {

        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        String csvText = "eget|17\n" +
                "vel|17\n" +
                "sed|16\n" +
                "in|15\n" +
                "et|14";

        Map<String, Integer> stringTokenMap = new HashMap<String, Integer>();
        stringTokenMap.put("Duis", 11);
        stringTokenMap.put("Sed", 15);

        when(searchService.getWordCountMap()).thenReturn(stringTokenMap);
        when(searchService.getTopOrderList(any(Map.class), any(Integer.class))).thenReturn(csvText);
        when(response.getWriter()).thenReturn(printWriter);

        controller.getTopCount(2, response);
        verify(searchService, times(1)).getWordCountMap();
        verify(searchService, times(1)).getTopOrderList(any(Map.class), any(Integer.class));

    }

    @Test
    public void testGetTopCountWhenTopCountIsLessThanZero() throws Exception {

        PrintWriter printWriter = Mockito.mock(PrintWriter.class);

        when(response.getWriter()).thenReturn(printWriter);

        controller.getTopCount(0, response);
        verify(searchService, Mockito.never()).getWordCountMap();
        verify(searchService, Mockito.never()).getTopOrderList(any(Map.class), any(Integer.class));

    }

}
