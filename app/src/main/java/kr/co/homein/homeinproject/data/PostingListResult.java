package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 11..
 */
public class PostingListResult {

    @SerializedName("result")
    public List<PostingListData> postingListData;
}
