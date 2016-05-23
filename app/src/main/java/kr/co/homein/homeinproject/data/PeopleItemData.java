package kr.co.homein.homeinproject.data;

import java.util.ArrayList;

/**
 * Created by seoeunbi on 2016. 5. 13..
 */
public class PeopleItemData {

    String url;
    String goodCount;
    public ArrayList<String> tag = new ArrayList<String>();

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(String goodCount) {
        this.goodCount = goodCount;
    }

    /**
     * Created by seoeunbi on 2016. 5. 16..
     */

}
