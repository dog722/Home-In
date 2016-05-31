package kr.co.homein.homeinproject.Company;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;
import com.wefika.flowlayout.FlowLayout;

import java.io.IOException;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyDetailItemData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class CompanyDetailItemActivity extends AppCompatActivity {
    public static final String EXTRA_PEOPLE_ITEM_CODE = "company_item_code";

    String CH_number;
    FlowLayout layout;
    final static String CH_NUMBER = "CH_number";
    final static String OF_NUMBER = "office_number";
    String officeNumber;

    Intent intent;
    CompanyDetailItemData companyDetailItemData;
    ViewPager viewPager;
    TextView userId;
    TextView comment;
    TextView comment2;
    TextView adress , price, period , size, style;
    ImageButton backKey;
    TextView subName;

    CompanyItemPageAdapter mAdapter; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail_item);

        layout = (FlowLayout) findViewById(R.id.tag_layout);

        viewPager = (ViewPager) findViewById(R.id.company_viewPager); //
        userId = (TextView) findViewById(R.id.user_id);
        subName = (TextView) findViewById(R.id.sub_name);

        comment = (TextView) findViewById(R.id.comment2);
        comment2= (TextView) findViewById(R.id.comment3);
        adress = (TextView) findViewById(R.id.adress);
        price = (TextView) findViewById(R.id.price);
        period = (TextView) findViewById(R.id.period);
        backKey = (ImageButton) findViewById(R.id.back_key);
        style = (TextView) findViewById(R.id.style);

        intent= getIntent();
        CH_number = intent.getStringExtra(CH_NUMBER);
//        Toast.makeText(CompanyDetailItemActivity.this, "CH_number : "+ CH_number, Toast.LENGTH_SHORT).show();

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
                        intent.putExtra(OF_NUMBER, officeNumber);
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


    }

//    private void setData() {
//
//        companyDetailItemData.comment =  "마일드 블랙 스타일 제안";
//        companyDetailItemData.user_id="한셈 인테리어";
//        companyDetailItemData.tag.add("#42평");
//        companyDetailItemData.tag.add("#한셈인테리어");
//        companyDetailItemData.comment2 = "한샘 마일드 블랙 스타일은 꾸미지 않은 듯 자연스로운 분위기 속에서, 균형잡힌 절제미와 세련된 감각이 " +
//                "묻어나는 스타일입니다.";
//        companyDetailItemData.adress = "경기도 화성시 반송동 현대아이파크 42평";
//        companyDetailItemData.price = "5000만원 이상";
//        companyDetailItemData.period = "3주 이상";
//        companyDetailItemData.size = "42평";
//
//    }

    private void setData() {

        NetworkManager.getInstance().getCompanyItemDetail(this, CH_number, new NetworkManager.OnResultListener<CompanyDetailItemData>() {
            @Override
            public void onSuccess(Request request, CompanyDetailItemData result) {
//                mAdapter.set(result);
                companyDetailItemData = result;
                Toast.makeText(CompanyDetailItemActivity.this, "server connected", Toast.LENGTH_SHORT).show();
                userId.setText(companyDetailItemData.getOffice_name());
                subName.setText(companyDetailItemData.getOffice_sub_name());
                comment.setText(companyDetailItemData.getCH_content1());
                adress.setText(companyDetailItemData.getCH_location());
                comment2.setText(companyDetailItemData.getCH_content2());
                price.setText(companyDetailItemData.getCH_price());
                period.setText(companyDetailItemData.getCH_period());
                style.setText(companyDetailItemData.getCH_living());

                officeNumber = companyDetailItemData.getOffice_number();

                for (String s : companyDetailItemData.getCH_tag()) {
                    TextView tv = new TextView(CompanyDetailItemActivity.this);
                    FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(20, 20, 20, 20);
                    tv.setLayoutParams(lp);
                    tv.setTextSize(20);
                    tv.setTextColor(Color.WHITE);
                    tv.setBackgroundColor(Color.BLUE);
                    tv.setText(s);
                    layout.addView(tv);
                }

                List<String> url = companyDetailItemData.getCH_picture();
//                Glide.with(imageView.getContext()).load(url.get(0)).into(imageView);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(CompanyDetailItemActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
