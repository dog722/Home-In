package kr.co.homein.homeinproject.data;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 25..
 */
public class MyInfoData {


    String _id;
    String general_id ;
    String general_name;
    int general_login_yn;
    String genral_location;
    String general_tel;
    String general_number;
    List<String> general_picture;

    public String getGeneral_number() {
        return general_number;
    }

    public void setGeneral_number(String general_number) {
        this.general_number = general_number;
    }

    public String getGeneral_tel() {
        return general_tel;
    }

    public void setGeneral_tel(String general_tel) {
        this.general_tel = general_tel;
    }

    public List<String> getGeneral_picture() {
        return general_picture;
    }

    public int getGeneral_login_yn() {
        return general_login_yn;
    }

    public void setGeneral_login_yn(int general_login_yn) {
        this.general_login_yn = general_login_yn;
    }

    public void setGeneral_picture(List<String> general_picture) {
        this.general_picture = general_picture;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGenral_location() {
        return genral_location;
    }

    public void setGenral_location(String genral_location) {
        this.genral_location = genral_location;
    }

    public String getGeneral_id() {
        return general_id;
    }

    public void setGeneral_id(String general_id) {
        this.general_id = general_id;
    }

    public String getGeneral_name() {
        return general_name;
    }

    public void setGeneral_name(String general_name) {
        this.general_name = general_name;
    }
}
