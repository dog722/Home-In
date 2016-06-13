package kr.co.homein.homeinproject.Menu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

import java.io.File;
import java.io.IOException;

import kr.co.homein.homeinproject.Login.LoginActivity;
import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.MyInfoData;
import kr.co.homein.homeinproject.data.ReceiveResultData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;


public class MyInfoActivity extends AppCompatActivity implements OnDismissListener , OnClickListener {

    ImageView profileImg;
    TextView userId, email, editPW, userPhone, inputAdress;
    String currentPW , newPW;
    MyInfoData myInfoData;
    Uri mFileUri;
    File path_temp;
    ImageButton btnLogout;

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
        btnLogout = (ImageButton) findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogOut();
            }
        });

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

        //프로필 사진 수정
        profileImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callGallery();

            }
        });
        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable("selected_file");
        }



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


        setData();

    }


    private void doLogOut(){

        String email = PropertyManager.getInstance().getEmail();
        if (!TextUtils.isEmpty(email)) {
            String password = PropertyManager.getInstance().getPassword();


            NetworkManager.getInstance().signOut(this, PropertyManager.getInstance().getGeneralNumber(),
                    new NetworkManager.OnResultListener<MyInfoData>() {
                        @Override
                        public void onSuccess(Request request, MyInfoData result) {
                            if (result.getGeneral_login_yn() == 0) {
                                PropertyManager.getInstance().setLogin(false);
                                PropertyManager.getInstance().setUser(null);
                                PropertyManager.getInstance().setGeneralNumber("");
                                PropertyManager.getInstance().setPassword("");
                                PropertyManager.getInstance().setEmail("");

                                LoginManager.getInstance().logOut();

                                Intent intent = new Intent(MyInfoActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }
                        }

                        @Override
                        public void onFail(Request request, IOException exception) {
                            Toast.makeText(MyInfoActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }




    private static final int RC_GALLERY = 1;
    private static final int CROP_IMAGE = 2;

    private void callGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, RC_GALLERY);
    }

    private Uri getFileUri() {
        File dir = getExternalFilesDir("myfile");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "my_image_" + System.currentTimeMillis() + ".jpeg");
        mFileUri = Uri.fromFile(file);
        return mFileUri;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable("selected_file", mFileUri);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    path_temp = new File(path);

                    Uri fileUri = Uri.fromFile(path_temp);

//                    BitmapFactory.Options opts = new BitmapFactory.Options();
//                    opts.inSampleSize = 4;
//                    Bitmap bm = BitmapFactory.decodeFile(path, opts);
//                    photoView.setImageBitmap(bm);
                    Glide.with(this).load(fileUri).into(profileImg);

                    sendProfileImg();

                }
                c.close();
//                Glide.with(this).load(mFileUri).into(photoView);
            }
        }
    }



    String general_number = "GM722";
    public void sendProfileImg(){
        NetworkManager.getInstance().uploadProfileImg(this, PropertyManager.getInstance().getGeneralNumber(),path_temp, new NetworkManager.OnResultListener<ReceiveResultData>() {
            @Override
            public void onSuccess(Request request, ReceiveResultData result) {
//                mAdapter.set(result);
                 if(result.isResult == 1){
                     Toast.makeText(MyInfoActivity.this, "사진이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                 }else {
                    Toast.makeText(MyInfoActivity.this, "사진 수정이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                 }
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyInfoActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }



//    private void setData() {
//        myInfoData.setUserId("ving.9");
//        myInfoData.setUserAdress("서울 특별시 신림동");
//        myInfoData.setUserEmail("mingu77777@gmail.com");
//        myInfoData.setUserPhoneNum("+82 10 2866 8263");
//        myInfoData.setUserPW("00000000");
//    }

    public void setData(){

        NetworkManager.getInstance().getMyInfo(this, PropertyManager.getInstance().getGeneralNumber(), new NetworkManager.OnResultListener<MyInfoData>() {
            @Override
            public void onSuccess(Request request, MyInfoData result) {
//                mAdapter.set(result);

                if(result.getGeneral_picture().size()>0) {

                    String path = result.getGeneral_picture().get(0);
                    Toast.makeText(MyInfoActivity.this, "사진 :" + result.getGeneral_picture().get(0), Toast.LENGTH_SHORT).show();
                    Glide.with(MyInfoActivity.this).load(path).into(profileImg);

                }

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
    }



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
