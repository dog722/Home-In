package kr.co.homein.homeinproject.User;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
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
public class WishListAdapter extends RecyclerView.Adapter<WishListViewHolder>  implements WishListViewHolder.OnItemClickListener {

    List<WishListData> wishListdata = new ArrayList<>();
    SparseBooleanArray checkedItems = new SparseBooleanArray();

    WishListViewHolder.OnItemClickListener mListener;

    public void setOnItemClickListener(WishListViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }



    @Override
    public WishListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_view, parent, false);
        return new WishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WishListViewHolder holder, int position) {
//        WishListViewHolder h = (WishListViewHolder) holder;
        holder.setWishListItem(wishListdata.get(position));
        holder.setOnItemClickListener(mListener);
        holder.setChecked(checkedItems.get(position));
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

    @Override
    public void onItemClick(View view, WishListData wishListData, int position) {
        boolean checked = !checkedItems.get(position);
        checkedItems.put(position, checked);
        notifyDataSetChanged();
    }

    public SparseBooleanArray getCheckedItemPositions() { return checkedItems; }
    public void setItemCheck(int position, boolean check) {
        checkedItems.put(position, check);
        notifyDataSetChanged();
    }
}


