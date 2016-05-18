package kr.co.homein.homeinproject.data;

import java.util.ArrayList;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class CommentData {
    public String userId;
    public String Comment;
    public String groupDesc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public ArrayList<ChildItemData> getChilderen() {
        return childeren;
    }

    public void setChilderen(ArrayList<ChildItemData> childeren) {
        this.childeren = childeren;
    }

    public ArrayList<ChildItemData> childeren = new ArrayList<ChildItemData>();
}
