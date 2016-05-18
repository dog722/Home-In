package kr.co.homein.homeinproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import kr.co.homein.homeinproject.Company.CompanyItemFragment;
import kr.co.homein.homeinproject.HomeIn.PeopleItemFragment;
import kr.co.homein.homeinproject.Menu.InformationRuleActivity;
import kr.co.homein.homeinproject.Menu.MyInfoActivity;
import kr.co.homein.homeinproject.Menu.NoticeActivity;
import kr.co.homein.homeinproject.Menu.ServiceInfoActivity;
import kr.co.homein.homeinproject.Menu.SettingActivity;
import kr.co.homein.homeinproject.Menu.VersionInfoActivity;
import kr.co.homein.homeinproject.Posting.PostingFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FragmentTabHost tabHost;
    NavigationView menuView;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //탭 등록
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("홈-인"), PeopleItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("시공사례"), CompanyItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("포스팅"), PostingFragment.class, null);

        menuView = (NavigationView)findViewById(R.id.nav_menu);
        menuView.setNavigationItemSelectedListener(this);
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        Button btn = (Button)findViewById(R.id.btn_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_info) { //내 정보
            startActivity(new Intent(MainActivity.this, MyInfoActivity.class));

        } else if (id == R.id.notice) { //공지사항
            startActivity(new Intent(MainActivity.this, NoticeActivity.class));

        } else if (id == R.id.service) { //서비스 이용약관
            startActivity(new Intent(MainActivity.this, ServiceInfoActivity.class));

        } else if (id == R.id.information_rule) { //개인정보 보호정책
            startActivity(new Intent(MainActivity.this, InformationRuleActivity.class));

        } else if (id == R.id.version) { //버전정보
            startActivity(new Intent(MainActivity.this, VersionInfoActivity.class));

          }
        else if (id == R.id.setting) { //환경설정
            startActivity(new Intent(MainActivity.this, SettingActivity.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
