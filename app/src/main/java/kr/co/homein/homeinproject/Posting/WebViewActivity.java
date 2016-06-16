package kr.co.homein.homeinproject.Posting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kr.co.homein.homeinproject.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    Intent intent;
    String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        intent = getIntent();
        webUrl = intent.getStringExtra(PostingDetailActivity.WEB_URL);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // 응룡프로그램에서 직접 url 처리
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webView.loadUrl(webUrl);

    }
}
