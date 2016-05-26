package kr.co.homein.homeinproject.Estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.co.homein.homeinproject.R;

public class EstimateRequestActivity extends AppCompatActivity {


    Button nextBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request);

        nextBtn = (Button) findViewById(R.id.next_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EstimateRequestActivity.this,EstimateRequestActivity2.class)); //견적문의 2페이지로 이동
            }
        });

    }
}
