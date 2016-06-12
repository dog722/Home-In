package kr.co.homein.homeinproject.Posting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PostingListData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostingFragment extends Fragment {
    RecyclerView recyclerView;
    PostingAdapter mAdatper;

    public PostingFragment() {
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
        setData();
    }



    @Override
    public void onResume() {
        super.onResume();
        recyclerView.scrollToPosition(0);
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

//    private void setData() {
//
//        for( int i = 0 ; i < 10 ; i ++){
//            PostingItemData p = new PostingItemData();
//            p.setPost_name("눈 감고 할 수 있는 셀프 인테리어 10가지"+ i);
////            p.set("2" + i);
//            mAdatper.add(p);
//        }
//    }


    private void setData() {
        NetworkManager.getInstance().getPostingList(getContext(), new NetworkManager.OnResultListener<List<PostingListData>>() {
            @Override
            public void onSuccess(Request request, List<PostingListData> result) {
//                mAdapter.clear();
                mAdatper.addAll(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
