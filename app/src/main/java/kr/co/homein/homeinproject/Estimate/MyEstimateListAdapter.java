package kr.co.homein.homeinproject.Estimate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateListData;

/**
 * Created by seoeunbi on 2016. 5. 19..
 */
public class MyEstimateListAdapter extends  RecyclerView.Adapter<MystimateListViewHolder> {
    List<EstimateListData> myEstimateItem = new ArrayList<>();


    MystimateListViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(MystimateListViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }



    @Override
    public MystimateListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_estimate_view, parent , false);
        return new MystimateListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MystimateListViewHolder holder, int position) {
        MystimateListViewHolder h = (MystimateListViewHolder) holder;
        h.setEstimateItem(myEstimateItem.get(position));
        holder.setOnItemClickListener(mListener);

    }

    @Override
    public int getItemCount() {
        return myEstimateItem.size();
    }


    public void clear() {
        myEstimateItem.clear();
        notifyDataSetChanged();
    }

    private int totalCount = 0;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private int lastPage = 0;

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }


    public boolean isMore() {
//        if (totalCount == 0) return false;
//        if (totalCount > items.size()) return true;
//        return false;
        return totalCount == 0 ? false : (totalCount > myEstimateItem.size() ? true : false);
    }


    public void add(EstimateListData product) {
        myEstimateItem.add(product);
        notifyDataSetChanged();
    }

    public void addAll(List<EstimateListData> items) {
        this.myEstimateItem.addAll(items);
        notifyDataSetChanged();
    }

}
