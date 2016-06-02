package kr.co.homein.homeinproject.User;

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

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;
import kr.co.homein.homeinproject.data.WishListResult;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class WishListActivity extends AppCompatActivity {

    WishListAdapter mAdapter;
    RecyclerView listView;
    TextView deleteBtn;
    List<WishListData> wishListData;
    SparseBooleanArray deleteResult = new SparseBooleanArray();

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

        deleteBtn = (TextView) findViewById(R.id.btn_delete);

        listView = (RecyclerView)  findViewById(R.id.rv_list);
        mAdapter = new WishListAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 3));



        setData();
        deleteBtn.setOnClickListener(new View.OnClickListener() { //삭제 버튼
            @Override
            public void onClick(View v) {

                SparseBooleanArray array = mAdapter.getCheckedItemPositions();
                List<String> select = new ArrayList<String>();



                select.clear();
                for (int index = 0; index < array.size(); index++) {
                    int position = array.keyAt(index);
                    if (array.get(position)) {
                        Toast.makeText(WishListActivity.this, "position : "+ mAdapter.getWishListdata(position).getPosting_number(), Toast.LENGTH_SHORT).show();
                        select.add(mAdapter.getWishListdata(position).getPosting_number());
                    }
                }
                Toast.makeText(WishListActivity.this, "delete바로전 ", Toast.LENGTH_SHORT).show();

                deleteWishList(select);
            }
        });
    }



    String general_number = "GM722";
    private void deleteWishList(List<String> select) {

        Toast.makeText(WishListActivity.this, "select 첫 원소 : +" + select.get(0), Toast.LENGTH_SHORT).show();

            /////여기다가 삭제 구현!
        NetworkManager.getInstance().deleteMyWishList(this, general_number, select, new NetworkManager.OnResultListener<WishListResult>() {
            @Override
            public void onSuccess(Request request, WishListResult result) {

                if (result.isSuccess == 1) {
                    Toast.makeText(WishListActivity.this, "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WishListActivity.this, "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(WishListActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setData() {
        NetworkManager.getInstance().getMyWishList(this, general_number, new NetworkManager.OnResultListener<List<WishListData>>() {
            @Override
            public void onSuccess(Request request, List<WishListData> result) {

                wishListData = result;
                mAdapter.addAll(result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(WishListActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
