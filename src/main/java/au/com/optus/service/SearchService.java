package au.com.optus.service;

import java.util.List;
import java.util.Map;

/**
 * Created by revathy.ms on 14/5/19.
 */
public interface SearchService {

    /**
     * To process the file and retrieve the word and count as a map
     *
     * @return
     */
    Map getWordCountMap();

    /**
     *To retrieve the top number of text and occurrence from sorted map
     *
     * @param sortedMap
     * @param count
     * @return
     */
    String getTopOrderList(Map<String, Integer> sortedMap, int count);
}
