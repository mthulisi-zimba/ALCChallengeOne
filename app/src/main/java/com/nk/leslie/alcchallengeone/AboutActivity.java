package com.nk.leslie.alcchallengeone;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                switch (error.getPrimaryError()) {
                    case SslError.SSL_UNTRUSTED:
                        Toast.makeText(AboutActivity.this,"SslError : The certificate authority is not trusted.",Toast.LENGTH_LONG).show();
                        //LogUtility.debug("SslError : The certificate authority is not trusted.");
                        break;
                    case SslError.SSL_EXPIRED:
                        Toast.makeText(AboutActivity.this,"SslError : The certificate has expired.",Toast.LENGTH_LONG).show();
                        //LogUtility.debug("SslError : The certificate has expired.");
                        break;
                    case SslError.SSL_IDMISMATCH:
                        Toast.makeText(AboutActivity.this,"The certificate Hostname mismatch.",Toast.LENGTH_LONG).show();
                        //LogUtility.debug("The certificate Hostname mismatch.");
                        break;
                    case SslError.SSL_NOTYETVALID:
                        Toast.makeText(AboutActivity.this,"The certificate is not yet valid.",Toast.LENGTH_LONG).show();
                        //LogUtility.debug("The certificate is not yet valid.");
                        break;
                }
                handler.proceed();
            }
        });


        webView.loadUrl("https://andela.com/alc/");


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
