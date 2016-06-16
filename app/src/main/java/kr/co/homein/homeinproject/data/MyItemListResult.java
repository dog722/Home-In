package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 14..
 */
public class MyItemListResult {
    @SerializedName("result")
    public List<MyItemListData> myItemListData;
}
