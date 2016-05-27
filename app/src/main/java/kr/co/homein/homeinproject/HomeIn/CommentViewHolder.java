package kr.co.homein.homeinproject.HomeIn;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CommentData;
import kr.co.homein.homeinproject.data.ResponseCommentData;

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
    TextView[] answerViews = new TextView[3];

    public CommentViewHolder(View view)
    {
        super(view);

        userId = (TextView) view.findViewById(R.id.user_id);
        comment = (TextView) view.findViewById(R.id.comment);
        commentCount = (TextView) view.findViewById(R.id.comment_count);

        btnComment = (ImageButton) view.findViewById(R.id.btn_comment);
        layout = (LinearLayout) view.findViewById(R.id.comment_answer_window);

        answerViews[0] = (TextView)view.findViewById(R.id.comment_answer_1);
        answerViews[1] = (TextView)view.findViewById(R.id.comment_answer_2);
        answerViews[2] = (TextView)view.findViewById(R.id.comment_answer_3);

        layout.setVisibility(View.GONE);
        result = layout.getVisibility();

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //comment_answer_window
                layout.setVisibility(layout.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
//                if(result == View.VISIBLE ){
//                    //닫기
//                    layout.setVisibility(View.GONE);
//                }else if(result == View.GONE){
//                    //열기
//                    layout.setVisibility(View.VISIBLE);
//                }
//
//                result = layout.getVisibility();
            }
        });
    }

    public void setComment(CommentData commentData){
        this.commentData = commentData;
//        int ph_comment_size = commentData.getResponse_comment().size();
        int response_comment_size = commentData.getResponse_comment().size();

        comment.setText( commentData.getComment_content());
        userId.setText(commentData.getMember_name());
        commentCount.setText(commentData.getComment_number());

        int i = 0;
        for (; i < response_comment_size; i++) {
            ResponseCommentData data = commentData.getResponse_comment().get(i);
            answerViews[i].setText(data.getRecomment_content());
            answerViews[i].setVisibility(View.VISIBLE);
        }
        for (;i < 3;i++) {
            answerViews[i].setVisibility(View.GONE);
        }

//        userId.setText(peopleDetailItemData.getGeneral_name());
//        comment.setText(peopleDetailItemData.get);
//        Glide.with(imageView.getContext()).load(url).into(imageView);

    }
}
