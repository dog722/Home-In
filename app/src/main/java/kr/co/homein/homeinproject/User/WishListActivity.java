package kr.co.homein.homeinproject.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;

public class WishListActivity extends AppCompatActivity {

    WishListAdapter mAdapter;
    RecyclerView listView;
    List<WishListData> wishListData= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        listView = (RecyclerView)  findViewById(R.id.rv_list);
        mAdapter = new WishListAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 3));


//        mAdapter.setOnItemClickListener(new WishListViewHolder.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, WishListData wishListData, int position) {
//                Toast.makeText(WishListActivity.this, "eunbi!", Toast.LENGTH_SHORT).show();
//            }
//        });

        setWishList();
    }

    private void setWishList() {
            //여기다 이미지 서버에서 받아서 설정해주기

        for(int i = 0 ; i< 7 ; i++){
            WishListData data = new WishListData();
            data.setUrl("url넣기");
            mAdapter.add(data);
        }

    }

}
