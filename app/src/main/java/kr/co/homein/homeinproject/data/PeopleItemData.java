package kr.co.homein.homeinproject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 13..
 */
public class PeopleItemData {

//    String url;
//    String goodCount;
    String PH_number;
    int category_number;
    String pass_time;
    int PH_pick;
    String _id;
    String PH_regdate;
    public List<String> PH_picture;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPH_regdate() {
        return PH_regdate;
    }

    public void setPH_regdate(String PH_regdate) {
        this.PH_regdate = PH_regdate;
    }

    public ArrayList<String> PH_tag = new ArrayList<String>();

    public String getPH_number() {
        return PH_number;
    }

    public void setPH_number(String PH_number) {
        this.PH_number = PH_number;
    }

    public int getCategory_number() {
        return category_number;
    }

    public void setCategory_number(int category_number) {
        this.category_number = category_number;
    }

    public String getPass_time() {
        return pass_time;
    }

    public void setPass_time(String pass_time) {
        this.pass_time = pass_time;
    }

    public int getPH_pick() {
        return PH_pick;
    }

    public void setPH_pick(int PH_pick) {
        this.PH_pick = PH_pick;
    }

    public List<String> getPH_picture() {
        return PH_picture;
    }

    public void setPH_picture(List<String> PH_picture) {
        this.PH_picture = PH_picture;
    }

    public ArrayList<String> getPH_tag() {
        return PH_tag;
    }

    public void setPH_tag(ArrayList<String> PH_tag) {
        this.PH_tag = PH_tag;
    }

/**
     * Created by seoeunbi on 2016. 5. 16..
     */

}
