package kr.co.homein.homeinproject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class CommentData {

    public List<ResponseCommentData> response_comment = new ArrayList<ResponseCommentData>();
    public String _id;
    public String posting_number;
    public String comment_content;
    public String member_name;
    public String comment_number;
    public String recomment_regdate;
    public int response_comment_number;

    public int getResponse_comment_number() {
        return response_comment_number;
    }

    public void setResponse_comment_number(int response_comment_number) {
        this.response_comment_number = response_comment_number;
    }

    public String getRecomment_regdate() {
        return recomment_regdate;
    }

    public void setRecomment_regdate(String recomment_regdate) {
        this.recomment_regdate = recomment_regdate;
    }

    public List<ResponseCommentData> getResponse_comment() {
        return response_comment;
    }

    public void setResponse_comment(List<ResponseCommentData> response_comment) {
        this.response_comment = response_comment;
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

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
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
}
