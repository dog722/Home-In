package kr.co.homein.homeinproject.Company;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.homein.homeinproject.data.CompanyItemData;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 17..
 */
public class CompanyItemListAdapter extends RecyclerView.Adapter<CompanyItemViewHolder> {

    List<CompanyItemData> companyItem = new ArrayList<>();


    CompanyItemViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(CompanyItemViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }



    @Override
    public CompanyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item_view, parent , false);
        return new CompanyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompanyItemViewHolder holder, int position) {
        CompanyItemViewHolder h = (CompanyItemViewHolder) holder;
        h.setCompanyItem(companyItem.get(position));
        holder.setOnItemClickListener(mListener);

    }

    @Override
    public int getItemCount() {
        return companyItem.size();
    }


    public void clear() {
        companyItem.clear();
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
        return totalCount == 0 ? false : (totalCount > companyItem.size() ? true : false);
    }


    public void add(CompanyItemData product) {
        companyItem.add(product);
        notifyDataSetChanged();
    }

    public void addAll(List<CompanyItemData> items) {
        this.companyItem.addAll(items);
        notifyDataSetChanged();
    }

}

