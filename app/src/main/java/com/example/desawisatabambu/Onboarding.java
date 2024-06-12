package com.example.desawisatabambu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desawisatabambu.Authentikasi.Login;
import com.google.android.material.button.MaterialButton;

public class Onboarding extends AppCompatActivity {

    MaterialButton buttonLogin;

    private SharedPreferences sharedPreferences;

    private String userToken;

    private LinearLayout layoutOnboarding;

    private ProgressBar progressBar;

    private TextView txtLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        startLoadingTextAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutOnboarding.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                txtLoading.setVisibility(View.GONE);
            }
        }, 2000);

        // Ambil data token user yang sudah login
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        userToken = getUserToken();

        // Button Login
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });

        layoutOnboarding = findViewById(R.id.kontenOnboarding);
        progressBar = findViewById(R.id.progressBar);
        txtLoading = findViewById(R.id.txtLoading);
    }

    // Setting textLoading
    private void startLoadingTextAnimation() {
        final Handler handler = new Handler();
        final String[] loadingTexts = {"Loading.", "Loading..", "Loading..."};
        final int delay = 500; // Setengah detik
        handler.post(new Runnable() {
            int index = 0;

            @Override
            public void run() {
                txtLoading.setText(loadingTexts[index]);
                index = (index + 1) % loadingTexts.length; // Ulang dari awal setelah tiga teks
                handler.postDelayed(this, delay);
            }
        });
    }

    // Fungsi get token user
    private String getUserToken() {
        return sharedPreferences.getString("user_token", "");
    }

    // Fungsi untuk cek token dan validasi
    private void checkUser(){
        if (userToken.isEmpty()) {
            Intent intent = new Intent(Onboarding.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Onboarding.this, "Selamat Datang Kembali, Anda sudah login", Toast.LENGTH_SHORT).show();
                }
            });

            Intent intent = new Intent(Onboarding.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}