package kr.co.homein.homeinproject.HomeIn;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.homein.homeinproject.data.PeopleItemData;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 13..
 */
public class PeoPleItemListViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView scoreView;
    TextView tag;





   PeopleItemData peopleItem;


    public interface OnItemClickListener {
        public void onItemClick(View view, PeopleItemData peopleItem);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public PeoPleItemListViewHolder(final View itemView) {
        super(itemView);

        imageView = (ImageView)itemView.findViewById(R.id.item_image);
        scoreView = (TextView)itemView.findViewById(R.id.good_score);
        tag = (TextView) itemView.findViewById(R.id.btn_tag);


//
//        ///아이템 누르면 넘어가게 구현하기.
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mListener != null){
//                    mListener.onItemClick(v, peopleItem);
//                }
//            }
//        });


    }

    public void setPeopleItem(PeopleItemData peopleItem){
        this.peopleItem = peopleItem;


        scoreView.setText(peopleItem.getGoodCount());
//        Glide.with(imageView.getContext()).load(url).into(imageView);

    }




}
