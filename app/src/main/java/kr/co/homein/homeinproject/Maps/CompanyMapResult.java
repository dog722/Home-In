package kr.co.homein.homeinproject.Maps;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seoeunbi on 2016. 6. 7..
 */
public class CompanyMapResult {
    @SerializedName("result")
    public CompanyMapInfo companyMapInfo;

    public CompanyMapInfo getCompanyMapInfo() {
        return companyMapInfo;
    }

    public void setCompanyMapInfo(CompanyMapInfo companyMapInfo) {
        this.companyMapInfo = companyMapInfo;
    }

    @SerializedName("success")
    public int isResult;

}
