package kr.co.homein.homeinproject.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyItemData;
import kr.co.homein.homeinproject.data.PeopleItemData;
import kr.co.homein.homeinproject.data.PostingListData;
import kr.co.homein.homeinproject.data.SearchDetailListResult;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class SearchResultActivity extends AppCompatActivity {

    FragmentTabHost tabHost;
    TextView tagText;
    Intent intent;
    String tag;
    List<PeopleItemData> peopleItemData;
    List<CompanyItemData> companyItemData;
    List<PostingListData> postingItemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        tagText = (TextView) findViewById(R.id.search_tag);
        intent = getIntent();
        tag = intent.getStringExtra("tag");
        tagText.setText(tag);


        setData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        View tab1 = (View) getLayoutInflater().inflate(R.layout.search_tab1_layout, null,false);
        View tab2 = (View) getLayoutInflater().inflate(R.layout.search_tab2_layout, null,false);
        View tab3 = (View) getLayoutInflater().inflate(R.layout.search_tab3_layout, null,false);


        //탭 등록
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(tab1), SearchPeopleItemListFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(tab2), SearchCompanyItemListFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(tab3), SearchPostingFragment.class, null);

    }

    public interface OnNotifyDataUpdateListener {
        public void onNotifyDataUpdate();
    }
    List<OnNotifyDataUpdateListener> mListeners = new ArrayList<>();
    public void addOnNotifyDataUpdateListener(OnNotifyDataUpdateListener listener) {
        mListeners.add(listener);
    }

    public void removeNotifyDataUpdateListener(OnNotifyDataUpdateListener listener) {
        mListeners.remove(listener);
    }

    void notifyDataUpdate() {
        for (OnNotifyDataUpdateListener listener : mListeners) {
            listener.onNotifyDataUpdate();
        }
    }


    public List <PeopleItemData> getPeopleData(){
        return  peopleItemData;
    }

    public List <CompanyItemData> getCompanyItemData(){
        return companyItemData;
    }

    public List <PostingListData> getPostingItemData(){
        return postingItemData;
    }




    public void setData(){
        NetworkManager.getInstance().getSearchResultList(this, tag, new NetworkManager.OnResultListener<SearchDetailListResult>() {
            @Override
            public void onSuccess(Request request, SearchDetailListResult result) {


//                Toast.makeText(SearchResultActivity.this, " tag:" + tag, Toast.LENGTH_SHORT).show();
                peopleItemData =  result.peopleItemData;
                companyItemData = result.companyItemData;
                postingItemData = result.postingItemData;
                notifyDataUpdate();

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(SearchResultActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
