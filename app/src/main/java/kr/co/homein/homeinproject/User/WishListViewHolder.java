package kr.co.homein.homeinproject.User;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class WishListViewHolder extends RecyclerView.ViewHolder implements Checkable {

    WishListData wishListData;
    ImageView imageView;
    ImageView checkedImg;


    boolean isChecked = false;

    @Override
    public void setChecked(boolean checked) {

        if (isChecked != checked) {
            isChecked = checked;
            drawCheck();
        }
    }


    @Override
    public boolean isChecked() {

        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }

    public void drawCheck() {
        if (isChecked) { // check draw
            checkedImg.setVisibility(View.VISIBLE);
//            imageView.setImageResource(R.drawable.check);

        }else{ // un check draw
            checkedImg.setVisibility(View.GONE);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, WishListData wishListData , int position);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public WishListViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.wishImg);
        checkedImg = (ImageView)itemView.findViewById(R.id.checked);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, wishListData, getAdapterPosition());
                }
            }
        });

    }

    public void setWishListItem(WishListData wishListData) {
        this.wishListData = wishListData;
//        Glide.with(imageView.getContext()).load(wishListData.getUrl()).into(imageView);
    }



}