package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seoeunbi on 2016. 5. 31..
 */
public class MyInfoDataResult {
    @SerializedName("result")
    public MyInfoData myInfoData;

    @SerializedName("success")
    int isSuccess;

    public MyInfoData getMyInfoData() {
        return myInfoData;
    }

    public void setMyInfoData(MyInfoData myInfoData) {
        this.myInfoData = myInfoData;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }
}
