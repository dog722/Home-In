package kr.co.homein.homeinproject.HomeIn;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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


    int isBtn = 0 ;
    int result;
    PeopleDetailItemData peopleDetailItem;

   public PeopleDetailItemViewHolder(View view) {
        super(view);

       userId = (TextView) view.findViewById(R.id.user_id);
       comment = (TextView) view.findViewById(R.id.comment2);
       tag =(TextView) view.findViewById(R.id.tag1);

    }



    public void setPeopleDetailItem(PeopleDetailItemData PeopleDetailItemData){
        this.peopleDetailItem = PeopleDetailItemData;

        userId.setText(peopleDetailItem.getUser_id());
        comment.setText(peopleDetailItem.getComment());
        tag.setText(peopleDetailItem.getTag().get(0));
//        Glide.with(imageView.getContext()).load(url).into(imageView);

    }



}
