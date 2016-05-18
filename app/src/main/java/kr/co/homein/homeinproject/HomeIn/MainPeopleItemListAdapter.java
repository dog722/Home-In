package kr.co.homein.homeinproject.HomeIn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EventPageData;
import kr.co.homein.homeinproject.data.PeopleItemData;

/**
 * Created by seoeunbi on 2016. 5. 13..
 */
public class MainPeopleItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VEIW_TYPE_BODY = 2;

//    PeopleItemData product;

    EventPageData EventPageItem = new EventPageData();
    List<PeopleItemData> peopleItem = new ArrayList<>();



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


    public void setEventPage( EventPageAdapter eAdapter){

        notifyDataSetChanged();

    }


    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VEIW_TYPE_BODY;
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


//    PeoPleItemListViewHolder.OnItemClickListener mListener;
//    public void setOnItemClickListener(PeoPleItemListViewHolder.OnItemClickListener listener) {
//        mListener = listener;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case VIEW_TYPE_HEADER :{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homein_header_view, null);
//                return new MainHeaderViewHolder(view);
//                return new TestHeaderViewHolder(view);
            }
            case VEIW_TYPE_BODY :{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_item_view, null);
                return new PeoPleItemListViewHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER: {
//                MainHeaderViewHolder h = (MainHeaderViewHolder) holder;
//                h.setHeaderITem();

                break;
            }
            case VEIW_TYPE_BODY : {
                PeoPleItemListViewHolder h = (PeoPleItemListViewHolder) holder;
                h.setPeopleItem(peopleItem.get(position));
                break;
            }
        }

    }







    @Override
    public int getItemCount() {
        return  peopleItem.size() + 1;
    }
}
