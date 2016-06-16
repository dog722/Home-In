package kr.co.homein.homeinproject.Menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 25..
 */
public class InputAdressDialog extends DialogFragment {
    EditText inputAdress;
    Button exit , ok;

    private DialogInterface.OnDismissListener _listener ;




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.input_add_width);
        int height = getResources().getDimensionPixelSize(R.dimen.input_add_height);
        getDialog().getWindow().setLayout(width, height);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_adress_view, container, false);

        inputAdress = (EditText) view.findViewById(R.id.input_adress);


        ///여기서 비밀번호 서로 비교해주는 알고리즘 추가하기.

        exit = (Button)view.findViewById(R.id.exit);
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

