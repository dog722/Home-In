package kr.co.homein.homeinproject.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.homein.homeinproject.Posting.PostingAdapter;
import kr.co.homein.homeinproject.Posting.PostingDetailActivity;
import kr.co.homein.homeinproject.Posting.PostingViewHolder;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PostingItemData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPostingFragment extends Fragment {


    RecyclerView recyclerView;
    PostingAdapter mAdatper;

    public SearchPostingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdatper = new PostingAdapter();
        mAdatper.setOnItemClickListener(new PostingViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, PostingItemData postingItemData) {
                Intent intent = new Intent(getContext(), PostingDetailActivity.class); //포스팅 상세 페이지로 이동
                startActivity(intent);
            }
        });
        setData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posting, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list2);
        recyclerView.setAdapter(mAdatper);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        return view;
    }

    private void setData() { //키워드 넘겨서 서버에서 값 받아오기

        for( int i = 0 ; i < 10 ; i ++){
            PostingItemData p = new PostingItemData();
            p.setPostingTitle("눈 감고 할 수 있는 셀프 인테리어 10가지"+ i);
            p.setGoocScore("2" + i);
            mAdatper.add(p);
        }
    }


}
