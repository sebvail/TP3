package com.example.snowt.omnipucks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends AppCompatActivity {

    WebView loginWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginWv = (WebView) findViewById(R.id.loginWV);

        loginWv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(url.equals("http://d53equipe5.sv55.cmaisonneuve.qc.ca/"))
                {
                    String cookies = CookieManager.getInstance().getCookie(url);
                    Intent intent = new Intent(LoginActivity.this,TestFragment.class);
                    intent.putExtra("cookies",cookies);

                    startActivity(intent);
                }


            }




        });

        loginWv.loadUrl("http://d53equipe5.sv55.cmaisonneuve.qc.ca/account/login");


    }
}
