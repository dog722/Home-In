package kr.co.homein.homeinproject.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seoeunbi on 2016. 5. 27..
 */
public class ResponseCommentData {
    @SerializedName("response_comment")
    public String _id;
    public String recomment_content;
    public String member_name;
    public String comment_number;
    public String recomment_number;
    public String recomment_regdate;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRecomment_content() {
        return recomment_content;
    }

    public void setRecomment_content(String recomment_content) {
        this.recomment_content = recomment_content;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getComment_number() {
        return comment_number;
    }

    public void setComment_number(String comment_number) {
        this.comment_number = comment_number;
    }

    public String getRecomment_number() {
        return recomment_number;
    }

    public void setRecomment_number(String recomment_number) {
        this.recomment_number = recomment_number;
    }

    public String getRecomment_regdate() {
        return recomment_regdate;
    }

    public void setRecomment_regdate(String recomment_regdate) {
        this.recomment_regdate = recomment_regdate;
    }
}
