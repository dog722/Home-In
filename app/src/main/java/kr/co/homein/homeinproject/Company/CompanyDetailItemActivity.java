package kr.co.homein.homeinproject.Company;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyDetailItemData;

public class CompanyDetailItemActivity extends AppCompatActivity {
    public static final String EXTRA_PEOPLE_ITEM_CODE = "company_item_code";


    CompanyDetailItemData companyDetailItemData;
    ViewPager viewPager;
    TextView userId;
    TextView comment;
    TextView tag1, tag2;
    TextView comment2;
    TextView adress , price, period , size;
    ImageButton backKey;

    CompanyItemPageAdapter mAdapter; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail_item);
        companyDetailItemData = new CompanyDetailItemData();

        viewPager = (ViewPager) findViewById(R.id.company_viewPager); //
        userId = (TextView) findViewById(R.id.user_id);
        comment = (TextView) findViewById(R.id.comment2);
        tag1 = (TextView) findViewById(R.id.tag1);
        tag2 = (TextView) findViewById(R.id.tag2);
        comment2= (TextView) findViewById(R.id.comment3);
        adress = (TextView) findViewById(R.id.adress);
        price = (TextView) findViewById(R.id.price);
        period = (TextView) findViewById(R.id.period);
        size = (TextView) findViewById(R.id.size);
        backKey = (ImageButton) findViewById(R.id.back_key);
//        backKey.setEnabled(false);
        final DotIndicator infoIndicator = (DotIndicator)findViewById(R.id.dot);
        infoIndicator.setSelectedDotColor(Color.parseColor("#013ADF"));
        infoIndicator.setUnselectedDotColor(Color.parseColor("#CFCFCF"));

        backKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PeopleItemDetailActivity.this, MainActivity.class); //홈으로 가기
//                startActivity(intent);
                finish();
            }
        });

        //업체 프로필로 이동
        userId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(CompanyDetailItemActivity.this, CompanyInfoActivity.class); //사용자 아이템 상세 페이지 이동
                        startActivity(intent);
            }
        });


        mAdapter = new CompanyItemPageAdapter(this);//
        infoIndicator.setNumberOfItems(mAdapter.getCount());
        viewPager.setAdapter(mAdapter);//
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                infoIndicator.setSelectedItem(viewPager.getCurrentItem(), true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });//


        setData();
        userId.setText(companyDetailItemData.getUser_id());
        comment.setText(companyDetailItemData.getComment());
        tag1.setText(companyDetailItemData.getTag().get(0));
        tag2.setText(companyDetailItemData.getTag().get(1));
        adress.setText(companyDetailItemData.getAdress());
        comment2.setText(companyDetailItemData.getComment2());
        price.setText(companyDetailItemData.getPrice());
        period.setText(companyDetailItemData.getPeriod());
        size.setText(companyDetailItemData.getSize());




    }

    private void setData() {

        companyDetailItemData.comment =  "마일드 블랙 스타일 제안";
        companyDetailItemData.user_id="한셈 인테리어";
        companyDetailItemData.tag.add("#42평");
        companyDetailItemData.tag.add("#한셈인테리어");
        companyDetailItemData.comment2 = "한샘 마일드 블랙 스타일은 꾸미지 않은 듯 자연스로운 분위기 속에서, 균형잡힌 절제미와 세련된 감각이 " +
                "묻어나는 스타일입니다.";
        companyDetailItemData.adress = "경기도 화성시 반송동 현대아이파크 42평";
        companyDetailItemData.price = "5000만원 이상";
        companyDetailItemData.period = "3주 이상";
        companyDetailItemData.size = "42평";

    }
}
