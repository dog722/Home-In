package kr.co.homein.homeinproject.Estimate;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seoeunbi on 2016. 6. 8..
 */
public class GalleryAdapter  extends BaseAdapter {
    public List<String> items = new ArrayList<>();
//    int checkResult=0;


    public void addAll(List <String> items) {
        this.items.addAll(items);
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
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(parent.getContext());
        } else {
            imageView = (ImageView)convertView;
        }

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(360, 360));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(354, 354);
//        params.height = 354;

        imageView.setLayoutParams(params);


                /*
                ImageView img = (ImageView) findViewById(R.id.imgView);
LayoutParams params = (LayoutParams) img.getLayoutParams();
params.width = metrics.widthPixels;
params.height = metrics.heightPixels;

img.setLayoutParams(params);

                 */


        File path_temp = new File(items.get(position));
        Uri fileUri = Uri.fromFile(path_temp);
        Glide.with(parent.getContext()).load(fileUri).into(imageView);


//        imageView.setImage(items.get(position).resId);
        return imageView;
    }





}
