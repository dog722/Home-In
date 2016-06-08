package kr.co.homein.homeinproject.Estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

public class EstimateRequestActivity2 extends AppCompatActivity {

    Button nextBtn;
    RadioGroup radioGroup;
    Intent intent;
    EstimateDetailData estimateDetailData;

    final static String ESTIMATE_SUB_SPACE = "estimate_sub_space";
    String estimate_sub_space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_estimate_request2);



        intent = getIntent();
        estimateDetailData = (EstimateDetailData) intent.getSerializableExtra(EstimateRequestActivity.ESTIMATE_DATA);


        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbtn_apart){ //아파트
                    estimateDetailData.setEstimate_sub_space("아파트");
//                    estimate_sub_space = "아파트";
                 }else if(checkedId == R.id.rbtn_house){ //주택
                    estimateDetailData.setEstimate_sub_space("주택");
//                    estimate_sub_space = "주택";
                }else if(checkedId == R.id.rbtn_bil){ //빌라
                    estimateDetailData.setEstimate_sub_space("빌라");
//                    estimate_sub_space = "빌라";
                }else if(checkedId == R.id.rbtn_one){ //원룸
                    estimateDetailData.setEstimate_sub_space("원룸");
//                    estimate_sub_space = "원룸";
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        nextBtn = (Button) findViewById(R.id.next_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EstimateRequestActivity2.this, EstimateRequestActivity3.class);
//                intent.putExtra(ESTIMATE_SUB_SPACE, estimate_sub_space);
                intent.putExtra(EstimateRequestActivity.ESTIMATE_DATA , estimateDetailData);
                startActivity(intent); //견적문의 2페이지로 이동
            }
        });
    }
}
