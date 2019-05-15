package au.com.optus.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by revathy.ms on 13/5/19.
 */
public class SearchData implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("searchText")
	private List<String> searchDataList;

	public List<String> getSearchDataList() {
		return searchDataList;
	}

	public void setSearchDataList(List<String> searchDataList) {
		this.searchDataList = searchDataList;
	}
	

}
