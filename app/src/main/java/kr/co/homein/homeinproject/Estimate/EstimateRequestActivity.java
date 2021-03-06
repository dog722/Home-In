package kr.co.homein.homeinproject.Estimate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

public class EstimateRequestActivity extends AppCompatActivity {

    Button nextBtn;
    RadioGroup groupView;

    final static  String  ESTIMATE_SPACE = "estimate_space";
    public static String ESTIMATE_DATA = "estimate_data";
    final static String OF_NUMBER = "office_number";

    String estimate_space;
    public static ArrayList<Activity> at = new ArrayList<Activity>();

    EstimateDetailData estimateDetailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request);
        at.add(this);

        estimateDetailData = new EstimateDetailData();

        Intent intent = getIntent();
        estimateDetailData.setOffice_number(intent.getStringExtra(OF_NUMBER));

        groupView = (RadioGroup) findViewById(R.id.radio_group);
        groupView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbtn_living) //주거공간
                {

                    estimateDetailData.setEstimate_space("주거공간");

                }else if(checkedId == R.id.rbtn_company) //상업공간
                {
                    estimateDetailData.setEstimate_space("상업공간");

                }else if(checkedId == R.id.r_btn_part){ //부분시공

                    estimateDetailData.setEstimate_space("부분시공");
                }
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
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
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                if(estimateDetailData.getEstimate_space() != null) {
                    Intent intent = new Intent(EstimateRequestActivity.this, EstimateRequestActivity2_1.class);
//                intent.putExtra(ESTIMATE_SPACE, estimate_space); //결과 다음창으로 전달
//                startActivity(intent); //견적문의 2페이지로 이동
                    intent.putExtra(ESTIMATE_DATA, estimateDetailData);
                    startActivity(intent);
                }else{
                    Toast.makeText(EstimateRequestActivity.this, "인테리어 공간을 선택하세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
