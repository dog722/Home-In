package kr.co.homein.homeinproject.Estimate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateComment;

/**
 * Created by seoeunbi on 2016. 6. 7..
 */
public class MyEstimateCommentViewHolder extends RecyclerView.ViewHolder {
    TextView userId;
    TextView comment;
    EstimateComment commentData;

    public MyEstimateCommentViewHolder(View view) {
        super(view);

        userId = (TextView)view.findViewById(R.id.user_id);
        comment = (TextView)view.findViewById(R.id.comment);
    }

    public void setEstimateCommentData(EstimateComment estimateCommentData) {
        this.commentData = estimateCommentData;
        comment.setText(commentData.getComment_content());
        userId.setText(commentData.getMember_name());
    }
}
