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

import java.io.IOException;

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

    ImageButton SignInButton , signUpButton;
    EditText emailView, passwordView;

    String email;
    String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSignIn();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailView= (EditText)  view.findViewById(R.id.input_email);
        passwordView = (EditText) view.findViewById(R.id.input_pw);

        SignInButton = (ImageButton) view.findViewById(R.id.signin);



//        SignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                email = emailView.getText().toString();
//                password = passwordView.getText().toString();
//
//                setupSignIn();
//            }
//        });


        signUpButton = (ImageButton) view.findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        return view;
    }

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

        email ="dog722@gmail.com";
        password = "dog722";
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
