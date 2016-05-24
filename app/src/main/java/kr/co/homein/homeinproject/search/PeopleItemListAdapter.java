package kr.co.homein.homeinproject.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.HomeIn.PeoPleItemListViewHolder;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PeopleItemData;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class PeopleItemListAdapter extends RecyclerView.Adapter<PeoPleItemListViewHolder> {

    List<PeopleItemData> peopleItem = new ArrayList<>();


    PeoPleItemListViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(PeoPleItemListViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }



    @Override
    public PeoPleItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_item_view, parent , false);
        return new PeoPleItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeoPleItemListViewHolder holder, int position) {
        PeoPleItemListViewHolder h = (PeoPleItemListViewHolder) holder;
        h.setPeopleItem(peopleItem.get(position));
        holder.setOnItemClickListener(mListener);

    }

    @Override
    public int getItemCount() {
        return peopleItem.size();
    }


    public void clear() {
        peopleItem.clear();
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
        return totalCount == 0 ? false : (totalCount > peopleItem.size() ? true : false);
    }


    public void add(PeopleItemData product) {
        peopleItem.add(product);
        notifyDataSetChanged();
    }

    public void addAll(List<PeopleItemData> items) {
        this.peopleItem.addAll(items);
        notifyDataSetChanged();
    }

}

