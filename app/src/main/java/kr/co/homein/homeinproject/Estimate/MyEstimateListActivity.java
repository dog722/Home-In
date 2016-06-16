package kr.co.homein.homeinproject.Estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateListData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;


public class MyEstimateListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyEstimateListAdapter mAdatper;
    public final static String GENERAL_ESTIMATE_NUMBER ="general_estimate_number";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_estimate_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.rv_list3);

        mAdatper = new MyEstimateListAdapter();
        mAdatper.setOnItemClickListener(new MystimateListViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, EstimateListData myEstimateItemData) {
                Intent intent = new Intent(MyEstimateListActivity.this, MyEstimateDetailActivity.class); //내 견적서 문의 상세 페이지로 이동
                intent.putExtra(GENERAL_ESTIMATE_NUMBER, myEstimateItemData.getGeneral_estimate_number());
                Toast.makeText(MyEstimateListActivity.this, "general_estimate_number : "+ myEstimateItemData.getGeneral_estimate_number() , Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(mAdatper);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        setData();

    }


    private void setData() {


        NetworkManager.getInstance().getMyEstimateList(this, PropertyManager.getInstance().getGeneralNumber(),  new NetworkManager.OnResultListener<List<EstimateListData>>() {
            @Override
            public void onSuccess(Request request, List<EstimateListData> result) {
//                mAdapter.clear();
                mAdatper.addAll(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyEstimateListActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
