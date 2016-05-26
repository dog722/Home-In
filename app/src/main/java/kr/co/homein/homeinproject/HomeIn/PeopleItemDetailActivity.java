package kr.co.homein.homeinproject.HomeIn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CommentData;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;

public class PeopleItemDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PEOPLE_ITEM_CODE = "people_item_code";

    RecyclerView listView;
    PeopleItemDetailAdapter mAdapter;
    ImageButton backKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_item_detail);

        listView = (RecyclerView) findViewById(R.id.rv_list);
        mAdapter = new PeopleItemDetailAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

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

    private void setData() {


        PeopleDetailItemData peopleDetailItemData = new PeopleDetailItemData();
        peopleDetailItemData.PH_content = "작은 내 방 모던하고 따뜻하게 :=)";
        peopleDetailItemData.general_id="eunbi722";
        for(int i = 0 ; i< 5 ; i++) {
            CommentData commentData = new CommentData();
            commentData.member_name ="eunbi" + i;
            commentData.comment_content = "우와 신기해요!!! " + i;
//            mAdapter.addComment(commentData);
            peopleDetailItemData.getPH_comment().add(commentData);
        }
//        peopleDetailItemData.getPH_comment().get(0).comment_content="너무 이뻐요!!^^";
        peopleDetailItemData.getPH_tag().add("#한셈 인테리어");

        mAdapter.addHeader(peopleDetailItemData);
    }
}
