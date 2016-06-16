package kr.co.homein.homeinproject.data;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 2..
 */
public class EstimateListData {

    String _id;
    String general_estimate_number;
    String interior_info_content;
    List<String> interior_picture ;
    String estimate_regdate;

    public String getEstimate_regdate() {
        return estimate_regdate;
    }

    public void setEstimate_regdate(String estimate_regdate) {
        this.estimate_regdate = estimate_regdate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGeneral_estimate_number() {
        return general_estimate_number;
    }

    public void setGeneral_estimate_number(String general_estimate_number) {
        this.general_estimate_number = general_estimate_number;
    }

    public String getInterior_info_content() {
        return interior_info_content;
    }

    public void setInterior_info_content(String interior_info_content) {
        this.interior_info_content = interior_info_content;
    }

    public List<String> getInterior_picture() {
        return interior_picture;
    }

    public void setInterior_picture(List<String> interior_picture) {
        this.interior_picture = interior_picture;
    }
}
