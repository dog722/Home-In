package kr.co.homein.homeinproject.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import kr.co.homein.homeinproject.HomeIn.PeopleItemDetailActivity;
import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyItemListData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class MyItemActivity1 extends AppCompatActivity {
    MyItemListAdapter1 mAdapter;
    RecyclerView listView;
    TextView editBtn;
    List<MyItemListData> myItemhListData;
    final static int DELETE = 1;
    final static String PH_NUMBER = "PH_number";
    final static String CH_NUMBER = "CH_number";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();

                finish();
            }
        });


        listView = (RecyclerView)  findViewById(R.id.rv_list);
        mAdapter = new MyItemListAdapter1();
        listView.setAdapter(mAdapter);


        listView.setLayoutManager(new GridLayoutManager(this, 3));
        editBtn = (TextView) findViewById(R.id.btn_edit);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MyItemActivity1.this, MyItemActivity2.class), DELETE);
                finish();
            }
        });

        mAdapter.setOnItemClickListener(new MyItemListViewHolder1.OnItemClickListener() {

            @Override
            public void onItemClick(View view, MyItemListData myItemListData) {

                Intent intent;
//                Toast.makeText(MyItemActivity1.this, "posting_number : " + myItemListData.getPH_number(), Toast.LENGTH_SHORT).show();

                intent = new Intent(MyItemActivity1.this, PeopleItemDetailActivity.class);
                intent.putExtra(PH_NUMBER, myItemListData.getPH_number());
                startActivity(intent);

//                //피플 홈인
//                if(wishListData.getCategory_number() == 1){
//                    intent = new Intent(MyItem.this, PeopleItemDetailActivity.class);
//                    intent.putExtra(PH_NUMBER , wishListData.getPosting_number());
//                    startActivity(intent);
//                    //시공 홈인
//                }else if(wishListData.getCategory_number() == 2){
//                    intent = new Intent(WishListActivity2.this, CompanyDetailItemActivity.class);
//                    intent.putExtra(CH_NUMBER , wishListData.getPosting_number());
//                    startActivity(intent);
//                    //포스팅
//                }else if(wishListData.getCategory_number() == 3){
//                    //포스팅 화면으로 이동
//                }


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.clear();
        setData();
    }

    private void setData() {
        NetworkManager.getInstance().getMyItemList(this, PropertyManager.getInstance().getGeneralNumber(), new NetworkManager.OnResultListener<List<MyItemListData>>() {
            @Override
            public void onSuccess(Request request, List<MyItemListData> result) {
//                mAdapter.clear();
//                wishListData= result;
                myItemhListData = result;
                mAdapter.addAll(result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyItemActivity1.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}