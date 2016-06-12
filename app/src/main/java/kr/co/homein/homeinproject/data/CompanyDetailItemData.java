package kr.co.homein.homeinproject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class CompanyDetailItemData {
    String _id;
    String office_name;
    String office_sub_name;
    String office_number;
    public List<String> office_picture;

    public String getOffice_number() {
        return office_number;
    }

    public void setOffice_number(String office_number) {
        this.office_number = office_number;
    }

    String CH_content1;
    String CH_content2;

    public List<String> getOffice_picture() {
        return office_picture;
    }

    public void setOffice_picture(List<String> office_picture) {
        this.office_picture = office_picture;
    }

    String CH_living;
    String CH_price;
    String CH_period;
    String CH_location;
    String CH_number;
    List <String> CH_picture = new ArrayList<String>();
    int CH_pick;
    List <String> CH_tag = new ArrayList<String>();


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOffice_name() {
        return office_name;
    }

    public void setOffice_name(String office_name) {
        this.office_name = office_name;
    }

    public String getOffice_sub_name() {
        return office_sub_name;
    }

    public void setOffice_sub_name(String office_sub_name) {
        this.office_sub_name = office_sub_name;
    }

    public String getCH_content1() {
        return CH_content1;
    }

    public void setCH_content1(String CH_content1) {
        this.CH_content1 = CH_content1;
    }

    public String getCH_content2() {
        return CH_content2;
    }

    public void setCH_content2(String CH_content2) {
        this.CH_content2 = CH_content2;
    }

    public String getCH_living() {
        return CH_living;
    }

    public void setCH_living(String CH_living) {
        this.CH_living = CH_living;
    }

    public String getCH_price() {
        return CH_price;
    }

    public void setCH_price(String CH_price) {
        this.CH_price = CH_price;
    }

    public String getCH_period() {
        return CH_period;
    }

    public void setCH_period(String CH_period) {
        this.CH_period = CH_period;
    }

    public String getCH_location() {
        return CH_location;
    }

    public void setCH_location(String CH_location) {
        this.CH_location = CH_location;
    }

    public String getCH_number() {
        return CH_number;
    }

    public void setCH_number(String CH_number) {
        this.CH_number = CH_number;
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
}
