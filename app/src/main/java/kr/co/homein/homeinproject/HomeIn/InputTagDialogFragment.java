package kr.co.homein.homeinproject.HomeIn;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 5. 20..
 */
public class InputTagDialogFragment extends DialogFragment {


    EditText inputTag;
    private Dialog.OnDismissListener _listener ;

    public String getInputTag() {
        return inputTag.getText().toString();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.inputtag_dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.inputtag_dialog_Height);
        getDialog().getWindow().setLayout(width, height);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_tag_dialog_view, container, false);

        inputTag = (EditText) view.findViewById(R.id.input_tag);
        ImageButton btn = (ImageButton)view.findViewById(R.id.input_btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //키보드 감추기
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputTag.getWindowToken(), 0);

                if (_listener == null) {
                } else {
                    _listener.onDismiss(getDialog());
                }
                inputTag.setText("");
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




}
