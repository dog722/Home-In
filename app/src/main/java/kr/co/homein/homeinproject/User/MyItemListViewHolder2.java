package kr.co.homein.homeinproject.User;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyItemListData;

/**
 * Created by seoeunbi on 2016. 6. 14..
 */
public class MyItemListViewHolder2 extends RecyclerView.ViewHolder implements Checkable {

    MyItemListData myItemListData;
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
        public void onItemClick(View view, MyItemListData myItemListData, int position);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MyItemListViewHolder2(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.select_img);
        checkedImg = (ImageView)itemView.findViewById(R.id.checked);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, myItemListData, getAdapterPosition());
                }
            }
        });

    }

    public void setWishListItem(MyItemListData myItemListData) {
        this.myItemListData = myItemListData;
//        Glide.with(imageView.getContext()).load(wishListData.getPost_picture().get(0)).into(imageView);

        if (myItemListData.getPH_picture().size()> 0) {
            List<String> url = myItemListData.getPH_picture();
            Glide.with(imageView.getContext()).load(url.get(0)).into(imageView);
        }
    }

}