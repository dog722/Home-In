package kr.co.homein.homeinproject.Company;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 18..
 */
public class CompanyItemPageAdapter extends PagerAdapter {

//    List<String> items = new ArrayList<String>();
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

    @Override
    public int getCount() {
        return galImages.length;
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
        options.inSampleSize = 4;
        galImage = BitmapFactory.decodeResource(context.getResources(), galImages[position], options);

        imageView.setImageBitmap(galImage);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}