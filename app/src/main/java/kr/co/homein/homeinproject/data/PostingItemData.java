package kr.co.homein.homeinproject.data;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 19..
 */
public class PostingItemData {

    String _id;
    String post_number;
    String post_name;
    String post_website;
    String post_regdate;
    List<String> post_picture;
    List<String >post_tag;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPost_number() {
        return post_number;
    }

    public void setPost_number(String post_number) {
        this.post_number = post_number;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_website() {
        return post_website;
    }

    public void setPost_website(String post_website) {
        this.post_website = post_website;
    }

    public String getPost_regdate() {
        return post_regdate;
    }

    public void setPost_regdate(String post_regdate) {
        this.post_regdate = post_regdate;
    }

    public List<String> getPost_picture() {
        return post_picture;
    }

    public void setPost_picture(List<String> post_picture) {
        this.post_picture = post_picture;
    }

    public List<String> getPost_tag() {
        return post_tag;
    }

    public void setPost_tag(List<String> post_tag) {
        this.post_tag = post_tag;
    }
}
