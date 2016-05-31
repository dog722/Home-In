package kr.co.homein.homeinproject.HomeIn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CommentData;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class PeopleItemDetailAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VEIW_TYPE_BODY = 2;

    List<CommentData> commentData = new ArrayList<>();
    PeopleDetailItemData peopleDetailItemData;


//    public void addAll(List<TStoreCategory> items) {
//        this.items.addAll(items);
//        notifyDataSetChanged();
//    }

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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_item_detail_header_view, parent, false);
                return new PeopleDetailItemViewHolder(view);
            }
            case VEIW_TYPE_BODY :{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_view, parent, false);
                return new CommentViewHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER: {
                PeopleDetailItemViewHolder h = (PeopleDetailItemViewHolder) holder;
                h.setPeopleDetailItem(peopleDetailItemData);
                break;
            }
            case VEIW_TYPE_BODY : {
                CommentViewHolder h = (CommentViewHolder) holder;
                h.setComment(peopleDetailItemData.getPH_comment().get(position - 1));
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (peopleDetailItemData == null) return 0;
        return peopleDetailItemData.getPH_comment().size() + 1;
    }

    public void set(PeopleDetailItemData peopleDetailItemData) {
        this.peopleDetailItemData = peopleDetailItemData;
        notifyDataSetChanged();
    }
//
//    public void addComment(PeopleDetailItemData peopleDetailItemData){
//        this.peopleDetailItemData = peopleDetailItemData;
//        peopleDetailItemData.getPH_comment()
//        notifyDataSetChanged();
//    }


}
