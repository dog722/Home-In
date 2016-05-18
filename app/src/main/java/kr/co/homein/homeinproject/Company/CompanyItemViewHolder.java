package kr.co.homein.homeinproject.Company;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.homein.homeinproject.data.CompanyItemData;

import kr.co.homein.homeinproject.R;




/**
 * Created by seoeunbi on 2016. 5. 17..
 */
public class CompanyItemViewHolder extends RecyclerView.ViewHolder {

    CompanyItemData companyItem;
    ImageView imageView;
    TextView scoreView;

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

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, companyItem);
                }
            }
        });

    }


    public void setCompanyItem(CompanyItemData companyItem) {
        this.companyItem = companyItem;


        scoreView.setText(companyItem.getGoodCount());
//        Glide.with(imageView.getContext()).load(url).into(imageView);

    }

}