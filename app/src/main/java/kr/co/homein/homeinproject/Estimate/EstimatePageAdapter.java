package kr.co.homein.homeinproject.Estimate;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kr.co.homein.homeinproject.data.EstimateDetailData;

/**
 * Created by seoeunbi on 2016. 6. 7..
 */
public class EstimatePageAdapter extends PagerAdapter {

    EstimateDetailData estimateDetailData;
    Context context;

    EstimatePageAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        if(estimateDetailData== null)
            return 0;
        return estimateDetailData.getInterior_picture().size();
    }

    public void set(EstimateDetailData estimateDetailData) {
        this.estimateDetailData = estimateDetailData;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(imageView.getContext()).load(estimateDetailData.getInterior_picture().get(position)).into(imageView);
        ((ViewPager) container).addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
