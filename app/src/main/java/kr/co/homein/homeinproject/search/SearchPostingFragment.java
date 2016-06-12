package kr.co.homein.homeinproject.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.co.homein.homeinproject.Posting.PostingAdapter;
import kr.co.homein.homeinproject.Posting.PostingDetailActivity;
import kr.co.homein.homeinproject.Posting.PostingViewHolder;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PostingListData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPostingFragment extends Fragment implements SearchResultActivity.OnNotifyDataUpdateListener {


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
            public void onItemClick(View view, PostingListData postingItemData) {
                Intent intent = new Intent(getContext(), PostingDetailActivity.class); //포스팅 상세 페이지로 이동
                startActivity(intent);
            }
        });
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


    void updateData() {
        List<PostingListData> list = ((SearchResultActivity)getActivity()).getPostingItemData();
        if (list != null) {
            // ...
            mAdatper.clear();
            mAdatper.addAll(list);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
        ((SearchResultActivity)getActivity()).addOnNotifyDataUpdateListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((SearchResultActivity)getActivity()).removeNotifyDataUpdateListener(this);
    }

    @Override
    public void onNotifyDataUpdate() {
        updateData();
    }


}
