package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 26..
 */


public class PeopleItemDataResult {
    @SerializedName("result")
    public List<PeopleItemData> peopleitemdata;
}
