package kr.co.homein.homeinproject.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.SearchData;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class SearchViewHolder  extends RecyclerView.ViewHolder {

    TextView companyName, searchCount;
    SearchData searchData;


    public SearchViewHolder(View itemView) {
        super(itemView);


        companyName = (TextView) itemView.findViewById(R.id.search_result);
        searchCount = (TextView) itemView.findViewById(R.id.count);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, searchData );
                }
            }
        });

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, SearchData searchData);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




    public void setSearchItem(SearchData searchData) {
        this.searchData = searchData;
        companyName.setText(searchData.getTag_name());
        searchCount.setText(searchData.getCount()+"");
    }

}