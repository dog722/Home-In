package kr.co.homein.homeinproject.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.co.homein.homeinproject.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }


    //회원 가입
    private void signup() {

    }

}
