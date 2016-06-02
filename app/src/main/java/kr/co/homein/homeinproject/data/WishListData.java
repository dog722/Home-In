package kr.co.homein.homeinproject.data;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class WishListData {

    String _id;
    String posting_number;
    List <String> posting_picture ;
    int category_number;


    public int getCategory_number() {
        return category_number;
    }

    public void setCategory_number(int category_number) {
        this.category_number = category_number;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPosting_number() {
        return posting_number;
    }

    public void setPosting_number(String posting_number) {
        this.posting_number = posting_number;
    }

    public List<String> getPosting_picture() {
        return posting_picture;
    }

    public void setPosting_picture(List<String> posting_picture) {
        this.posting_picture = posting_picture;
    }
}
