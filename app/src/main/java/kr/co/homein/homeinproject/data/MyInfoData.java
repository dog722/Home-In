package kr.co.homein.homeinproject.data;

/**
 * Created by seoeunbi on 2016. 5. 25..
 */
public class MyInfoData {


    String _id;
    String general_id ;
    String general_name;
    String genral_location;
    String general_picture;

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

    public String getGeneral_picture() {
        return general_picture;
    }

    public void setGeneral_picture(String general_picture) {
        this.general_picture = general_picture;
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
