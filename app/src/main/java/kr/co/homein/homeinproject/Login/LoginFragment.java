package kr.co.homein.homeinproject.Login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.io.IOException;
import java.util.Arrays;

import kr.co.homein.homeinproject.MainActivity;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyInfoData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    ImageButton SignInButton , signUpButton , facebookLoginButton;
    EditText emailView, passwordView;

    String email;
    String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
    }

    private boolean isLogin() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token!=null;
    }

    private void login() {
        if (!isLogin()) {
            loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    loginServer();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });

            loginManager.logInWithReadPermissions(this, Arrays.asList("email"));
        } else {
//            loginManager.logOut();
            loginServer();
        }
    }

    private void loginServer() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        NetworkManager.getInstance().facebookSignIn(getContext(), token.getToken(),
                new NetworkManager.OnResultListener<MyInfoData>(){
                    @Override
                    public void onSuccess(Request request, MyInfoData result) {
                        if (result.getGeneral_login_yn() == 1) {
                            PropertyManager.getInstance().setLogin(true);
                            PropertyManager.getInstance().setUser(result);
                            PropertyManager.getInstance().setEmail(email);
                            PropertyManager.getInstance().setPassword(password);
                            PropertyManager.getInstance().setGeneralNumber(result.getGeneral_number());
                            goMainActivity();
                        }
                    }

                    @Override
                    public void onFail(Request request, IOException exception) {
                        Toast.makeText(getContext(), "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailView= (EditText)  view.findViewById(R.id.input_email);
        passwordView = (EditText) view.findViewById(R.id.input_pw);

        SignInButton = (ImageButton) view.findViewById(R.id.signin);

        facebookLoginButton = (ImageButton) view.findViewById(R.id.facebook_btn);
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailView.getText().toString();
                password = passwordView.getText().toString();

                setupSignIn();
            }
        });


        signUpButton = (ImageButton) view.findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        return view;
    }


    CallbackManager callbackManager;
    LoginManager loginManager;
    AccessTokenTracker tokenTracker;


    private void goMainActivity() {
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

    private void signup() {
        ((LoginActivity)getActivity()).changeSignUp();
    }


    private void setupSignIn(){
//
//        email = emailView.getText().toString();
//        password = passwordView.getText().toString();


        NetworkManager.getInstance().signin(getContext(), email, password,
                new NetworkManager.OnResultListener<MyInfoData>(){
                    @Override
                    public void onSuccess(Request request, MyInfoData result) {
                        if (result.getGeneral_login_yn() == 1) {
                            PropertyManager.getInstance().setLogin(true);
                            PropertyManager.getInstance().setUser(result);
                            PropertyManager.getInstance().setEmail(email);
                            PropertyManager.getInstance().setPassword(password);
                            PropertyManager.getInstance().setGeneralNumber(result.getGeneral_number());
                            goMainActivity();
                        }
                    }

                    @Override
                    public void onFail(Request request, IOException exception) {
                        Toast.makeText(getContext(), "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
