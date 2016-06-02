package kr.co.homein.homeinproject.Estimate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.co.homein.homeinproject.R;

public class EstimateRequestActivity5 extends AppCompatActivity implements DialogInterface.OnDismissListener, View.OnClickListener {

    Button nextBtn;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request5);

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
        finish();
    }
}
