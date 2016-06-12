package kr.co.homein.homeinproject.Company;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class CompanyItemPageAdapter extends PagerAdapter {

    List<String> items = new ArrayList<String>();
//
//    List<View> scrapped = new ArrayList<View>();

    Context context;
    Bitmap galImage;
    BitmapFactory.Options options;
    private final int[] galImages = new int[] {
            R.drawable.company_item_dummy,
            R.drawable.company_item_dummy,
            R.drawable.company_item_dummy,
            R.drawable.company_item_dummy
    };

    CompanyItemPageAdapter(Context context) {
        this.context = context;
        options = new BitmapFactory.Options();
    }


    public void addAll(List<String> items){
        this.items.addAll(items);
        notifyDataSetChanged();

    }
    
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
//            int padding = context.getResources().getDimensionPixelSize();
//            imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        options.inSampleSize = 4;
//        galImage = BitmapFactory.decodeResource(context.getResources(), items.get(position), options);
//
//        imageView.setImageBitmap(galImage);
        ((ViewPager) container).addView(imageView, 0);
        Glide.with(imageView.getContext()).load(items.get(position)).into(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}