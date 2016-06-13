package kr.co.homein.homeinproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.IOException;

import kr.co.homein.homeinproject.Login.LoginActivity;
import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.data.MyInfoData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class SplashActivity extends AppCompatActivity {
    Handler mHandler = new Handler(Looper.getMainLooper());

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        doRealStart();

//        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//            }
//        };
//        setUpIfNeeded();


    }

    @Override
    protected void onResume() {
        super.onResume();
//        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
//                new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_SERVICES_RESOLUTION_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            setUpIfNeeded();
        }
    }

    private void setUpIfNeeded() {
        if (checkPlayServices()) {
            String regId = PropertyManager.getInstance().getRegistrationToken();
            if (!regId.equals("")) {
                doRealStart();
            } else {
//                Intent intent = new Intent(this, RegistrationIntentService.class);
//                startService(intent);
            }
        }
    }

    private void doRealStart() {
        startSplash();

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

    private void startSplash() {


        String email = PropertyManager.getInstance().getEmail();
        if (!TextUtils.isEmpty(email)) {
            String password = PropertyManager.getInstance().getPassword();
//
//        email = emailView.getText().toString();
//        password = passwordView.getText().toString();
//                email ="dog722@gmail.com";
//                password = "dog722";

            NetworkManager.getInstance().signin(this, email, password,
                    new NetworkManager.OnResultListener<MyInfoData>() {
                        @Override
                        public void onSuccess(Request request, MyInfoData result) {
                            if (result.getGeneral_login_yn() == 1) {
                                PropertyManager.getInstance().setLogin(true);
                                PropertyManager.getInstance().setUser(result);
                                goMainActivity();
                            }
                        }

                        @Override
                        public void onFail(Request request, IOException exception) {
                            Toast.makeText(SplashActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            goLoginActivity();
                        }
                    });

        } else {
                AccessToken token = AccessToken.getCurrentAccessToken();
                if (token == null) {
                    PropertyManager.getInstance().setFacebookId("");
                    goLoginActivity();
                } else {
                        NetworkManager.getInstance().facebookSignIn(this, token.getToken(), new NetworkManager.OnResultListener<MyInfoData>() {
                            @Override
                            public void onSuccess(Request request, MyInfoData result) {
                                if (result.getGeneral_login_yn() == 1) {
                                    PropertyManager.getInstance().setLogin(true);
                                    PropertyManager.getInstance().setUser(result);
                                    goMainActivity();
                                } else {
                                    PropertyManager.getInstance().setFacebookId("");
                                    LoginManager.getInstance().logOut();
                                    goLoginActivity();
                                }

                            }

                            @Override
                            public void onFail(Request request, IOException exception) {
                                PropertyManager.getInstance().setFacebookId("");
                                LoginManager.getInstance().logOut();
                                goLoginActivity();
                            }
                        });
                }

        }
    }

    private void goMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
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
}
