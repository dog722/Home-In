package kr.co.homein.homeinproject.Estimate;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 26..
 */
public class EstimateRegisterDialog extends Dialog {
    Button exit;

    private OnDismissListener _listener ;
    public EstimateRegisterDialog(Context context) {
        super(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        ///여기서 비밀번호 서로 비교해주는 알고리즘 추가하기.



        exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (_listener == null) {
                } else {
                    _listener.onDismiss(EstimateRegisterDialog.this);
                }
                dismiss();
            }
        }) ;

    }

    public void setOnDismissListener( OnDismissListener $listener ) {
        _listener = $listener ;
    }

//    public String getTag(){
//        return inputTag.getText().toString();
//    }
}


