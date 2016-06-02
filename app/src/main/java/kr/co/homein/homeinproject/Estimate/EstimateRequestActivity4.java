package kr.co.homein.homeinproject.Estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

public class EstimateRequestActivity4 extends AppCompatActivity {

    Button nextBtn;
    final static String ESTIMATE_DATA = "estimate_data";
    EstimateDetailData estimateDetailData;
    ImageView uploadImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request4);

        Intent intent = getIntent();
        estimateDetailData = (EstimateDetailData) intent.getSerializableExtra(ESTIMATE_DATA);


        //사진 업로드 하는 버튼
        uploadImg = (ImageView) findViewById(R.id.plus);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                Intent intent =new Intent(EstimateRequestActivity4.this, EstimateRequestActivity5.class);
                intent.putExtra(ESTIMATE_DATA, estimateDetailData);
                startActivity(intent); //견적문의 2페이지로 이동
            }
        });
    }

}
