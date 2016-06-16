package kr.co.homein.homeinproject.data;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 14..
 */
public class MyItemListData {

    public String PH_number;
    public List<String>PH_picture;

    public String getPH_number() {
        return PH_number;
    }

    public void setPH_number(String PH_number) {
        this.PH_number = PH_number;
    }

    public List<String> getPH_picture() {
        return PH_picture;
    }

    public void setPH_picture(List<String> PH_picture) {
        this.PH_picture = PH_picture;
    }
}
