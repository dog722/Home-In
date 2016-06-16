package kr.co.homein.homeinproject.Estimate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

public class EstimateRequestActivity3 extends AppCompatActivity {

    Button nextBtn;
    EditText edit;
    ImageView plus;
    ImageView img;
    EstimateDetailData estimateDetailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request3);
        EstimateRequestActivity.at.add(this);

        Intent intent = getIntent();
        estimateDetailData = (EstimateDetailData) intent.getSerializableExtra(EstimateRequestActivity.ESTIMATE_DATA);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        edit = (EditText) findViewById(R.id.input_size);
        edit.requestFocus();
        edit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edit.setInputType(InputType.TYPE_CLASS_NUMBER);


        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        edit.setText(edit.getText().toString() + "평");

//                        Toast.makeText(getApplicationContext(), "완료", Toast.LENGTH_LONG).show();
                        InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                        //여기서 검색 키워드 서버에 보내주기
                        break;
                    default:
//                        Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_LONG).show();
                        return false;
                }
                return true;
            }
        });


        plus = (ImageView) findViewById(R.id.plus);
        img = (ImageView) findViewById(R.id.img);

        edit.setVisibility(View.GONE);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);
                edit.requestFocus();
                edit.setFocusable(true);
//                //키보드 보이게 하는 부분
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

            }
        });



        nextBtn = (Button) findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                estimateDetailData.setEstimate_size(edit.getText().toString());
                Intent intent = new Intent(EstimateRequestActivity3.this, EstimateRequestActivity4.class);
                intent.putExtra(EstimateRequestActivity.ESTIMATE_DATA, estimateDetailData);
                startActivity(intent); //견적문의 2페이지로 이동
            }
        });
    }
}
