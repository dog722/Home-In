package kr.co.homein.homeinproject.Posting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PostingItemData;
import kr.co.homein.homeinproject.data.PostingListData;

/**
 * Created by seoeunbi on 2016. 5. 19..
 */
public class PostingAdapter extends  RecyclerView.Adapter<PostingViewHolder>{

    List<PostingListData> postItem = new ArrayList<>();


    PostingViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(PostingViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }



    @Override
    public PostingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posting_view, parent , false);
        return new PostingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostingViewHolder holder, int position) {
        PostingViewHolder h = (PostingViewHolder) holder;
        h.setPostingItem(postItem.get(position));
        holder.setOnItemClickListener(mListener);

    }

    @Override
    public int getItemCount() {
        return postItem.size();
    }


    public void clear() {
        postItem.clear();
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
        return totalCount == 0 ? false : (totalCount > postItem.size() ? true : false);
    }


    public void add(PostingListData product) {
        postItem.add(product);
        notifyDataSetChanged();
    }

    public void addAll(List<PostingListData> items) {
        this.postItem.addAll(items);
        notifyDataSetChanged();
    }

}

