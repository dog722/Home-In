package kr.co.homein.homeinproject.Maps;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.co.homein.homeinproject.Company.CompanyInfoActivity;
import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 6. 13..
 */
public class CompanyInfoDialogFragment extends DialogFragment {
    private Dialog.OnDismissListener _listener ;

    TextView companyName;
    TextView companySubName;
    ImageView comapanyLogo;
    RelativeLayout relativeLayout;
    final static String OF_NUMBER = "office_number";
    String office_number;

    CurrentOffice cO;
    AroundOffice aO;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.map_dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.map_dialog_Height);
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setGravity(Gravity.CENTER | Gravity.BOTTOM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_company_info_view, container, false);

        companyName = (TextView) view.findViewById(R.id.company_name);
        companySubName = (TextView) view.findViewById(R.id.company_sub_name);
        comapanyLogo = (ImageView) view.findViewById(R.id.company_logo);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.company_info);

        if (cO != null) {
            setData(cO);
            relativeLayout.setVisibility(View.VISIBLE);

        }


        if (aO != null) {
            setData2(aO);
            relativeLayout.setVisibility(View.VISIBLE);

        }

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CompanyInfoActivity.class);
                intent.putExtra(OF_NUMBER, office_number);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }


    private void setData(CurrentOffice cO){



        if( cO.getOffice_picture().size() >0) {

            String path = cO.getOffice_picture().get(0);
            Glide.with(getActivity()).load(path).into(comapanyLogo);

        }
        office_number = cO.getOffice_number();
        companyName.setText(cO.getOffice_name());
        companySubName.setText(cO.getOffice_sub_name());

    }



    private void setData2(AroundOffice aO){



        if( aO.getOffice_picture().size() >0) {

            String path = aO.getOffice_picture().get(0);
            Glide.with(getActivity()).load(path).into(comapanyLogo);

        }
        office_number = aO.getOffice_number();
        companyName.setText(aO.getOffice_name());
        companySubName.setText(aO.getOffice_sub_name());

    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.MyDIalogTheme);
        Bundle b = getArguments();
        if (b != null) {
            cO = (CurrentOffice)b.getSerializable("arg1");
            aO = (AroundOffice)b.getSerializable("arg2");
        }
    }


    public void setOnDismissListener( Dialog.OnDismissListener $listener ) {
        _listener = $listener ;
    }

}
