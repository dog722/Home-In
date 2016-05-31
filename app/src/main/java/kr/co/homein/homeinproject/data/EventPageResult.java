package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 31..
 */
public class EventPageResult {
    @SerializedName("result")

    public List<EventPageData> eventPageData;
}
