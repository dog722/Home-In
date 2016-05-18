package kr.co.homein.homeinproject.HomeIn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import kr.co.homein.homeinproject.data.CommentData;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;

import kr.co.homein.homeinproject.R;

public class PeopleItemDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PEOPLE_ITEM_CODE = "people_item_code";

    RecyclerView listView;
    PeopleItemDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_item_detail);

        listView = (RecyclerView) findViewById(R.id.rv_list);
        mAdapter = new PeopleItemDetailAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        setData();
    }

    private void setData() {

        for(int i = 0 ; i< 5 ; i++) {
            CommentData commentData = new CommentData();
            commentData.userId ="eunbi" + i;
            commentData.Comment = "우와 신기해요!!! " + i;
            mAdapter.addComment(commentData);
        }

        PeopleDetailItemData peopleDetailItemData = new PeopleDetailItemData();
        peopleDetailItemData.comment = "작은 내 방 모던하고 따뜻하게 :=)";
        peopleDetailItemData.user_id="eunbi722";
        peopleDetailItemData.tag.add("#한셈 인테리어");

        mAdapter.addHeader(peopleDetailItemData);
    }
}
