package kr.co.homein.homeinproject.HomeIn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.InputCommentResult;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class PeopleItemDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PEOPLE_ITEM_CODE = "people_item_code";

    RecyclerView listView;
    PeopleItemDetailAdapter mAdapter;
    ImageButton backKey;
    Intent intent;
    String PH_number;
    EditText input_comment;
    Button input_btn;
    PeopleDetailItemData peopleDetailItemData;
    final static String PH_NUMBER = "PH_number";
    String comment_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_item_detail);

        listView = (RecyclerView) findViewById(R.id.rv_list);
        mAdapter = new PeopleItemDetailAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        intent = getIntent();
        PH_number =  intent.getStringExtra(PH_NUMBER);

        //댓글 입력 창
        input_comment = (EditText) findViewById(R.id.input_comment);
        //댓글 입력 버튼
        input_btn = (Button) findViewById(R.id.input_btn);
        input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input_comment.getWindowToken(), 0);
                comment_content = input_comment.getText().toString();
                addComment();
                listView.scrollToPosition(0);
                setData();
            }
        });


//        Toast.makeText(PeopleItemDetailActivity.this, PH_number + "", Toast.LENGTH_SHORT).show();

        backKey = (ImageButton) findViewById(R.id.back_key);
        backKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PeopleItemDetailActivity.this, MainActivity.class); //홈으로 가기
//                startActivity(intent);
                finish();
            }
        });

        setData();
    }
//
//    private void setData() {
//
//
//        PeopleDetailItemData peopleDetailItemData = new PeopleDetailItemData();
//        peopleDetailItemData.PH_content = "작은 내 방 모던하고 따뜻하게 :=)";
//        peopleDetailItemData.general_id="eunbi722";
//        for(int i = 0 ; i< 5 ; i++) {
//            CommentData commentData = new CommentData();
//            commentData.member_name ="eunbi" + i;
//            commentData.comment_content = "우와 신기해요!!! " + i;
////            mAdapter.addComment(commentData);
//            peopleDetailItemData.getPH_comment().set(commentData);
//        }
////        peopleDetailItemData.getPH_comment().get(0).comment_content="너무 이뻐요!!^^";
//        peopleDetailItemData.getPH_tag().set("#한셈 인테리어");
//        mAdapter.addHeader(peopleDetailItemData);
//    }


    private void setData() {

        NetworkManager.getInstance().getPeopleItemDetail(this, PH_number, new NetworkManager.OnResultListener<PeopleDetailItemData>() {
            @Override
            public void onSuccess(Request request, PeopleDetailItemData result) {
                peopleDetailItemData = result;
                mAdapter.set(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
//                Toast.makeText(PeopleItemDetailActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addComment(){
        NetworkManager.getInstance().addComment(this, PH_number, comment_content , PropertyManager.getInstance().getGeneralNumber(), new NetworkManager.OnResultListener<InputCommentResult>() {
            @Override
            public void onSuccess(Request request, InputCommentResult result) {

                if (result.isSuccess == 0) {
                    Toast.makeText(PeopleItemDetailActivity.this, "댓글 입력에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PeopleItemDetailActivity.this, "댓글 입력에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                    input_comment.setText("");

                }
            }

            @Override
            public void onFail(Request request, IOException exception) {
//                Toast.makeText(PeopleItemDetailActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}






