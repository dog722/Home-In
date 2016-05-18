package kr.co.homein.homeinproject.HomeIn;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.co.homein.homeinproject.data.CommentData;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {

    TextView userId;
    TextView comment;
    TextView commentCount;

    CommentData commentData;
    ImageButton btnComment;
    int result;
    LinearLayout layout;



    public CommentViewHolder(View view)
    {
        super(view);

        userId = (TextView) view.findViewById(R.id.user_id);
        comment = (TextView) view.findViewById(R.id.comment);
        commentCount = (TextView) view.findViewById(R.id.comment_count);

        btnComment = (ImageButton) view.findViewById(R.id.btn_comment);
        layout = (LinearLayout) view.findViewById(R.id.comment_answer_window);


        layout.setVisibility(View.GONE);
        result = layout.getVisibility();

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //comment_answer_window
                if(result == View.VISIBLE ){
                    //닫기
                    layout.setVisibility(View.GONE);
                }else if(result == View.GONE){
                    //열기
                    layout.setVisibility(View.VISIBLE);
                }

                result = layout.getVisibility();
            }
        });



    }

    public void setComment(CommentData commentData){
        this.commentData = commentData;

        userId.setText(commentData.getUserId());
        comment.setText(commentData.getComment());
//        Glide.with(imageView.getContext()).load(url).into(imageView);

    }
}
