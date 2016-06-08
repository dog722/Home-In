package kr.co.homein.homeinproject.Estimate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

/**
 * Created by seoeunbi on 2016. 5. 27..
 */
public class MyEstimateDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VIEW_TYPE_HEADER2 = 2;
    public static final int VIEW_TYPE_BODY = 3;


    EstimateDetailData estimateDetailData;

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return VIEW_TYPE_HEADER;
        }else if(position == 1){
            return  VIEW_TYPE_HEADER2;
        }else{
            return VIEW_TYPE_BODY;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_HEADER :{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_estimate_header_view, parent, false);
                return new MyEstimateHeaderViewHolder1(view);

            }
            case VIEW_TYPE_HEADER2:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_estimate_header_view2, parent, false);
                return new MyEstimateHeaderViewHolder2(view);
            }
            case VIEW_TYPE_BODY :{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_view, parent, false);
                return new MyEstimateCommentViewHolder(view);
            }
        }

        throw  new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER: {
                MyEstimateHeaderViewHolder1 h = (MyEstimateHeaderViewHolder1) holder;
                h.setEstimateHeader1Data(estimateDetailData);
                break;
            }
            case VIEW_TYPE_HEADER2 : {
                MyEstimateHeaderViewHolder2 h = (MyEstimateHeaderViewHolder2) holder;
                h.setEstimateHeader2Data(estimateDetailData);
                break;
            }
             case VIEW_TYPE_BODY : {
                 MyEstimateCommentViewHolder h = (MyEstimateCommentViewHolder) holder;
                 h.setEstimateCommentData(estimateDetailData.getEstimate_comment().get(position - 2)); ///여기서 position -?
                break;
        }
    }
}

    @Override
    public int getItemCount() {
        if (estimateDetailData == null) return 0;
        return estimateDetailData.getEstimate_comment().size() + 2;
    }



    public void set(EstimateDetailData estimateDetailData) {
        this.estimateDetailData = estimateDetailData;
        notifyDataSetChanged();
    }


}
