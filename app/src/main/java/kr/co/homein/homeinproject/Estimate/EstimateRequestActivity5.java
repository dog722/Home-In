package kr.co.homein.homeinproject.Estimate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


        uploadImage = new ArrayList<File>();

        Intent intent = getIntent();
        estimateDetailData = (EstimateDetailData) intent.getSerializableExtra(EstimateRequestActivity.ESTIMATE_DATA);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        input_name = (EditText) findViewById(R.id.input_name);
        input_call = (EditText) findViewById(R.id.input_call);
        input_comment = (EditText) findViewById(R.id.input_content);
        input_email = (EditText) findViewById(R.id.input_email);


        estimateDetailData.setGeneral_real_name(input_name.getText().toString());
        estimateDetailData.setGeneral_tel(input_call.getText().toString());
        estimateDetailData.setGeneral_email(input_email.getText().toString());
        estimateDetailData.setInterior_info_content(input_comment.getText().toString());

        estimateDetailData.setGeneral_number("GM722");

        for(int i = 0 ; i< estimateDetailData.getInterior_picture().size() ; i++){
            uploadImage.add(new File(estimateDetailData.getInterior_picture().get(i)));
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
        sendImage();
        EstimateRegisterDialog dialog = new EstimateRegisterDialog(this);
        dialog.setOnDismissListener(this);
        dialog.show();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        startActivity(new Intent(EstimateRequestActivity5.this, MyEstimateListActivity.class)); //견적문의 2페이지로 이동
        finish();
    }


    private void sendImage() {
        NetworkManager.getInstance().sendEstimateWrite(this, estimateDetailData.getGeneral_number(), estimateDetailData.getOffice_number(), estimateDetailData.getEstimate_space(),
                estimateDetailData.getEstimate_sub_space(), estimateDetailData.getEstimate_size(), uploadImage,
                estimateDetailData.getGeneral_real_name(), estimateDetailData.getGeneral_email(), estimateDetailData.getGeneral_tel(),estimateDetailData.getInterior_info_content(), new NetworkManager.OnResultListener<EstimateWriteResult>() {
                    @Override
                    public void onSuccess(Request request, EstimateWriteResult result) {
//                mAdapter.set(result);

                        if (result.isSuccess == 1) {
                            Toast.makeText(EstimateRequestActivity5.this, "사진이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EstimateRequestActivity5.this, "사진이 등록되지 않았습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(Request request, IOException exception) {
                        Toast.makeText(EstimateRequestActivity5.this, "server disconnected", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
