package kr.co.homein.homeinproject.data;

import java.util.ArrayList;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class PeopleDetailItemData {

    public String PH_number;
    public String general_name;
    public ArrayList<String> PH_picture = new ArrayList<String>();
    public String _id;
    public String PH_content;
    public ArrayList<String> PH_tag = new ArrayList<String>();
    public int PH_pick;
    public ArrayList<CommentData> PH_comment = new ArrayList<CommentData>();

    public String getPH_number() {
        return PH_number;
    }

    public void setPH_number(String PH_number) {
        this.PH_number = PH_number;
    }

    public String getGeneral_id() {
        return general_name;
    }

    public void setGeneral_id(String general_id) {
        this.general_name = general_id;
    }

    public ArrayList<String> getPH_picture() {
        return PH_picture;
    }

    public String getPH_content() {
        return PH_content;
    }

    public void setPH_content(String PH_content) {
        this.PH_content = PH_content;
    }

    public ArrayList<String> getPH_tag() {
        return PH_tag;
    }

    public void setPH_tag(ArrayList<String> PH_tag) {
        this.PH_tag = PH_tag;
    }

    public int getPH_pick() {
        return PH_pick;
    }

    public void setPH_pick(int PH_pick) {
        this.PH_pick = PH_pick;
    }

    public ArrayList<CommentData> getPH_comment() {
        return PH_comment;
    }

    public void setPH_comment(ArrayList<CommentData> PH_comment) {
        this.PH_comment = PH_comment;
    }

    public String getGeneral_name() {
        return general_name;
    }

    public void setGeneral_name(String general_name) {
        this.general_name = general_name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setPH_picture(ArrayList<String> PH_picture) {
        this.PH_picture = PH_picture;
    }
}
