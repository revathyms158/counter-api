package au.com.optus.service;

import java.util.List;
import java.util.Map;

/**
 * Created by revathy.ms on 14/5/19.
 */
public interface SearchService {

    Map getWordCountMap();

    String getTopOrderList(Map<String, Integer> sortedMapDesc, int count);
}
