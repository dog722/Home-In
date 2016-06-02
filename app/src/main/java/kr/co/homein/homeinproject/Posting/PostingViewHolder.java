package kr.co.homein.homeinproject.Posting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PostingItemData;

/**
 * Created by seoeunbi on 2016. 5. 19..
 */
public class PostingViewHolder extends RecyclerView.ViewHolder {
    PostingItemData postingItemData = new PostingItemData();
    ImageView imageView;
    TextView postingTitle;

    public interface OnItemClickListener {
        public void onItemClick(View view, PostingItemData postingItemData);
    }

    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public PostingViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.posting_img);
        postingTitle = (TextView) itemView.findViewById(R.id.posting_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, postingItemData);
                }
            }
        });

    }


    public void setPostingItem(PostingItemData postingItemData) {
        this.postingItemData = postingItemData;
        postingTitle.setText(postingItemData.getPost_name());
//        Glide.with(imageView.getContext()).load(url).into(imageView);

    }
}