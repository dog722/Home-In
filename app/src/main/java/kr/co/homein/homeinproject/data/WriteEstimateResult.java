package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seoeunbi on 2016. 6. 2..
 */
public class WriteEstimateResult {

    @SerializedName("success")
    int isSuccess;

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }
}
