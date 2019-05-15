package au.com.optus.model;

import java.util.HashMap;
import java.util.List;

public class SearchResult {

private List<HashMap<String, Integer>> counts;

public List<HashMap<String, Integer>> getCounts() {
	return counts;
}

public void setCounts(List<HashMap<String, Integer>> counts) {
	this.counts = counts;
}

}
