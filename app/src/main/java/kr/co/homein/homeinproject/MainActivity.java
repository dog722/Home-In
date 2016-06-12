package kr.co.homein.homeinproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import kr.co.homein.homeinproject.Company.CompanyItemFragment;
import kr.co.homein.homeinproject.Estimate.MyEstimateListActivity;
import kr.co.homein.homeinproject.HomeIn.AddPeopleImageActivity;
import kr.co.homein.homeinproject.HomeIn.PeopleItemFragment;
import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.Maps.HomeInMapActivity;
import kr.co.homein.homeinproject.Menu.InformationRuleActivity;
import kr.co.homein.homeinproject.Menu.MyInfoActivity;
import kr.co.homein.homeinproject.Menu.NoticeActivity;
import kr.co.homein.homeinproject.Menu.ServiceInfoActivity;
import kr.co.homein.homeinproject.Menu.VersionInfoActivity;
import kr.co.homein.homeinproject.Posting.PostingFragment;
import kr.co.homein.homeinproject.User.WishListActivity2;
import kr.co.homein.homeinproject.data.MyInfoData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import kr.co.homein.homeinproject.search.SearchTagActivity;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FragmentTabHost tabHost;
    NavigationView menuView;
    DrawerLayout drawer;
    ImageButton editBtn;
    ImageButton menuBtn;
//    FloatingActionButton fab;
    Uri mFileUri;
    ImageView profileImg;

    ImageButton fab;
    TextView userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

//        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab = (ImageButton) findViewById(R.id.map_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, HomeInMapActivity.class);
                startActivity(intent);
            }
        });

        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


//        Toast.makeText(MainActivity.this, ""+ PropertyManager.getInstance().getGeneralName(), Toast.LENGTH_SHORT).show();



//        tabHost.setBackground();
        View tab1 = (View) getLayoutInflater().inflate(R.layout.tab1_layout, null,false);
        View tab2 = (View) getLayoutInflater().inflate(R.layout.tab2_layout, null,false);
        View tab3 = (View) getLayoutInflater().inflate(R.layout.tab3_layout, null,false);

        //탭 등록
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(tab1), PeopleItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(tab2), CompanyItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(tab3), PostingFragment.class, null);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId == "tab1") {
                    editBtn.setVisibility(View.VISIBLE);
                } else if (tabId == "tab2") {
                    editBtn.setVisibility(View.GONE);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                } else if (tabId == "tab3") {
                    editBtn.setVisibility(View.GONE);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
            }
        });



        menuView = (NavigationView)findViewById(R.id.nav_menu);
        menuView.setNavigationItemSelectedListener(this);
        menuView.setItemIconTintList(null);
        View headerView = menuView.getHeaderView(0);

        profileImg = (ImageView) headerView.findViewById(R.id.profile_img);
        userId = (TextView) headerView.findViewById(R.id.user_id);


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
        toolbar.setNavigationIcon(R.drawable.search);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SearchTagActivity.class)); //
            }
        });


//
//        profileImg = (ImageView) headerView.findViewById(R.id.profile_img);
//        profileImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callGallery();
//            }
//        });


        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable("selected_file");
        }

        setData();



    }

    private static final int RC_GALLERY = 1;
    private static final int CROP_IMAGE = 2;

    private void callGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, RC_GALLERY);
    }

    private Uri getFileUri() {
        File dir = getExternalFilesDir("myfile");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir,"my_image_" + System.currentTimeMillis() + ".jpeg");
        mFileUri = Uri.fromFile(file);
        return mFileUri;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable("selected_file", mFileUri);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    Uri fileUri = Uri.fromFile(new File(path));
//                    BitmapFactory.Options opts = new BitmapFactory.Options();
//                    opts.inSampleSize = 4;
//                    Bitmap bm = BitmapFactory.decodeFile(path, opts);
//                    photoView.setImageBitmap(bm);
                    Glide.with(this).load(fileUri).into(profileImg);
                }
                c.close();
//                Glide.with(this).load(mFileUri).into(photoView);
            }
        }

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




    /*
        <item android:id="@+id/wishlist"
        android:title="관심목록"
        android:icon="@drawable/menu_icon_1"/>
    <item android:id="@+id/my_estimate"
        android:title="문의한 견적서"
        android:icon="@drawable/menu_icon_2"/>
    <item android:id="@+id/my_info"
        android:title="내 정보 수정"
        android:icon="@drawable/menu_icon_3"/>
    <item android:id="@+id/my_item"
        android:title="내 게시물"
        android:icon="@drawable/menu_icon_4"/>
    <item android:id="@+id/setting"
        android:title="설정"
        android:icon="@drawable/menu_icon_4"/>
    <item android:id="@+id/notice"
        android:title="공지사항"
        android:icon="@android:drawable/ic_input_get"/>
    <item android:id="@+id/version"
        android:title="버전 확인"
        android:icon="@android:drawable/ic_input_get"/>
    <item android:id="@+id/guide"
        android:title="이용약관"
        android:icon="@android:drawable/ic_input_get"/>
    <item android:id="@+id/information_rule"
        android:title="개인정보 취급방침"
        android:icon="@android:drawable/ic_input_get"/>
     */


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_info) { //내 정보
            startActivity(new Intent(MainActivity.this, MyInfoActivity.class));

        }else if (id == R.id.wishlist) { //관심목록
            startActivity(new Intent(MainActivity.this, WishListActivity2.class));

        }else if (id == R.id.my_estimate) { //문의한 견적서
            startActivity(new Intent(MainActivity.this, MyEstimateListActivity.class));

        }else if (id == R.id.notice) { //공지사항
            startActivity(new Intent(MainActivity.this, NoticeActivity.class));

        } else if (id == R.id.guide) { //서비스 이용약관
            startActivity(new Intent(MainActivity.this, ServiceInfoActivity.class));

        } else if (id == R.id.information_rule) { //개인정보 보호정책
            startActivity(new Intent(MainActivity.this, InformationRuleActivity.class));

        } else if (id == R.id.version) { //버전정보
            startActivity(new Intent(MainActivity.this, VersionInfoActivity.class));
          }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void setData() {

        NetworkManager.getInstance().getMyInfo(this, PropertyManager.getInstance().getGeneralNumber(), new NetworkManager.OnResultListener<MyInfoData>() {
            @Override
            public void onSuccess(Request request, MyInfoData result) {
//                mAdapter.set(result);


                if(result.getGeneral_picture().size() > 0) {
                    String path = result.getGeneral_picture().get(0);
                    Glide.with(MainActivity.this).load(path).into(profileImg);
                }

                userId.setText(result.getGeneral_name());

//                userPhone.setText(result.get);

            }


            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MainActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }


        });
    }

}



