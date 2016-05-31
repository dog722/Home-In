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
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people_image);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
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
        uploadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ////여기서  입력받은 글이랑 사진이랑 태그 서버로 보내주기
                finish();
            }
        });


        inputTagBtn.setOnClickListener(this);


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

                general_number = "GM722";
                PH_content = inputComment.getText().toString();
                category_number = 1;
                sendPeopleItemWrite();


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
        InputTagDialogFragment dialog = (InputTagDialogFragment) $dialog;
        String tag = dialog.getTag();


        FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);

        TextView tagText = new TextView(this);
        tagText.setText(tag);
        tagText.setLayoutParams(lp);
        tagText.setTextSize(20);
        tagText.setTextColor(Color.WHITE);
        tagText.setBackgroundColor(Color.BLUE);
//        tagText.setPadding(20,20,20,20);

        layout.addView(tagText);
        if (tagText.length() > 0) {
            count++;
            PH_tag.add(tagText.getText().toString());
            explainTag.setVisibility(View.GONE);

            if (count >= 2) {
                //다이얼로그 띄어주기
                inputTagBtn.setEnabled(false);

                NoInputDialog dialog2 = new NoInputDialog(this);
                dialog2.setOnDismissListener(this);
                dialog2.show();
            }
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputComment.getWindowToken(), 0);

    }

    @Override
    public void onClick(View v) {

        InputTagDialogFragment dialog = new InputTagDialogFragment(this);
        dialog.setOnDismissListener(this);
        dialog.show();
    }

    public void sendPeopleItemWrite() {


        NetworkManager.getInstance().sendPeopleWrite(this, general_number, PH_content, PH_tag, path_temp, category_number, new NetworkManager.OnResultListener<PeopleItemWriteData>() {
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


