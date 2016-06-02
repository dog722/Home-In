package kr.co.homein.homeinproject.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 2..
 */
public class EstimateDetailData implements Serializable {
    String _id;
    String general_estimate_number;
    String general_number;
    String estimate_space;
    String estimate_sub_space;
    String estimate_size;
    String general_real_name;
    String general_email;
    String general_tel;
    String interior_info_content;
    int category_number;
    String estimate_regdate;
    EstimateComment estimate_comment;
    List<String> interior_picture;

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

    public String getGeneral_number() {
        return general_number;
    }

    public void setGeneral_number(String general_number) {
        this.general_number = general_number;
    }

    public String getEstimate_space() {
        return estimate_space;
    }

    public void setEstimate_space(String estimate_space) {
        this.estimate_space = estimate_space;
    }

    public String getEstimate_sub_space() {
        return estimate_sub_space;
    }

    public void setEstimate_sub_space(String estimate_sub_space) {
        this.estimate_sub_space = estimate_sub_space;
    }

    public String getEstimate_size() {
        return estimate_size;
    }

    public void setEstimate_size(String estimate_size) {
        this.estimate_size = estimate_size;
    }

    public String getGeneral_real_name() {
        return general_real_name;
    }

    public void setGeneral_real_name(String general_real_name) {
        this.general_real_name = general_real_name;
    }

    public String getGeneral_email() {
        return general_email;
    }

    public void setGeneral_email(String general_email) {
        this.general_email = general_email;
    }

    public String getGeneral_tel() {
        return general_tel;
    }

    public void setGeneral_tel(String general_tel) {
        this.general_tel = general_tel;
    }

    public String getInterior_info_content() {
        return interior_info_content;
    }

    public void setInterior_info_content(String interior_info_content) {
        this.interior_info_content = interior_info_content;
    }

    public int getCategory_number() {
        return category_number;
    }

    public void setCategory_number(int category_number) {
        this.category_number = category_number;
    }

    public String getEstimate_regdate() {
        return estimate_regdate;
    }

    public void setEstimate_regdate(String estimate_regdate) {
        this.estimate_regdate = estimate_regdate;
    }

    public EstimateComment getEstimate_comment() {
        return estimate_comment;
    }

    public void setEstimate_comment(EstimateComment estimate_comment) {
        this.estimate_comment = estimate_comment;
    }

    public List<String> getInterior_picture() {
        return interior_picture;
    }

    public void setInterior_picture(List<String> interior_picture) {
        this.interior_picture = interior_picture;
    }
}
