package com.example.desawisatabambu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MapsView extends AppCompatActivity {

    private WebView webView;

    private ProgressBar progressBar;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view);

        // Setting webview maps desawisata
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.loadUrl("https://www.google.com/maps/place/Limasan+Desa+Wisata+Karangasem/@-7.9188284,110.4442793,17z/data=!4m15!1m8!3m7!1s0x2e7a53b1ec518463:0x64276e584add050b!2sLimasan+Desa+Wisata+Karangasem!8m2!3d-7.9188284!4d110.4442793!10e5!16s%2Fg%2F11j8jn1vwz!3m5!1s0x2e7a53b1ec518463:0x64276e584add050b!8m2!3d-7.9188284!4d110.4442793!16s%2Fg%2F11j8jn1vwz?hl=id&entry=ttu");

        // Tombol kembali
        imageView = findViewById(R.id.iconBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}