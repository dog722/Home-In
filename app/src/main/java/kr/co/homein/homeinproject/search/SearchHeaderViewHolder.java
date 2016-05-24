package kr.co.homein.homeinproject.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */
public class SearchHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView searchTotalCount;
        String totalCount;

public SearchHeaderViewHolder(View itemView) {
        super(itemView);

    searchTotalCount = (TextView) itemView.findViewById(R.id.total_count);

    }
    public void setSearchHeaderItem(String totalCount) {
        this.totalCount = totalCount;
        searchTotalCount.setText(totalCount);
    }
}