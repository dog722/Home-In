package kr.co.homein.homeinproject.data;

import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 16..
 */
public class EventPageData {


    public String _id;
    public String event_number;
    public List <String> event_picture;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEvent_number() {
        return event_number;
    }

    public void setEvent_number(String event_number) {
        this.event_number = event_number;
    }

    public List<String> getEvent_picture() {
        return event_picture;
    }

    public void setEvent_picture(List<String> event_picture) {
        this.event_picture = event_picture;
    }
}
