package kr.co.homein.homeinproject.Estimate;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

/**
 * Created by seoeunbi on 2016. 5. 27..
 */
public class MyEstimateHeaderViewHolder1  extends RecyclerView.ViewHolder{

    ViewPager viewPager;
    DotIndicator infoIndicator;
    EstimatePageAdapter EPageAdapter;
    private EstimateDetailData estimateDetailData;

    public MyEstimateHeaderViewHolder1(View view) {
        super(view);

        viewPager = (ViewPager) view.findViewById(R.id.estimatePage);
        infoIndicator = (DotIndicator) view.findViewById(R.id.dot);
        infoIndicator.setSelectedDotColor(Color.parseColor("#013ADF"));
        infoIndicator.setUnselectedDotColor(Color.parseColor("#CFCFCF"));

        EPageAdapter = new EstimatePageAdapter(view.getContext());

        viewPager.setAdapter(EPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //indicater 구현
//                Toast.makeText(getContext(), "dfsdf", Toast.LENGTH_SHORT).show();
                // only one selected


                infoIndicator.setNumberOfItems(estimateDetailData.getInterior_picture().size());
                infoIndicator.setSelectedItem(viewPager.getCurrentItem(), true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    public void setEstimateHeader1Data(EstimateDetailData estimateHeader1Data) {
        this.estimateDetailData = estimateHeader1Data;
        EPageAdapter.set(estimateDetailData);
    }
}
