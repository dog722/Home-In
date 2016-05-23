package kr.co.homein.homeinproject.Company;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.co.homein.homeinproject.Maps.CompanyMapActivity;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyInfoData;

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
    CompanyInfoData companyInfoData;

    public CompanyInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        companyInfoData = new CompanyInfoData();

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
        companyTel = (TextView) view.findViewById(R.id.tel);
        companyDomain = (TextView) view.findViewById(R.id.domain);
        comment = (TextView) view.findViewById(R.id.comment);


        companyId.setText(companyInfoData.getCompanyId());
        companyTitle.setText(companyInfoData.getCompanyTitle());
        companyScore.setText(companyInfoData.getCompanyScore());
        companyAdress.setText(companyInfoData.getCompanyAdress());
        companyTel.setText(companyInfoData.getCompanyTel());
        companyDomain.setText(companyInfoData.getCompanyDomain());
        comment.setText(companyInfoData.getComment());

        return view;
    }

    private void setData() {
        companyInfoData.setCompanyId("한셈인테리어");
        companyInfoData.setCompanyTitle("논현홈아이엔티키친프라자점");
        companyInfoData.setCompanyScore("472");
        companyInfoData.setCompanyAdress("서울특별시 서초구 방배중앙로 107-1");
        companyInfoData.setCompanyTel("02 596 8213");
        companyInfoData.setCompanyDomain("http://www.hanssem.com/");
        companyInfoData.setComment("지난 30년간 우리나라 주거환경의 변화를 주도해 온 한샘은 부엌을 비롯한 실내 공간의 가구와 기기," +
                "소품,조명,패브릭 등을 제공하는 토탈 홈 인테리어 기업입니다.");

    }
}
