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

                //if(url.equals("http://10.0.2.2:50788/"))
                if (url.equals("http://d53equipe5.sv55.cmaisonneuve.qc.ca/"))
                {

                    String cookieAuth = getCookie(url,".ASPXAUTH");
                    String cookieKey = getCookie(url, "__RequestVerificationToken");
                    String cookieId = getCookie(url, "id");
                    Intent intent = new Intent(LoginActivity.this,TestFragment.class);
                    intent.putExtra("cookieAuth",cookieAuth);
                    intent.putExtra("cookieKey", cookieKey);
                    intent.putExtra("cookieId", cookieId);

                    startActivity(intent);
                }


            }





        });

        //loginWv.loadUrl("http://10.0.2.2:50788/account/login/");
        loginWv.loadUrl("http://d53equipe5.sv55.cmaisonneuve.qc.ca/account/login");


    }

    public String getCookie(String siteName,String CookieName){
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);
        String[] temp=cookies.split(";");
        for (String ar1 : temp ){
            if(ar1.contains(CookieName)){
                String[] temp1=ar1.split("=");
                CookieValue = temp1[1];
            }
        }
        return CookieValue;
    }
}
