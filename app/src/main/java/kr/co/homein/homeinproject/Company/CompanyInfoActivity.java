package kr.co.homein.homeinproject.Company;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;

import kr.co.homein.homeinproject.R;

public class CompanyInfoActivity extends AppCompatActivity {
    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);

        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //탭 등록
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("업체 프로필"), CompanyInfoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("시공사례"), CompanyInfoItemFragment.class, null);

    }

}
