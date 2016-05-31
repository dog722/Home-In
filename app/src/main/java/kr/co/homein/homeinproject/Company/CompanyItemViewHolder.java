package kr.co.homein.homeinproject.Company;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wefika.flowlayout.FlowLayout;

import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyItemData;

/**
 * Created by seoeunbi on 2016. 5. 17..
 */
public class CompanyItemViewHolder extends RecyclerView.ViewHolder {

    CompanyItemData companyItem;
    ImageView imageView;
    TextView scoreView;
    FlowLayout tagLayout;
    TextView distance;
    

    public interface OnItemClickListener {
        public void onItemClick(View view, CompanyItemData companyItemData);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public CompanyItemViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.item_image);
        scoreView = (TextView) itemView.findViewById(R.id.good_score);
        tagLayout = (FlowLayout)itemView.findViewById(R.id.tag_layout);
        distance = (TextView) itemView.findViewById(R.id.distance);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, companyItem);
                }
            }
        });

    }

    public String getCompanyItemId(CompanyItemData companyItem){
        this.companyItem = companyItem;
        return companyItem.getCH_number();

    }

    public void setCompanyItem(CompanyItemData companyItem) {
        this.companyItem = companyItem;


        scoreView.setText(companyItem.getCH_pick()+"");
        distance.setText(companyItem.getDistance());
        tagLayout.removeAllViews();

        for (String s : companyItem.getCH_tag()) {
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

        if (companyItem.getCH_picture().size() > 0) {
            List<String> url = companyItem.getCH_picture();
            Glide.with(imageView.getContext()).load(url.get(0)).into(imageView);
        }
    }

}