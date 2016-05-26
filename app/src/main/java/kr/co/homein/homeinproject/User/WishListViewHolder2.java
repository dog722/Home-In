package kr.co.homein.homeinproject.User;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class WishListViewHolder2 extends RecyclerView.ViewHolder {

    WishListData wishListData;
    ImageView imageView;

    boolean isChecked = false;

    public interface OnItemClickListener {
        public void onItemClick(View view, WishListData wishListData);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public WishListViewHolder2(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.wishImg);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, wishListData);
                }
            }
        });

    }

    public void setWishListItem(WishListData wishListData) {
        this.wishListData = wishListData;
//        Glide.with(imageView.getContext()).load(wishListData.getUrl()).into(imageView);
    }

}