package kr.co.homein.homeinproject.HomeIn;

import android.app.Activity;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wefika.flowlayout.FlowLayout;

import java.io.File;

import kr.co.homein.homeinproject.R;

public class AddPeopleImageActivity extends AppCompatActivity implements OnDismissListener, OnClickListener{


    ImageView photoView;
    Uri mFileUri;
    FlowLayout layout;
    ImageButton inputTagBtn;
    TextView textCount;
    EditText inputComment;
    String strCur;
    ImageButton backKey;
    Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people_image);

        layout = (FlowLayout) findViewById(R.id.tag_layout);
        photoView = (ImageView )findViewById(R.id.picture);
        inputTagBtn = (ImageButton) findViewById(R.id.input_tag_btn);
        textCount = (TextView) findViewById(R.id.text_count);
        inputComment = (EditText) findViewById(R.id.input_comment);
        backKey = (ImageButton) findViewById(R.id.back_key);
        backKey.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                if(s.length() > 300){
                    inputComment.setText(strCur);
                    inputComment.setSelection(start);
                }else{
                    textCount.setText(String.valueOf(s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ImageButton btn = (ImageButton) findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGallery();
            }
        });

        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable("selected_file");
        }



    }

    private static final int RC_GALLERY = 1;
    private static final int CROP_IMAGE = 2;

    private void callGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,RC_GALLERY);
    }

    private Uri getFileUri() {
        File dir = getExternalFilesDir("myfile");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir,"my_image_" + System.currentTimeMillis() + ".jpeg");
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
                    Uri fileUri = Uri.fromFile(new File(path));
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



    @Override
    public void onDismiss(DialogInterface $dialog) {
        // TODO Auto-generated method stub
        InputTagDialogFragment dialog = (InputTagDialogFragment) $dialog ;
        String tag = dialog.getTag() ;


        FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20,20,20,20);

        TextView tagText = new TextView(this);
        tagText.setText(tag);
        tagText.setLayoutParams(lp);
        tagText.setTextSize(20);
        tagText.setTextColor(Color.WHITE);
        tagText.setBackgroundColor(Color.BLUE);
//        tagText.setPadding(20,20,20,20);

        layout.addView(tagText);


        ////여기다 텍스트뷰 동적으로 생성 ////
    }

    @Override
    public void onClick(View v) {

        InputTagDialogFragment dialog =  new InputTagDialogFragment(this);
        dialog.setOnDismissListener(this);
        dialog.show();
    }
}


