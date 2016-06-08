package kr.co.homein.homeinproject.Estimate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

/**
 * Created by seoeunbi on 2016. 5. 27..
 */
public class MyEstimateHeaderViewHolder2 extends RecyclerView.ViewHolder{

    TextView homeType;
    TextView homeSubType;
    TextView homeSize;
    TextView userName;
    TextView userEmail;
    TextView userPhone;
    TextView contentEstimate;

    private EstimateDetailData estimateDetailData;

    public MyEstimateHeaderViewHolder2(View view) {
        super(view);

        homeType = (TextView) view.findViewById(R.id.home_type);
        homeSubType = (TextView) view.findViewById(R.id.sub_home_type);
        homeSize = (TextView)view.findViewById(R.id.home_size);
        userName = (TextView) view.findViewById(R.id.name);
        userEmail = (TextView)view.findViewById(R.id.email);
        userPhone = (TextView) view.findViewById(R.id.phone);
        contentEstimate = (TextView)view.findViewById(R.id.content);

    }


    public void setEstimateHeader2Data(EstimateDetailData estimateHeader2Data) {
        this.estimateDetailData = estimateHeader2Data;

        homeType.setText( estimateDetailData.getEstimate_space());
        homeSubType.setText(estimateDetailData.getEstimate_sub_space());
        homeSize.setText(estimateDetailData.getEstimate_size());

        userName.setText(estimateDetailData.getGeneral_real_name());
        userEmail.setText(estimateDetailData.getGeneral_email());
        userPhone.setText(estimateDetailData.getGeneral_tel());
        contentEstimate.setText(estimateDetailData.getInterior_info_content());

    }
}
