package kr.co.homein.homeinproject.Estimate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 26..
 */
public class EstimateRegisterDialog extends DialogFragment
{
    Button exit;

    private Dialog.OnDismissListener _listener ;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.register_dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.register_dialog_Height);
        getDialog().getWindow().setLayout(width, height);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_view, container, false);

        exit = (Button) view.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (_listener == null) {
                } else {
                    _listener.onDismiss(getDialog());
                }
                dismiss();
            }
        }) ;
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.MyDIalogTheme);
    }

    public void setOnDismissListener( Dialog.OnDismissListener $listener ) {
        _listener = $listener ;
    }

//    public String getTag(){
//        return inputTag.getText().toString();
//    }
}


