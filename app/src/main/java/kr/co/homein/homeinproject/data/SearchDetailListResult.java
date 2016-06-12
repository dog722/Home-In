package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 1..
 */
public class SearchDetailListResult {

    @SerializedName("People_homein")
    public List<PeopleItemData> peopleItemData;

    @SerializedName("Construction_homein")
    public List<CompanyItemData> companyItemData;

    @SerializedName("Posting")
    public List<PostingListData> postingItemData;

}
