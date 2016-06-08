package kr.co.homein.homeinproject.Estimate;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 3..
 */
public class CategoryAdapter  extends BaseAdapter {
    public List<CategoryItem> items = new ArrayList<>();
    int checkResult=0;
    public void addAll(CategoryItem[] items) {
        this.items.addAll(Arrays.asList(items));
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView itemView;
        if (convertView == null) {
            itemView = new ItemView(parent.getContext());
        } else {
            itemView = (ItemView)convertView;
        }
        itemView.setImage(items.get(position).resId);
        return itemView;
    }





}
