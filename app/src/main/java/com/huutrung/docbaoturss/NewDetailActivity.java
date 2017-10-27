package com.huutrung.docbaoturss;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class NewDetailActivity extends AppCompatActivity {
    private WebView wvData;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        wvData = (WebView) findViewById(R.id.wvData);
        String url = getIntent().getStringExtra("LINK");
        if(url != null){
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading.....");
            dialog.setCancelable(false);
            dialog.show();
            wvData.setWebViewClient(onWebViewLoaded);
            wvData.loadUrl(url);
        }
    }
    private WebViewClient onWebViewLoaded = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dialog.dismiss();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            dialog.dismiss();
            super.onReceivedError(view, request, error);
            Toast.makeText(NewDetailActivity.this, " Error!! ", Toast.LENGTH_SHORT);
        }
    };
}
