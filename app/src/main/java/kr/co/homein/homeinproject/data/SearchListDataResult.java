package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 31..
 */
public class SearchListDataResult {

    @SerializedName("search_result")
    public List<SearchData> searchData;
    int total_count;

    public int getTotal_count() {
            return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<SearchData> getSearchData() {
        return searchData;
    }

    public void setSearchData(List<SearchData> searchData) {
        this.searchData = searchData;
    }
}
