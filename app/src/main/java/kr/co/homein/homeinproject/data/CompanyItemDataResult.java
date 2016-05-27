package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 27..
 */
public class CompanyItemDataResult {
    @SerializedName("result")
    public List<CompanyItemData> companyItemData;
}
