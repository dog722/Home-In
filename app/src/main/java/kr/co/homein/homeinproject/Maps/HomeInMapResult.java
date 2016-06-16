package kr.co.homein.homeinproject.Maps;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seoeunbi on 2016. 6. 7..
 */
public class HomeInMapResult {

    @SerializedName("result")
    public HomeInMapData homeInMapData;

    @SerializedName("success")
    public int isResult;
}
