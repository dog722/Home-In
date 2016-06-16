package kr.co.homein.homeinproject.User;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyItemListData;

/**
 * Created by seoeunbi on 2016. 6. 14..
 */
public class MyItemListAdapter1 extends RecyclerView.Adapter<MyItemListViewHolder1>   {

    List<MyItemListData> myItemListData= new ArrayList<>();

    MyItemListViewHolder1.OnItemClickListener mListener;

    public void setOnItemClickListener(MyItemListViewHolder1.OnItemClickListener listener) {
        mListener = listener;
    }



    @Override
    public MyItemListViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_view, parent, false);
        return new MyItemListViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(MyItemListViewHolder1 holder, int position) {
//        WishListViewHolder h = (WishListViewHolder) holder;
        holder.setWishListItem(myItemListData.get(position));
        holder.setOnItemClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return myItemListData.size();
    }


    public void clear() {
        myItemListData.clear();
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
        return totalCount == 0 ? false : (totalCount > myItemListData.size() ? true : false);
    }
    public void add(MyItemListData product) {
        myItemListData.add(product);
        notifyDataSetChanged();
    }

    public void addAll(List<MyItemListData> items) {
        this.myItemListData.addAll(items);
        notifyDataSetChanged();
    }

}


