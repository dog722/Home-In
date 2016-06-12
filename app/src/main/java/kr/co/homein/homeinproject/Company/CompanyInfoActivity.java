package kr.co.homein.homeinproject.Company;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.co.homein.homeinproject.R;

public class CompanyInfoActivity extends AppCompatActivity {
    FragmentTabHost tabHost;
    Intent intent;
    final static String OF_NUMBER = "office_number";
    String officeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);

        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        intent = getIntent();
        officeNumber = intent.getStringExtra(OF_NUMBER);


        //        tabHost.setBackground();
        View tab1 = (View) getLayoutInflater().inflate(R.layout.com_tab_layout1, null,false);
        View tab2 = (View) getLayoutInflater().inflate(R.layout.com_tab_layout2, null,false);

        //탭 등록
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(tab1), CompanyInfoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(tab2), CompanyInfoItemFragment.class, null);
    }

    public String getOfficeNumber(){
        return officeNumber;
    }


}
