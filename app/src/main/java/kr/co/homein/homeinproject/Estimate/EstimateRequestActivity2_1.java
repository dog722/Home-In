package kr.co.homein.homeinproject.Estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

public class  EstimateRequestActivity2_1 extends AppCompatActivity {


    GridView gridView;
//    ArrayAdapter<String>
    EstimateDetailData estimateDetailData;
    CategoryAdapter mAdapter;
    Intent intent;
    CategoryItem categoryItem;
    Button nextBtn;
    String resultKeyword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request_activity2_1);
        EstimateRequestActivity.at.add(this);

        intent = getIntent();
        estimateDetailData = (EstimateDetailData) intent.getSerializableExtra(EstimateRequestActivity.ESTIMATE_DATA);

        nextBtn = (Button) findViewById(R.id.next_btn);
        gridView  = (GridView) findViewById(R.id.gridView);
        mAdapter = new CategoryAdapter();
        gridView.setAdapter(mAdapter);
        gridView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            if (estimateDetailData.getEstimate_space().equals("주거공간")) {
                mAdapter.clear();
                mAdapter.addAll(category1);
                Toast.makeText(EstimateRequestActivity2_1.this, "주거공간!!", Toast.LENGTH_SHORT).show();

            } else if (estimateDetailData.getEstimate_space().equals("상업공간")) {
                mAdapter.clear();
                mAdapter.addAll(category2);
                Toast.makeText(EstimateRequestActivity2_1.this, "상업공!!", Toast.LENGTH_SHORT).show();

            } else if (estimateDetailData.getEstimate_space().equals("부분시공")) {
                mAdapter.clear();
                mAdapter.addAll(category3);
                Toast.makeText(EstimateRequestActivity2_1.this, "부분시공!!", Toast.LENGTH_SHORT).show();
            }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                categoryItem = (CategoryItem) mAdapter.getItem(position);
                resultKeyword = categoryItem.key;
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(resultKeyword != null) {
                        estimateDetailData.setEstimate_sub_space(resultKeyword);
                        Toast.makeText(EstimateRequestActivity2_1.this, "key 값 : " + resultKeyword, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EstimateRequestActivity2_1.this, EstimateRequestActivity3.class);
//                intent.putExtra(ESTIMATE_SUB_SPACE, estimate_sub_space);
                        intent.putExtra(EstimateRequestActivity.ESTIMATE_DATA, estimateDetailData);
                        startActivity(intent); //견적문의 2페이지로 이동
                    }
                    else{
                        Toast.makeText(EstimateRequestActivity2_1.this, "세부 공간을 선택하세요.", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }

    public static final CategoryItem[] category1 = {
//        new CategoryItem("a", R.mipmap.i)
            new CategoryItem("아파트", R.drawable.bt_2_1_on ,R.drawable.bt_2_1),
            new CategoryItem("주택", R.drawable.bt_2_2_on,R.drawable.bt_2_2),
            new CategoryItem("빌라", R.drawable.bt_2_3_on,R.drawable.bt_2_3),
            new CategoryItem("원룸", R.drawable.bt_2_4_on,R.drawable.bt_2_4),
            new CategoryItem("기타", R.drawable.bt_2_5_on,R.drawable.bt_2_5)

    };

    public static final CategoryItem[] category2 = {
//        new CategoryItem("a", R.mipmap.i)
            new CategoryItem("사무실", R.drawable.bt_3_1_on ,R.drawable.bt_3_1),
            new CategoryItem("상가,매장", R.drawable.bt_3_2_on ,R.drawable.bt_3_2),
            new CategoryItem("학원,교육",R.drawable.bt_3_3_on ,R.drawable.bt_3_3),
            new CategoryItem("학교,교육", R.drawable.bt_3_4_on ,R.drawable.bt_3_4),
            new CategoryItem("병원", R.drawable.bt_3_5_on ,R.drawable.bt_3_5),
            new CategoryItem("기타", R.drawable.bt_3_6_on,R.drawable.bt_3_6)
    };


    public static final CategoryItem[] category3 = {
//        new CategoryItem("a", R.mipmap.i)
            new CategoryItem("확장", R.drawable.bt_4_1_on,R.drawable.bt_4_1),
            new CategoryItem("도배, 장판",R.drawable.bt_4_2_on,R.drawable.bt_4_2),
            new CategoryItem("바닥",R.drawable.bt_4_3_on,R.drawable.bt_4_3),
            new CategoryItem("가구",R.drawable.bt_4_4_on,R.drawable.bt_4_4),
            new CategoryItem("기타", R.drawable.bt_4_5_on,R.drawable.bt_4_5),
    };

}
