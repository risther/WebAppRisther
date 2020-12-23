package com.example.wapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wapp.WebAppInterface;
import com.example.wapp.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;

    private static String DIRECTION = "http://www.risther.ga/";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings();

        constraintLayout = (ConstraintLayout)findViewById(R.id.idContrainL);

        createSnackbar();
    }

    private void settings() {
        WebView webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new WebAppInterface(this,webView,webView),"Android");
        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl(DIRECTION);
    }

    private  class MyWebViewClient extends android.webkit.WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView webView = (WebView)findViewById(R.id.webView);
        if((keyCode== KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void createSnackbar(){
        Snackbar snackbar = Snackbar.make(constraintLayout, "Bienbenido al mundo de Risther", Snackbar.LENGTH_LONG)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(Color.parseColor("#29ca8e"));
        snackbar.show();

    }


}