package kr.co.homein.homeinproject.HomeIn;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wefika.flowlayout.FlowLayout;

import kr.co.homein.homeinproject.data.PeopleItemData;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 13..
 */
public class PeoPleItemListViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView scoreView;
    TextView tag;
    FlowLayout tagLayout;





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
        tagLayout = (FlowLayout)itemView.findViewById(R.id.tag_layout);
//        tag = (TextView) itemView.findViewById(R.id.btn_tag);


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

        tagLayout.removeAllViews();

        for (String s : peopleItem.tag) {
            TextView tv = new TextView(itemView.getContext());
            FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 20, 20, 20);
            tv.setLayoutParams(lp);
            tv.setTextSize(20);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(Color.BLUE);
            tv.setText(s);
            tagLayout.addView(tv);
        }
//        Glide.with(imageView.getContext()).load(url).into(imageView);

    }




}
