package kr.co.homein.homeinproject.HomeIn;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wefika.flowlayout.FlowLayout;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class PeopleDetailItemViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView userId;
    TextView comment;
    TextView tag;
    FlowLayout tagLayout;

    int isBtn = 0 ;
    int result;
    PeopleDetailItemData peopleDetailItem;

   public PeopleDetailItemViewHolder(View view) {
        super(view);

       userId = (TextView) view.findViewById(R.id.user_id);
       comment = (TextView) view.findViewById(R.id.comment2);
       tag =(TextView) view.findViewById(R.id.tag1);
       tagLayout = (FlowLayout) view.findViewById(R.id.tag_layout);
       img = (ImageView) view.findViewById(R.id.item_image);

    }

    public void setPeopleDetailItem(PeopleDetailItemData PeopleDetailItemData){
        this.peopleDetailItem = PeopleDetailItemData;

        userId.setText(peopleDetailItem.getGeneral_name());
        comment.setText(peopleDetailItem.getPH_content());
        for (String s : peopleDetailItem.getPH_tag()) {
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

        if (peopleDetailItem.getPH_picture().size() > 0) {
            String url = peopleDetailItem.getPH_picture().get(0);
            Log.d("테스트url ", url);
            Glide.with(img.getContext()).load(url).into(img);
        }

    }
}
