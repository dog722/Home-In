package kr.co.homein.homeinproject.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.homein.homeinproject.HomeIn.PeoPleItemListViewHolder;
import kr.co.homein.homeinproject.HomeIn.PeopleItemDetailActivity;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PeopleItemData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPeopleItemListFragment extends Fragment {


    RecyclerView recyclerView;
    PeopleItemListAdapter mAdatper;


    public SearchPeopleItemListFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdatper = new PeopleItemListAdapter();
        mAdatper.setOnItemClickListener(new PeoPleItemListViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, PeopleItemData peopleItem) {
                Intent intent = new Intent(getContext(), PeopleItemDetailActivity.class); //사용자 아이템 상세 페이지 이동
                startActivity(intent);
            }
        });
        setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search_people_item_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list2);
        recyclerView.setAdapter(mAdatper);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private void setData() { ///여기서 다르게 검색해주기. 키워드 받아서 서버에서 값 받아오기.

        for( int i = 0 ; i < 10 ; i ++){
            PeopleItemData p = new PeopleItemData();
            p.setPH_pick(20 + i);
            mAdatper.add(p);
            p.getPH_tag().add("태그1");
            p.getPH_tag().add("태그2");
        }

    }
}