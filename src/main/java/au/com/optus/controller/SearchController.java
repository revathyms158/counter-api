package au.com.optus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import au.com.optus.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.optus.model.SearchData;
import au.com.optus.model.SearchResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SearchController {

    @Autowired
    SearchResult searchResult;

    @Autowired
    SearchService searchService;

    ObjectMapper mapper = new ObjectMapper();

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public void getSearchDataCount(@RequestBody SearchData searchData, HttpServletResponse response) throws IOException {

        List<HashMap<String, Integer>> resultList = new ArrayList<HashMap<String, Integer>>();
        HashMap<String, Integer> resultMap;
        Map<String, Integer> stringTokenMap = searchService.getWordCountMap();

        for (String key : searchData.getSearchDataList()) {
            resultMap = new HashMap<String, Integer>();
            resultMap.put(key, stringTokenMap.get(key.toLowerCase()));
            resultList.add(resultMap);
        }
        searchResult.setCounts(resultList);
        response.getWriter().write(mapper.writeValueAsString(searchResult));

    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/top/{count}", method = RequestMethod.GET)
    public void getTopCount(@PathVariable("count") int count, HttpServletResponse response) {
        try {
            response.setContentType("text/plain; charset=utf-8");
            if (count <= 0) {

                response.getWriter().write("Received top value is invalid");
                System.out.println("Received top value is invalid");

            } else {

                Map<String, Integer> sortedMapDesc = sortByComparator(searchService.getWordCountMap());
                response.getWriter().write(searchService.getTopOrderList(sortedMapDesc, count));
            }
        } catch (IOException e) {
            System.out.println("searchController:: IOException occurred inside getTopCount()" + e.getMessage());
            e.printStackTrace();
        }

    }

    private Map<String, Integer> sortByComparator(Map<String, Integer> tokenCountMap) {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(tokenCountMap.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        Map<String, Integer> sortedtokenCountMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list) {
            sortedtokenCountMap.put(entry.getKey(), entry.getValue());
        }
        return sortedtokenCountMap;
    }

}

