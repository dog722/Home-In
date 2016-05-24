package kr.co.homein.homeinproject.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;

public class SearchResultActivity extends AppCompatActivity {

    FragmentTabHost tabHost;
    TextView tagText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        tagText = (TextView) findViewById(R.id.search_tag);


        intent = getIntent();
        String tag = intent.getStringExtra("tag");
        tagText.setText(tag);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //탭 등록
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("피플 홈인"), SearchPeopleItemListFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("시공 홈인"), SearchCompanyItemListFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("포스팅"), SearchPostingFragment.class, null);

    }
}
