package kr.co.homein.homeinproject.Menu;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 25..
 */
public class InputAdressDialog extends Dialog {
    EditText inputAdress;
    Button exit , ok;

    private DialogInterface.OnDismissListener _listener ;
    public InputAdressDialog(Context context) {
        super(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_adress_view);
        inputAdress = (EditText) findViewById(R.id.input_adress);


        ///여기서 비밀번호 서로 비교해주는 알고리즘 추가하기.

        exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (_listener == null) {
                } else {
                    _listener.onDismiss(InputAdressDialog.this);
                }
                dismiss();
            }
        }) ;
    }

    public void setOnDismissListener( DialogInterface.OnDismissListener $listener ) {
        _listener = $listener ;
    }

//    public String getTag(){
//        return inputTag.getText().toString();
//    }
}

