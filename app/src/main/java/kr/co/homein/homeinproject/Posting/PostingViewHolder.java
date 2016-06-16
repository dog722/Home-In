package kr.co.homein.homeinproject.Posting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PostingListData;

/**
 * Created by seoeunbi on 2016. 5. 19..
 */
public class PostingViewHolder extends RecyclerView.ViewHolder {
    PostingListData postingItemData = new PostingListData();
    ImageView imageView;
    TextView postingTitle;
    TextView score;


    public interface OnItemClickListener {
        public void onItemClick(View view, PostingListData postingItemData);
    }

    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public PostingViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.posting_img);
        postingTitle = (TextView) itemView.findViewById(R.id.posting_title);
        score = (TextView) itemView.findViewById(R.id.score);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, postingItemData);
                }
            }
        });

    }


    public void setPostingItem(PostingListData postingItemData) {
        this.postingItemData = postingItemData;
        postingTitle.setText(postingItemData.getPost_name());
//        score.setText(postingItemData.get);
        if(postingItemData.getPost_picture().size() != 0)
        Glide.with(imageView.getContext()).load(postingItemData.getPost_picture().get(0)).into(imageView);

    }
}