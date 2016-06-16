package kr.co.homein.homeinproject.Menu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.ReceiveResultData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

/**
 * Created by seoeunbi on 2016. 5. 25..
 */
public class EditPassWordDialog extends Dialog {

    EditText inputCurrent, inputNewPw1 ,inputNewPw2;
    Button exit , ok;
    String currentPW, newPW1, newPW2;
    int isSuccess;

    private OnDismissListener _listener ;
    public EditPassWordDialog(Context context) {
        super(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pw_view);
        inputCurrent = (EditText) findViewById(R.id.input_adress);
        inputNewPw1 = (EditText) findViewById(R.id.new_pw);
        inputNewPw2 = (EditText) findViewById(R.id.new_pw2);



        ///여기서 비밀번호 서로 비교해주는 알고리즘 추가하기.

        exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (_listener == null) {
                } else {
                    _listener.onDismiss(EditPassWordDialog.this);
                }
                dismiss();
            }
        }) ;


        ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (_listener == null) {
                } else {
                    _listener.onDismiss(EditPassWordDialog.this);
                }
                updatePW();
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




    public void updatePW(){

        currentPW= inputCurrent.getText().toString();
        newPW1 = inputNewPw1.getText().toString();
        newPW2 = inputNewPw2.getText().toString();

        NetworkManager.getInstance().updatePassword(this, PropertyManager.getInstance().getGeneralNumber(),currentPW, newPW1, newPW2, new NetworkManager.OnResultListener<ReceiveResultData>() {
            @Override
            public void onSuccess(Request request, ReceiveResultData result) {
//                mAdapter.clear();
//                wishListData= result;
                if(result.isResult == 1 ) {
                    Toast.makeText(getContext(), "비밀번호가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "비밀번호 수정에 실패하였습니다..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
