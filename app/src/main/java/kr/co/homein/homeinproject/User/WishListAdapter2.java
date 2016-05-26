package kr.co.homein.homeinproject.User;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class WishListAdapter2 extends RecyclerView.Adapter<WishListViewHolder2>   {

    List<WishListData> wishListdata = new ArrayList<>();

    WishListViewHolder2.OnItemClickListener mListener;

    public void setOnItemClickListener(WishListViewHolder2.OnItemClickListener listener) {
        mListener = listener;
    }



    @Override
    public WishListViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_view, parent, false);
        return new WishListViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(WishListViewHolder2 holder, int position) {
//        WishListViewHolder h = (WishListViewHolder) holder;
        holder.setWishListItem(wishListdata.get(position));
        holder.setOnItemClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return wishListdata.size();
    }


    public void clear() {
        wishListdata.clear();
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
        return totalCount == 0 ? false : (totalCount > wishListdata.size() ? true : false);
    }
    public void add(WishListData product) {
        wishListdata.add(product);
        notifyDataSetChanged();
    }

    public void addAll(List<WishListData> items) {
        this.wishListdata.addAll(items);
        notifyDataSetChanged();
    }

}


