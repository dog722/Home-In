package kr.co.homein.homeinproject.Estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyEstimateItemData;


public class MyEstimateListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyEstimateListAdapter mAdatper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_estimate_list);

        recyclerView = (RecyclerView) findViewById(R.id.rv_list3);

        mAdatper = new MyEstimateListAdapter();
        mAdatper.setOnItemClickListener(new MystimateListViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, MyEstimateItemData myEstimateItemData) {
                Intent intent = new Intent(MyEstimateListActivity.this, MyEstimateDetailActivity.class); //내 견적서 문의 상세 페이지로 이동
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

        for( int i = 0 ; i < 10 ; i ++){
            MyEstimateItemData me = new MyEstimateItemData();
            me.setEstimateTitle("사진같이 러블리 핑크 스타일로 시공할 수 있나요?");
            mAdatper.add(me);
        }
    }
}
