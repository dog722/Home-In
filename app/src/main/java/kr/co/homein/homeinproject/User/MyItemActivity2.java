package kr.co.homein.homeinproject.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyItemListData;
import kr.co.homein.homeinproject.data.ReceiveResultData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class MyItemActivity2 extends AppCompatActivity {

    MyItemListAdapter2 mAdapter;
    RecyclerView listView;
    TextView deleteBtn;
    List<MyItemListData> myItemListData;
    SparseBooleanArray deleteResult = new SparseBooleanArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        deleteBtn = (TextView) findViewById(R.id.btn_delete);

        listView = (RecyclerView)  findViewById(R.id.rv_list);
        mAdapter = new MyItemListAdapter2();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 3));


        deleteBtn.setOnClickListener(new View.OnClickListener() { //삭제 버튼
            @Override
            public void onClick(View v) {

                SparseBooleanArray array = mAdapter.getCheckedItemPositions();
                List<String> select = new ArrayList<String>();



                select.clear();
                for (int index = 0; index < array.size(); index++) {
                    int position = array.keyAt(index);
                    if (array.get(position)) {
                        Toast.makeText(MyItemActivity2.this, "position : " + mAdapter.getWishListdata(position).getPH_number(), Toast.LENGTH_SHORT).show();
                        select.add(mAdapter.getWishListdata(position).getPH_number());
                    }
                }
                Toast.makeText(MyItemActivity2.this, "delete바로전 ", Toast.LENGTH_SHORT).show();


                if(select.size() != 0) {
                    deleteWishList(select);
                }
                startActivity(new Intent(MyItemActivity2.this, MyItemActivity1.class));
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.clear();
        setData();

    }

    private void deleteWishList(List<String> select) {

        Toast.makeText(MyItemActivity2.this, "select 첫 원소 : +" + select.get(0), Toast.LENGTH_SHORT).show();

        /////여기다가 삭제 구현!
        NetworkManager.getInstance().deleteMyITemList(this, PropertyManager.getInstance().getGeneralNumber(), select, new NetworkManager.OnResultListener<ReceiveResultData>() {
            @Override
            public void onSuccess(Request request, ReceiveResultData result) {

                if (result.isResult == 1) {
                    Toast.makeText(MyItemActivity2.this, "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyItemActivity2.this, "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyItemActivity2.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        NetworkManager.getInstance().getMyItemList(this, PropertyManager.getInstance().getGeneralNumber(), new NetworkManager.OnResultListener<List<MyItemListData>>() {
            @Override
            public void onSuccess(Request request, List<MyItemListData> result) {
//                mAdapter.clear();
//                wishListData= result;
                myItemListData = result;
                mAdapter.addAll(result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyItemActivity2.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
