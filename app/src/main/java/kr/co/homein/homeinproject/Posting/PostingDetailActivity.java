package kr.co.homein.homeinproject.Posting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wefika.flowlayout.FlowLayout;

import java.io.IOException;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PostingItemData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class PostingDetailActivity extends AppCompatActivity {


    ImageView imageView;
    FlowLayout tag_layout;
    ImageButton likeBtn;
    ImageButton goWebBtn;
    Intent intent;
    String post_number;
    PostingItemData postingItemData;

    public static final String WEB_URL = "web_url";
    String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_detail);

        intent= getIntent();
        post_number = intent.getStringExtra(PostingFragment.POSTING_NUMBER);

        imageView = (ImageView) findViewById(R.id.item_image);
        tag_layout = (FlowLayout) findViewById(R.id.tag_layout);
        likeBtn = (ImageButton) findViewById(R.id.add_pick_btn);
        goWebBtn = (ImageButton) findViewById(R.id.goWeb);

        //원문보기로 가는 버튼
        goWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostingDetailActivity.this , WebViewActivity.class);
                intent.putExtra(WEB_URL,webUrl);
                startActivity(intent);

            }
        });


        ImageButton backKey = (ImageButton) findViewById(R.id.back_key);
        backKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PeopleItemDetailActivity.this, MainActivity.class); //홈으로 가기
//                startActivity(intent);
                finish();
            }
        });


        setData();
    }


    private void setData(){
        NetworkManager.getInstance().getPostingDetail(this, post_number, new NetworkManager.OnResultListener<PostingItemData>() {
            @Override
            public void onSuccess(Request request, PostingItemData result) {
//                mAdapter.set(result);
                postingItemData = result;
                Toast.makeText(PostingDetailActivity.this, "server connected", Toast.LENGTH_SHORT).show();


                Glide.with(imageView.getContext()).load(postingItemData.getPost_picture_content().get(0))
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                                Display display = getWindowManager().getDefaultDisplay();
                                int width = display.getWidth();
                                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                                params.width = width;
                                params.height = (int)(((float)width) / resource.getWidth() * resource.getHeight());
                                imageView.setLayoutParams(params);
                                imageView.setImageBitmap(resource);
                            }
                        });
                webUrl = postingItemData.getPost_website();


                for (String s : postingItemData.getPost_tag()) {
                    TextView tv = new TextView(PostingDetailActivity.this);
                    FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(0, 0, 30, 30);
                    lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                    tv.setLayoutParams(lp);
                    tv.setTextSize(15);

                    tv.setPadding(21, 21, 21, 21);
                    tv.setTextColor(Color.WHITE);
                    tv.setBackgroundColor(0XFF01579B);
                    tv.setText(s);
                    tag_layout.addView(tv);
                }

//                Glide.with(iconLogo.getContext()).load(url.get(0)).into(iconLogo);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(PostingDetailActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
