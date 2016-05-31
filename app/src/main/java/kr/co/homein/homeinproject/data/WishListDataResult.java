package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 31..
 */
public class WishListDataResult {
    @SerializedName("result")
    public List<WishListData> wishListData;
}
