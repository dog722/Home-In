package kr.co.homein.homeinproject.Estimate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.co.homein.homeinproject.R;

public class EstimateRequestActivity5 extends AppCompatActivity implements DialogInterface.OnDismissListener, View.OnClickListener {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request5);

        nextBtn = (Button) findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);
//        nextBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EstimateRequestActivity5.this, MyEstimateListActivity.class)); //견적문의 2페이지로 이동
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        EstimateRegisterDialog dialog = new EstimateRegisterDialog(this);
        dialog.setOnDismissListener(this);
        dialog.show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        startActivity(new Intent(EstimateRequestActivity5.this, MyEstimateListActivity.class)); //견적문의 2페이지로 이동
    }
}
