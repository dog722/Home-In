package kr.co.homein.homeinproject.HomeIn;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 29..
 */
public class NoInputDialog extends Dialog {

    private OnDismissListener _listener ;
    public NoInputDialog(Context context) {
        super(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_input_view);


        Button btn = (Button)findViewById(R.id.exit_btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss() ;
            }
        }) ;

    }

    public void setOnDismissListener( OnDismissListener $listener ) {
        _listener = $listener ;
    }



}
