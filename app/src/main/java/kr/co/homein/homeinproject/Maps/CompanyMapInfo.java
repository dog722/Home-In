package kr.co.homein.homeinproject.Maps;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seoeunbi on 2016. 6. 7..
 */
public class CompanyMapInfo {

    @SerializedName("current_address")
    CurrentOffice currentOffice;


    public CurrentOffice getCurrentOffice() {
        return currentOffice;
    }

    public void setCurrentOffice(CurrentOffice currentOffice) {
        this.currentOffice = currentOffice;
    }

}
