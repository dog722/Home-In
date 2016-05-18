package kr.co.homein.homeinproject.Company;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import kr.co.homein.homeinproject.data.CompanyDetailItemData;

import kr.co.homein.homeinproject.R;

public class CompanyDetailItemActivity extends AppCompatActivity {
    public static final String EXTRA_PEOPLE_ITEM_CODE = "company_item_code";


    CompanyDetailItemData companyDetailItemData;
    ViewPager viewPager;
    TextView userId;
    TextView comment;
    TextView tag1, tag2;

    CompanyItemPageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail_item);
        companyDetailItemData = new CompanyDetailItemData();

        viewPager = (ViewPager) findViewById(R.id.company_viewPager);
        userId = (TextView) findViewById(R.id.user_id);
        comment = (TextView) findViewById(R.id.comment2);
        tag1 = (TextView) findViewById(R.id.tag1);
        tag2 = (TextView) findViewById(R.id.tag2);

        mAdapter = new CompanyItemPageAdapter(this);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        setData();
        userId.setText(companyDetailItemData.getUser_id());
        comment.setText(companyDetailItemData.getComment());
        tag1.setText(companyDetailItemData.getTag().get(0));
        tag2.setText(companyDetailItemData.getTag().get(1));
    }

    private void setData() {

        companyDetailItemData.comment =  "한셈 인테리어만의 초특가 이벤트! 방문하셔서 확인하세요^^";
        companyDetailItemData.user_id="한셈 인테리어";
        companyDetailItemData.tag.add("#한셈인테리어세일");
        companyDetailItemData.tag.add("#한셈인테리어");

    }
}
