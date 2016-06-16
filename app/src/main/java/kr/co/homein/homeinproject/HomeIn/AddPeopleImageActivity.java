package kr.co.homein.homeinproject.HomeIn;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wefika.flowlayout.FlowLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.Login.PropertyManager;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PeopleItemWriteData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class AddPeopleImageActivity extends AppCompatActivity implements OnDismissListener, OnClickListener {


    ImageView photoView;
    Uri mFileUri;
    FlowLayout layout;
    ImageButton inputTagBtn;
    TextView textCount;
    EditText inputComment;
    String strCur;
    Button uploadBtn;
    TextView explainTag;
    int isSuccess =0;
    PeopleItemWriteData peopleItemWriteData;


    String general_number;
    String PH_content;
    List<String> PH_tag = new ArrayList<String>();
    int category_number;
    File path_temp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    InputTagDialogFragment dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people_image);

        dialog= new InputTagDialogFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_60dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        layout = (FlowLayout) findViewById(R.id.tag_layout);
        explainTag = (TextView) findViewById(R.id.explain_tag);
        photoView = (ImageView) findViewById(R.id.picture);
        inputTagBtn = (ImageButton) findViewById(R.id.input_tag_btn);
        textCount = (TextView) findViewById(R.id.text_count);
        inputComment = (EditText) findViewById(R.id.input_comment);
        uploadBtn = (Button) findViewById(R.id.upload_btn);
//        uploadBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ////여기서  입력받은 글이랑 사진이랑 태그 서버로 보내주기
//                finish();
//            }
//        });


        inputTagBtn.setOnClickListener(this);



//        inputComment.requestFocus();
        inputComment.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        inputComment.setInputType(InputType.TYPE_CLASS_TEXT);

//        //키보드 보이게 하는 부분
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

//        inputComment.requestFocus();
        //키보드 보이게 하는 부분

        inputComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_NEXT:
                        Toast.makeText(getApplicationContext(), "다음", Toast.LENGTH_LONG).show();
                        //키보드 내리기
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(inputComment.getWindowToken(), 0);

                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_LONG).show();
                        return false;
                }
                return true;
            }
        });


        inputComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                strCur = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 300) {
                    inputComment.setText(strCur);
                    inputComment.setSelection(start);
                } else {
                    textCount.setText(String.valueOf(s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });




        photoView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callGallery();
            }

        });

        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable("selected_file");
        }

        //등록 버튼
        uploadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                PH_content = inputComment.getText().toString();
                category_number = 1;


                if(isSuccess != 0 ) {
                    sendPeopleItemWrite();
                    finish();
                }
                else{
                    Toast.makeText(AddPeopleImageActivity.this, "사진이 등록되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

                    Glide.with(this).load(fileUri).into(photoView);
                    isSuccess = 1;
                }
                c.close();
//                Glide.with(this).load(mFileUri).into(photoView);
            }
        }
    }


    int count = 0;

    @Override
    public void onDismiss(DialogInterface $dialog) {
        // TODO Auto-generated method stub
        String tag = dialog.getInputTag();

        /*
                   FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);

            lp.setMargins(0, 0, 30, 30);
            lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            tv.setLayoutParams(lp);
            tv.setTextSize(15);

            tv.setPadding(21, 21, 21, 21);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(0XFF01579B);
            tv.setText(s);
            tagLayout.addView(tv);
         */



        FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);

        TextView tagText = new TextView(this);
        lp.setMargins(0, 0, 30, 30);
        lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        tagText.setLayoutParams(lp);
        tagText.setTextSize(15);

        tagText.setPadding(21, 21, 21, 21);
        tagText.setTextColor(Color.WHITE);
        tagText.setBackgroundColor(0XFF01579B);
        tagText.setText(tag);
//        tagText.setPadding(20,20,20,20);

        layout.addView(tagText);
        if (tagText.length() > 0) {
            count++;
            PH_tag.add(tagText.getText().toString());
            explainTag.setVisibility(View.GONE);

            if (count >= 10) {
                //다이얼로그 띄어주기
                inputTagBtn.setEnabled(false);

                NoInputDialog dialog2 = new NoInputDialog();
                dialog2.setOnDismissListener(this);
                dialog2.show(getSupportFragmentManager(), "dialog");
            }
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputComment.getWindowToken(), 0);

    }

    @Override
    public void onClick(View v) {

        dialog.setOnDismissListener(this);
        dialog.show(getSupportFragmentManager(), "dialog");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    public void sendPeopleItemWrite() {


        NetworkManager.getInstance().sendPeopleWrite(this, PropertyManager.getInstance().getGeneralNumber(), PH_content, PH_tag, path_temp, category_number, new NetworkManager.OnResultListener<PeopleItemWriteData>() {
            @Override
            public void onSuccess(Request request, PeopleItemWriteData result) {
//                mAdapter.set(result);
                peopleItemWriteData = result;
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(AddPeopleImageActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


