package kr.co.homein.homeinproject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 17..
 */
public class CompanyItemData {

    String distance;
    String _id;
    String CH_number;
    String CH_regdate;
    List<String> CH_picture = new ArrayList<String>();
    int CH_pick;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCH_number() {
        return CH_number;
    }

    public void setCH_number(String CH_number) {
        this.CH_number = CH_number;
    }

    public String getCH_regdate() {
        return CH_regdate;
    }

    public void setCH_regdate(String CH_regdate) {
        this.CH_regdate = CH_regdate;
    }

    public List<String> getCH_picture() {
        return CH_picture;
    }

    public void setCH_picture(List<String> CH_picture) {
        this.CH_picture = CH_picture;
    }

    public int getCH_pick() {
        return CH_pick;
    }

    public void setCH_pick(int CH_pick) {
        this.CH_pick = CH_pick;
    }

    public List<String> getCH_tag() {
        return CH_tag;
    }

    public void setCH_tag(List<String> CH_tag) {
        this.CH_tag = CH_tag;
    }

    List<String> CH_tag = new ArrayList<String>();


    public ArrayList<String> tag = new ArrayList<String>();

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }


}
