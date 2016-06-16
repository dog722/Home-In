package kr.co.homein.homeinproject.Estimate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;
import kr.co.homein.homeinproject.data.InputCommentResult;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class MyEstimateDetailActivity extends AppCompatActivity {

    RecyclerView listView;
    MyEstimateDetailAdapter mAdapter;
    Intent intent;
    EditText input_comment;
    ImageButton input_btn;
    EstimateDetailData estimateDetailData;
    String comment_content;
    String general_estimate_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_estimate_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        intent = getIntent();
        general_estimate_number = intent.getStringExtra(MyEstimateListActivity.GENERAL_ESTIMATE_NUMBER);


        listView = (RecyclerView) findViewById(R.id.rvlist);
        mAdapter = new MyEstimateDetailAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        //intent받기
        input_comment = (EditText)  findViewById(R.id.input_comment);
        input_btn = (ImageButton) findViewById(R.id.upload_btn);
        input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input_comment.getWindowToken(), 0);
                comment_content = input_comment.getText().toString();
                addComment();
                setData();
                listView.scrollToPosition(0);
            }
        });


        setData();


    }


    String general_number = "GM722";
    public void addComment() {
        // String posting_number, String comment_content, String member_number,
        NetworkManager.getInstance().addEstimateComment(this, general_estimate_number, comment_content, PropertyManager.getInstance().getGeneralNumber(), new NetworkManager.OnResultListener<InputCommentResult>() {
            @Override
            public void onSuccess(Request request, InputCommentResult result) {

                if (result.isSuccess == 0) {
                    Toast.makeText(MyEstimateDetailActivity.this, "댓글 입력에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MyEstimateDetailActivity.this, "댓글 입력에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                    input_comment.setText("");
                    setData();

                }
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyEstimateDetailActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void setData(){
        NetworkManager.getInstance().getEstimateDetailItem(this, general_estimate_number,  PropertyManager.getInstance().getGeneralNumber(),  new NetworkManager.OnResultListener<EstimateDetailData>() {
            @Override
            public void onSuccess(Request request, EstimateDetailData result) {
                estimateDetailData = result;
                mAdapter.set(result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyEstimateDetailActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
