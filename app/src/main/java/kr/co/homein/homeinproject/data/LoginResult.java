package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seoeunbi on 2016. 6. 10..
 */
public class LoginResult {

    @SerializedName("result")
    public MyInfoData myInfoData;


    public MyInfoData getMyInfoData() {
        return myInfoData;
    }

    public void setMyInfoData(MyInfoData myInfoData) {
        this.myInfoData = myInfoData;
    }
}
