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
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.WishListData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class WishListActivity2 extends AppCompatActivity {

    WishListAdapter2 mAdapter;
    RecyclerView listView;
    TextView editBtn;
    List<WishListData> wishListData;
    final static int DELETE = 1;
    final static String PH_NUMBER = "PH_number";


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

        setData();

        listView.setLayoutManager(new GridLayoutManager(this, 3));
        editBtn = (TextView) findViewById(R.id.btn_edit);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(WishListActivity2.this, WishListActivity.class), DELETE);

            }
        });


        mAdapter.setOnItemClickListener(new WishListViewHolder2.OnItemClickListener() {

            @Override
            public void onItemClick(View view, WishListData wishListData) {


                Intent intent = new Intent(WishListActivity2.this, PeopleItemDetailActivity.class);
                intent.putExtra(PH_NUMBER , wishListData.getPosting_number());
                startActivity(new Intent(WishListActivity2.this, PeopleItemDetailActivity.class));
            }
        });


    }


    String general_number = "GM722";
    private void setData() {
        NetworkManager.getInstance().getMyWishList(this, general_number, new NetworkManager.OnResultListener<List<WishListData>>() {
            @Override
            public void onSuccess(Request request, List<WishListData> result) {
//                mAdapter.clear();
//                wishListData= result;
                mAdapter.addAll(result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(WishListActivity2.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
