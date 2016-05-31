package kr.co.homein.homeinproject.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class WishListActivity extends AppCompatActivity {

    WishListAdapter mAdapter;
    RecyclerView listView;
    TextView deleteBtn;
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


        deleteBtn = (TextView) findViewById(R.id.btn_delete);

        listView = (RecyclerView)  findViewById(R.id.rv_list);
        mAdapter = new WishListAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 3));

        deleteBtn.setOnClickListener(new View.OnClickListener() { //삭제 버튼
            @Override
            public void onClick(View v) {
                finish(); //여기서 선택된 아이템의 포지션 서버에 넘겨주기.
            }
        });

        setData();

    }


    String general_number = "GM722";
    private void setData() {
        NetworkManager.getInstance().getMyWishList(this, general_number, new NetworkManager.OnResultListener<List<WishListData>>() {
            @Override
            public void onSuccess(Request request, List<WishListData> result) {
//                mAdapter.clear();
                mAdapter.addAll(result);
                Toast.makeText(WishListActivity.this, "server connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(WishListActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
