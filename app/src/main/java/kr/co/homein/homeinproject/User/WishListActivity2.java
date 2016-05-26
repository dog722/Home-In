package kr.co.homein.homeinproject.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.HomeIn.PeopleItemDetailActivity;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;

public class WishListActivity2 extends AppCompatActivity {


    WishListAdapter2 mAdapter;
    RecyclerView listView;
    TextView editBtn;
    List<WishListData> wishListData= new ArrayList<>();
    final static int DELETE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list2);

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
        mAdapter = new WishListAdapter2();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 3));
        editBtn = (TextView) findViewById(R.id.btn_edit);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(WishListActivity2.this, WishListActivity.class) , DELETE);

            }
        });


        mAdapter.setOnItemClickListener(new WishListViewHolder2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, WishListData wishListData) {
                startActivity(new Intent(WishListActivity2.this, PeopleItemDetailActivity.class));
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
