package kr.co.homein.homeinproject.HomeIn;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wefika.flowlayout.FlowLayout;

import java.io.IOException;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PeopleAddWishListResult;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class PeopleDetailItemViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView userId;
    TextView comment;
    TextView tag;
    FlowLayout tagLayout;
    ImageButton add_pick_btn;

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
       add_pick_btn = (ImageButton)  view.findViewById(R.id.add_pick_btn);


       //관심목록 담기
       add_pick_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                pickWishList();
           }
       });
    }


    String general_number = "GM722";
    private void pickWishList() {
        NetworkManager.getInstance().addPeopleWishlist(this, peopleDetailItem.getPH_number(), "GM722", new NetworkManager.OnResultListener<PeopleAddWishListResult>() {
            @Override
            public void onSuccess(Request request, PeopleAddWishListResult result) {
                if(result.isSuccess == 0){
                    Toast.makeText(itemView.getContext(), "이미 관심목록에 존재합니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText( itemView.getContext(), "목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFail(Request request, IOException exception) {
            }
        });

    }


    public void setPeopleDetailItem(PeopleDetailItemData PeopleDetailItemData){
        this.peopleDetailItem = PeopleDetailItemData;

        tagLayout.removeAllViews();

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
