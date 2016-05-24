package kr.co.homein.homeinproject.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.SearchData;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class SearchAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VEIW_TYPE_BODY = 2;

    List<SearchData> searchData = new ArrayList<>();
    String totalCount;

    SearchViewHolder.OnItemClickListener mListener;

    public void setOnItemClickListener(SearchViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }




    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VEIW_TYPE_BODY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_HEADER :{
                //이 부분 주의!!
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_header_view, parent, false);
                return new SearchHeaderViewHolder(view);
            }
            case VEIW_TYPE_BODY :{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_view, parent, false);
                return new SearchViewHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER: {
                SearchHeaderViewHolder h = (SearchHeaderViewHolder) holder;
                h.setSearchHeaderItem(totalCount);
                break;
            }
            case VEIW_TYPE_BODY : {
                SearchViewHolder h = (SearchViewHolder) holder;
                h.setSearchItem(searchData.get(position-1));
                h.setOnItemClickListener(mListener);

                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return searchData.size() + 1;
    }

    public void addHeader(String totalCount) {
        this.totalCount = totalCount;
        notifyDataSetChanged();
    }

    public void addSearchItem(SearchData searchData){
        this.searchData.add(searchData);
        notifyDataSetChanged();
    }
}
