package kr.co.homein.homeinproject.HomeIn;

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

import kr.co.homein.homeinproject.data.EventPageData;

/**
 * Created by seoeunbi on 2016. 5. 13..
 */
public class EventPageAdapter extends PagerAdapter {

//    List<String> items = new ArrayList<String>();
//
//    List<View> scrapped = new ArrayList<View>();

        Context context;
        Bitmap galImage;
        BitmapFactory.Options options;


    List <EventPageData> eventPageData = new  ArrayList<EventPageData>();
//    EventPageData eventPageData = new EventPageData();


    EventPageAdapter(Context context) {
            this.context = context;
//            options = new BitmapFactory.Options();
        }



        public void clear() {
            eventPageData.clear();
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            return eventPageData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      //eventPageData.get(position)
                }
            });

                Glide.with(imageView.getContext()).load(eventPageData.get(position).getEvent_picture().get(0)).into(imageView);
//            }
            ((ViewPager) container).addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }


    public void addAll(List<EventPageData> items) {
        this.eventPageData.addAll(items);
        notifyDataSetChanged();
    }

    }