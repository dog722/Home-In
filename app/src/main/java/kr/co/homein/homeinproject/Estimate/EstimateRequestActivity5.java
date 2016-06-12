package kr.co.homein.homeinproject.Estimate;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;
import kr.co.homein.homeinproject.data.EstimateWriteResult;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class EstimateRequestActivity5 extends AppCompatActivity implements DialogInterface.OnDismissListener, View.OnClickListener {

    Button nextBtn;
    EditText input_name;
    EditText input_email;
    EditText input_call;
    EditText input_comment;
    EstimateDetailData estimateDetailData;
    List <File> uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request5);
//        EstimateRequestActivity.at.add(this);


        uploadImage = new ArrayList<File>();

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


        input_name = (EditText) findViewById(R.id.input_name);
        input_name.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input_name.setInputType(InputType.TYPE_CLASS_TEXT);
        input_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_NEXT:
                        //키보드 내리기

                        input_email.requestFocus();
                        input_email.setCursorVisible(true);

                }
                return true;
            }
        });




        input_email = (EditText) findViewById(R.id.input_email);
        input_email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        input_email.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input_email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_NEXT:
                        //키보드 내리기
                        input_call.requestFocus();
                        input_call.setCursorVisible(true);
                }
                return true;
            }
        });

        input_call = (EditText) findViewById(R.id.input_call);
        input_call.setInputType(InputType.TYPE_CLASS_PHONE);

        input_call.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input_call.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_NEXT:
                        input_comment.requestFocus();
                        input_comment.setCursorVisible(true);

                }
                return true;
            }
        });

        input_comment = (EditText) findViewById(R.id.input_content);
        input_comment.setImeOptions(EditorInfo.IME_ACTION_DONE);
        input_comment.setInputType(InputType.TYPE_CLASS_TEXT);
        input_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        //키보드 내리기
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input_comment.getWindowToken(), 0);

                }
                return true;
            }
        });






        if(estimateDetailData.getInterior_picture() != null) {
            for (int i = 0; i < estimateDetailData.getInterior_picture().size(); i++) {
                uploadImage.add(new File(estimateDetailData.getInterior_picture().get(i)));
            }
        }

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
        Toast.makeText(EstimateRequestActivity5.this, "general_number : " + estimateDetailData.getGeneral_number(), Toast.LENGTH_SHORT).show();
        senEstimateInfo();
        EstimateRegisterDialog dialog = new EstimateRegisterDialog();
        dialog.setOnDismissListener(this);

        dialog.show(getSupportFragmentManager(), "dialog");

    }

    @Override
    public void onDismiss(DialogInterface dialog) {

        for (int i = 0; i < EstimateRequestActivity.at.size(); i++) {
            EstimateRequestActivity.at.get(i).finish();  //List가 Static 이므로, Class명.변수명.get으로 접근
        }

        Intent intent = new Intent(EstimateRequestActivity5.this, MyEstimateListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(new Intent(EstimateRequestActivity5.this, MyEstimateListActivity.class)); //견적문의 2페이지로 이동
        finish();
    }


    private void senEstimateInfo() {

        estimateDetailData.setGeneral_real_name(input_name.getText().toString());
        estimateDetailData.setGeneral_tel(input_call.getText().toString());
        estimateDetailData.setGeneral_email(input_email.getText().toString());
        estimateDetailData.setInterior_info_content(input_comment.getText().toString());


        NetworkManager.getInstance().sendEstimateWrite(this, PropertyManager.getInstance().getGeneralNumber(), estimateDetailData.getOffice_number(), estimateDetailData.getEstimate_space(),
                estimateDetailData.getEstimate_sub_space(), estimateDetailData.getEstimate_size(), uploadImage,
                estimateDetailData.getGeneral_real_name(), estimateDetailData.getGeneral_email(), estimateDetailData.getGeneral_tel(),estimateDetailData.getInterior_info_content(), new NetworkManager.OnResultListener<EstimateWriteResult>() {
                    @Override
                    public void onSuccess(Request request, EstimateWriteResult result) {
//                mAdapter.set(result);

                        if (result.isSuccess == 1) {
                            Toast.makeText(EstimateRequestActivity5.this, "견적서가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EstimateRequestActivity5.this, "견적서가 등록되지 않았습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(Request request, IOException exception) {
                        Toast.makeText(EstimateRequestActivity5.this, "server disconnected", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
