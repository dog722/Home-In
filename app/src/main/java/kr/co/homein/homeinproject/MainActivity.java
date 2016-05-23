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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;

import kr.co.homein.homeinproject.Company.CompanyItemFragment;
import kr.co.homein.homeinproject.Estimate.MyEstimateListActivity;
import kr.co.homein.homeinproject.HomeIn.AddPeopleImageActivity;
import kr.co.homein.homeinproject.HomeIn.PeopleItemFragment;
import kr.co.homein.homeinproject.HomeIn.SearchTagActivity;
import kr.co.homein.homeinproject.Menu.InformationRuleActivity;
import kr.co.homein.homeinproject.Menu.MyInfoActivity;
import kr.co.homein.homeinproject.Menu.NoticeActivity;
import kr.co.homein.homeinproject.Menu.ServiceInfoActivity;
import kr.co.homein.homeinproject.Menu.SettingActivity;
import kr.co.homein.homeinproject.Menu.VersionInfoActivity;
import kr.co.homein.homeinproject.Posting.PostingFragment;
import kr.co.homein.homeinproject.User.UserProfileActivity;
import kr.co.homein.homeinproject.User.WishListActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FragmentTabHost tabHost;
    NavigationView menuView;
    DrawerLayout drawer;
    ImageButton editBtn;
    ImageButton menuBtn;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);


         fab = (FloatingActionButton) findViewById(R.id.fab);
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
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("피플 홈인"), PeopleItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("시공 홈인"), CompanyItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("포스팅"), PostingFragment.class, null);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId == "tab1") {
                    editBtn.setVisibility(View.VISIBLE);
                }
                else if(tabId == "tab2") {
                    editBtn.setVisibility(View.GONE);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
                else if(tabId == "tab3") {
                    editBtn.setVisibility(View.GONE);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
            }
        });

        menuView = (NavigationView)findViewById(R.id.nav_menu);
        menuView.setNavigationItemSelectedListener(this);
        View headerView = menuView.getHeaderView(0);
        drawer = (DrawerLayout)findViewById(R.id.drawer);
         menuBtn = (ImageButton)findViewById(R.id.btn_menu);
        editBtn = (ImageButton) findViewById(R.id.btn_edit);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddPeopleImageActivity.class)); //관심 목록으로 이동

            }
        });


        Toolbar toolbar = (Toolbar) headerView.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_search);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SearchTagActivity.class)); //
            }
        });

        ImageButton imgBtn = (ImageButton) headerView.findViewById(R.id.wishlist);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WishListActivity.class)); //관심 목록으로 이동

            }
        });


        imgBtn  = (ImageButton) headerView.findViewById(R.id.estimatelist);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MyEstimateListActivity.class)); // 내 견적서 문의로 이동

            }
        });


        ImageView profileImg = (ImageView) headerView.findViewById(R.id.profile_img);
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserProfileActivity.class)); // 프로필 수정 화면으로 이동
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




//
    public void goneEditBtn(){

        editBtn.setVisibility(View.GONE);
    }

    public void visibleEditBtn(){
        editBtn.setVisibility(View.VISIBLE);

    }

    public void goneMenuBtn(){
        menuBtn.setVisibility(View.GONE);
    }

    public void visibleMenuBtn(){
        menuBtn.setVisibility(View.VISIBLE);
    }

    public void visibleMapBtn(){
        fab.setVisibility(View.VISIBLE);
    }

    public void goneMapBtn(){
        fab.setVisibility(View.GONE);
    }



//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        editBtn.setVisibility(View.VISIBLE);
//    }

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
