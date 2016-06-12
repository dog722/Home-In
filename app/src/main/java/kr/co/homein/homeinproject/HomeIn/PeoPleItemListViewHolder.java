package kr.co.homein.homeinproject.HomeIn;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wefika.flowlayout.FlowLayout;

import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PeopleItemData;

/**
 * Created by seoeunbi on 2016. 5. 13..
 */
public class PeoPleItemListViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView scoreView;
    TextView tag;
    FlowLayout tagLayout;
    TextView time;
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
        time = (TextView) itemView.findViewById(R.id.time_text);
//        tag = (TextView) itemView.findViewById(R.id.btn_tag);



        ///아이템 누르면 넘어가게 구현하기.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onItemClick(v, peopleItem);
                }
            }
        });


    }

//    public String getItemId(PeopleItemData peopleItem){
//        this.peopleItem = peopleItem;
//        return peopleItem.getPH_number();
//
//    }

    public void setPeopleItem(PeopleItemData peopleItem){
        this.peopleItem = peopleItem;

        scoreView.setText(""+peopleItem.getPH_pick());
        time.setText(""+peopleItem.getPass_time());
        tagLayout.removeAllViews();

        for (String s : peopleItem.getPH_tag()) {
            TextView tv = new TextView(itemView.getContext());
            FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);

            lp.setMargins(0, 0, 30, 30);
            lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            tv.setLayoutParams(lp);
            tv.setTextSize(15);

            tv.setPadding(21, 21, 21, 21);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(0XFF01579B);
            tv.setText(s);
            tagLayout.addView(tv);
        }
        List<String> url = peopleItem.getPH_picture();
        Glide.with(imageView.getContext()).load(url.get(0)).into(imageView);
    }

}
