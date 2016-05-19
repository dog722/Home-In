package kr.co.homein.homeinproject.Company;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyItemData;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyInfoItemFragment extends Fragment {



    RecyclerView recyclerView;
    CompanyItemListAdapter mAdatper;


    public CompanyInfoItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdatper = new CompanyItemListAdapter();
        mAdatper.setOnItemClickListener(new CompanyItemViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, CompanyItemData companyItemData) {
                Intent intent = new Intent(getContext(), CompanyDetailItemActivity.class); //사용자 아이템 상세 페이지 이동
                startActivity(intent);
            }
        });
        setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_company_item, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list2);
        recyclerView.setAdapter(mAdatper);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        return view;
    }


    private void setData() {

        for( int i = 0 ; i < 10 ; i ++){
            CompanyItemData c = new CompanyItemData();
            c.setGoodCount("20" + i);
            mAdatper.add(c);
        }

    }
}
