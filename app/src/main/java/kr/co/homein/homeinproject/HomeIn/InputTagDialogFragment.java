package kr.co.homein.homeinproject.HomeIn;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 20..
 */
public class InputTagDialogFragment extends Dialog {


    EditText inputTag;
    private OnDismissListener _listener ;
    public InputTagDialogFragment(Context context) {
        super(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_tag_dialog_view);
        setTitle("태그를 입력하세요");
        inputTag = (EditText) findViewById(R.id.input_tag);


        Button btn = (Button)findViewById(R.id.input_btn);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //키보드 감추기
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputTag.getWindowToken(), 0);

                if (_listener == null) {
                } else {
                    _listener.onDismiss(InputTagDialogFragment.this);
                }
                dismiss() ;
            }
        }) ;

    }

    public void setOnDismissListener( OnDismissListener $listener ) {
        _listener = $listener ;
    }

    public String getTag(){
        return inputTag.getText().toString();
    }




}
