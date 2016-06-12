package kr.co.homein.homeinproject.HomeIn;

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
 * Created by seoeunbi on 2016. 5. 29..
 */
public class NoInputDialog extends DialogFragment {

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
        View view = inflater.inflate(R.layout.no_input_view, container, false);

        Button btn = (Button)view.findViewById(R.id.exit);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss() ;
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
