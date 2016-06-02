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

import kr.co.homein.homeinproject.Company.CompanyDetailItemActivity;
import kr.co.homein.homeinproject.Company.CompanyItemListAdapter;
import kr.co.homein.homeinproject.Company.CompanyItemViewHolder;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyItemData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchCompanyItemListFragment extends Fragment implements SearchResultActivity.OnNotifyDataUpdateListener {



    RecyclerView recyclerView;
    CompanyItemListAdapter mAdatper;
    String CH_number;
    final static String CH_NUMBER = "CH_number";

    public SearchCompanyItemListFragment() {
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
                intent.putExtra(CH_NUMBER, companyItemData.getCH_number());
                startActivity(intent);
            }
        });

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

    void updateData() {
        List<CompanyItemData> list = ((SearchResultActivity)getActivity()).getCompanyItemData();
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