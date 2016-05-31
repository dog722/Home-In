package kr.co.homein.homeinproject.Company;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import kr.co.homein.homeinproject.Estimate.EstimateRequestActivity;
import kr.co.homein.homeinproject.Maps.CompanyMapActivity;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyInfoData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyInfoFragment extends Fragment {

    FloatingActionButton fab;
    TextView companyId;
    TextView companyTitle;
    TextView companyScore;
    TextView companyAdress;
    TextView companyTel;
    TextView companyDomain;
    TextView comment;
    TextView requestBtn;
    TextView companyAddress2;
    CompanyInfoData companyInfoData;
    final static String OF_NUMBER = "office_number";
    String officeNumber;

    public CompanyInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        companyInfoData = new CompanyInfoData();
//        Bundle extra = getArguments();
        officeNumber = ((CompanyInfoActivity)getActivity()).getOfficeNumber();
        Toast.makeText(getContext(), "officeNumber : "+ officeNumber, Toast.LENGTH_SHORT).show();
        setData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_company_info, container, false);
        // Inflate the layout for this fragment
        fab = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getActivity(), CompanyMapActivity.class);
                startActivity(intent);
            }
        });

        companyId = (TextView) view.findViewById(R.id.company_id);
        companyTitle = (TextView) view.findViewById(R.id.company_title);
        companyScore = (TextView) view.findViewById(R.id.score);
        companyAdress = (TextView) view.findViewById(R.id.adress);
        companyAddress2 = (TextView) view.findViewById(R.id.adress2);
        companyTel = (TextView) view.findViewById(R.id.tel);
        companyDomain = (TextView) view.findViewById(R.id.domain);
        comment = (TextView) view.findViewById(R.id.comment);
        requestBtn = (TextView) view.findViewById(R.id.request);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EstimateRequestActivity.class); //포스팅 상세 페이지로 이동
                startActivity(intent);
            }
        });


        return view;
    }

    private void setData() {

        NetworkManager.getInstance().getCompanyInfo(this, officeNumber, new NetworkManager.OnResultListener<CompanyInfoData>() {
            @Override
            public void onSuccess(Request request, CompanyInfoData result) {
//                mAdapter.set(result);
                companyInfoData = result;

                companyId.setText(companyInfoData.getOffice_name());
                companyTitle.setText(companyInfoData.getOffice_sub_name());
                companyScore.setText(companyInfoData.getOffice_pick_count()+"");
                companyAdress.setText(companyInfoData.getOffice_address1());
                companyTel.setText(companyInfoData.getOffice_tel());
                companyDomain.setText(companyInfoData.getOffice_website());
                comment.setText(companyInfoData.getOffice_info_content());
                companyAddress2.setText(companyInfoData.getOffice_address2());

//                List<String> url = companyDetailItemData.getCH_picture();
//                Glide.with(imageView.getContext()).load(url.get(0)).into(imageView);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
