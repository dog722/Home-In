package kr.co.homein.homeinproject.Company;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyItemData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyItemFragment extends Fragment {


    RecyclerView recyclerView;
    CompanyItemListAdapter mAdatper;
    Button edit;
    String CH_number;
    final static String CH_NUMBER = "CH_number";


    public CompanyItemFragment() {
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
        NetworkManager.getInstance().getCompanyItemList(getContext(), new NetworkManager.OnResultListener<List<CompanyItemData>>() {
            @Override
            public void onSuccess(Request request, List<CompanyItemData> result) {
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
