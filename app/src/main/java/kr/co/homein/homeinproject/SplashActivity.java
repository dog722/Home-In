package kr.co.homein.homeinproject;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import kr.co.homein.homeinproject.Login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    Handler mHandler = new Handler(Looper.getMainLooper());

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                doRealStart();
//            }
//        };

        goLoginActivity();

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() { //로그인 되어 있지 않을 때
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 2000);
    }

    private void doRealStart() {
//        startSplash();
        goLoginActivity();
    }


    private void goMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Dialog dialog = apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }


    private void goLoginActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }


//    private void startSplash() {
//        String email = PropertyManager.getInstance().getEmail();
//        if (!TextUtils.isEmpty(email)) {
//            String password = PropertyManager.getInstance().getPassword();
//            NetworkManager.getInstance().signin(this, email, password, new NetworkManager.OnResultListener<MyInfoData>() {
//                @Override
//                public void onSuccess(Request request, MyInfoData result) {
//                    if (result.getGeneral_login_yn() == 1) { //로그인이 됬으면
//                        PropertyManager.getInstance().setLogin(true);
//                        PropertyManager.getInstance().setUser(result);
//                        goMainActivity();
//                    }
//                    else{
//                        goMainActivity();
//                    }
//                }
//
//                @Override
//                public void onFail(Request request, IOException exception) {
//                    Toast.makeText(SplashActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
//                    goLoginActivity();
//                }
//            });
//        }
//    }


    /*
            NetworkManager.getInstance().getMyInfo(this, general_number, new NetworkManager.OnResultListener<MyInfoData>() {
            @Override
            public void onSuccess(Request request, MyInfoData result) {
//                mAdapter.set(result);

                String path =result.getGeneral_picture().get(0);
                Toast.makeText(MyInfoActivity.this, "사진 :" + result.getGeneral_picture().get(0), Toast.LENGTH_SHORT).show();
                Glide.with(MyInfoActivity.this).load(path).into(profileImg);

                userId.setText(result.getGeneral_name());
                email.setText(result.getGeneral_id());
                userPhone.setText(result.getGeneral_tel());
//                userPhone.setText(result.get);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyInfoActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
     */


}
