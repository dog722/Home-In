package kr.co.homein.homeinproject.Maps;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 8..
 */
public class HomeInMapData {

    List<AroundOffice> around_office;
    String office_number;
    String office_name;
    String office_sub_name;
    List<String> office_picture;
    String office_address1;
    String office_address2;
    OfficeAddressNumber office_address_number;

    public List<AroundOffice> getAround_office() {
        return around_office;
    }

    public void setAround_office(List<AroundOffice> around_office) {
        this.around_office = around_office;
    }

    public String getOffice_number() {
        return office_number;
    }

    public void setOffice_number(String office_number) {
        this.office_number = office_number;
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

    public List<String> getOffice_picture() {
        return office_picture;
    }

    public void setOffice_picture(List<String> office_picture) {
        this.office_picture = office_picture;
    }

    public String getOffice_address1() {
        return office_address1;
    }

    public void setOffice_address1(String office_address1) {
        this.office_address1 = office_address1;
    }

    public String getOffice_address2() {
        return office_address2;
    }

    public void setOffice_address2(String office_address2) {
        this.office_address2 = office_address2;
    }

    public OfficeAddressNumber getOffice_address_number() {
        return office_address_number;
    }

    public void setOffice_address_number(OfficeAddressNumber office_address_number) {
        this.office_address_number = office_address_number;
    }
}
