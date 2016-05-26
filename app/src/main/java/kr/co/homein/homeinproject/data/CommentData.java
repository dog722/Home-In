package kr.co.homein.homeinproject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class CommentData {

    public String comment_number;
    public String comment_regdata;
    public String comment_content;
    public int response_comment_number;
    public String member_name;
    public List<CommentData> response_comment = new ArrayList<CommentData>();
}
