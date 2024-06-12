package com.example.desawisatabambu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.desawisatabambu.Authentikasi.Login;
import com.google.android.material.button.MaterialButton;

public class Onboarding extends AppCompatActivity {

    MaterialButton buttonLogin;

    private SharedPreferences sharedPreferences;

    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

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
    }

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