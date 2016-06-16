package kr.co.homein.homeinproject.User;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyItemListData;

/**
 * Created by seoeunbi on 2016. 6. 14..
 */
public class MyItemListViewHolder1 extends RecyclerView.ViewHolder {

    MyItemListData myItemListData;
    ImageView imageView;

    boolean isChecked = false;

    public interface OnItemClickListener {
        public void onItemClick(View view, MyItemListData myItemListData);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MyItemListViewHolder1(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.select_img);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, myItemListData);
                }
            }
        });

    }

    public void setWishListItem(MyItemListData myItemListData) {
        this.myItemListData = myItemListData;
//        Glide.with(imageView.getContext()).load(wishListData.getPost_picture().get(0)).into(imageView);

        if (myItemListData.getPH_picture().size() > 0) {
            List<String> url = myItemListData.getPH_picture();
            Glide.with(imageView.getContext()).load(url.get(0)).into(imageView);
        }
    }

}