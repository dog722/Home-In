package kr.co.homein.homeinproject.Estimate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateListData;

/**
 * Created by seoeunbi on 2016. 5. 19..
 */
public class MystimateListViewHolder extends RecyclerView.ViewHolder {

    EstimateListData myEstimateItemData = new EstimateListData();
    ImageView imageView;
    TextView myEstimateTitle;

    public interface OnItemClickListener {
        public void onItemClick(View view, EstimateListData myEstimateItemData);
    }

    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MystimateListViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.my_estimate_img);
        myEstimateTitle = (TextView) itemView.findViewById(R.id.my_estimate_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, myEstimateItemData);
                }
            }
        });

    }


    public void setEstimateItem(EstimateListData myEstimateItemData) {
        this.myEstimateItemData = myEstimateItemData;
        myEstimateTitle.setText(myEstimateItemData.getInterior_info_content());
        Glide.with(imageView.getContext()).load(myEstimateItemData.getInterior_picture().get(0)).into(imageView);


//        myEstimateTitle.setText(myEstimateItemData.get);

    }
}