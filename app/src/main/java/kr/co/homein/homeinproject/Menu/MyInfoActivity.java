package kr.co.homein.homeinproject.Menu;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyInfoData;


public class MyInfoActivity extends AppCompatActivity implements OnDismissListener , OnClickListener {

    ImageView profileImg;
    TextView userId, email, editPW, userPhone, inputAdress;
    MyInfoData myInfoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);


        myInfoData = new MyInfoData();

        profileImg = (ImageView) findViewById(R.id.profile_img);
        userId = (TextView) findViewById(R.id.user_id);
        email= (TextView) findViewById(R.id.user_email);
        editPW= (TextView) findViewById(R.id.edit_pw);
        userPhone= (TextView) findViewById(R.id.user_phone_num);
        inputAdress = (TextView) findViewById(R.id.input_adress);

        editPW.setOnClickListener(this);
        inputAdress.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        
//        setData();
//        userId.setText(myInfoData.getUserId());
//        email.setText(myInfoData.getUserEmail());
//        userPhone.setText(myInfoData.getUserPhoneNum());

//        //비밀번호 변경
//        editPW.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                return false;
//            }
//        });
//
//
//        //거주지역 입력
//        inputAdress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                return false;
//            }
//        });





    }

//    private void setData() {
//        myInfoData.setUserId("ving.9");
//        myInfoData.setUserAdress("서울 특별시 신림동");
//        myInfoData.setUserEmail("mingu77777@gmail.com");
//        myInfoData.setUserPhoneNum("+82 10 2866 8263");
//        myInfoData.setUserPW("00000000");
//    }


    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    @Override
    public void onClick(View v) {

        if(v == editPW) {
            EditPassWordDialog dialog = new EditPassWordDialog(this);
            dialog.setOnDismissListener(this);
            dialog.show();
        }else if (v == inputAdress){
            InputAdressDialog dialog = new InputAdressDialog(this);
            dialog.setOnDismissListener(this);
            dialog.show();
        }
    }
}
