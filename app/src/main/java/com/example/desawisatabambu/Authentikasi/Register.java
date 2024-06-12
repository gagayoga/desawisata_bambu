package com.example.desawisatabambu.Authentikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desawisatabambu.Koneksi.ApiClient;
import com.example.desawisatabambu.R;
import com.example.desawisatabambu.Request.RegisterRequest;
import com.example.desawisatabambu.Response.RegisterResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    TextView signinRedirectText;

    TextInputEditText edtEmail, edtPassword, edtUsername;

    MaterialButton buttonRegister;

    String emailInput, passwordInput, usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inputan user
        edtEmail = findViewById(R.id.email);
        edtUsername = findViewById(R.id.name);
        edtPassword = findViewById(R.id.password);

        // Button Register
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });

        // Halaman login
        signinRedirectText = findViewById(R.id.signinRedirectText);
        signinRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void validateInput(){
        emailInput = edtEmail.getText().toString().trim();
        passwordInput = edtPassword.getText().toString().trim();
        usernameInput = edtUsername.getText().toString().trim();

        // validasi inputan kosong
        if(TextUtils.isEmpty(emailInput)){
            Toast.makeText(Register.this, "Email wajib di isi!", Toast.LENGTH_SHORT).show();
            edtEmail.setFocusable(true);
            return;
        } else if(TextUtils.isEmpty(passwordInput)){
            Toast.makeText(Register.this, "Password wajib di isi!", Toast.LENGTH_SHORT).show();
            edtPassword.setFocusable(true);
            return;
        }else if(TextUtils.isEmpty(usernameInput)){
            Toast.makeText(Register.this, "Username wajib di isi", Toast.LENGTH_SHORT).show();
            edtPassword.setFocusable(true);
            return;
        }
        else{
            postRegister();
        }
    }

    private void postRegister(){
        emailInput = edtEmail.getText().toString().trim();
        passwordInput = edtPassword.getText().toString().trim();
        usernameInput = edtUsername.getText().toString().trim();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(usernameInput);
        registerRequest.setEmail(emailInput);
        registerRequest.setPassword(passwordInput);

        Call<RegisterResponse> loginResponseCall = ApiClient.getUserServices().postRegister(registerRequest);

        loginResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null) {
                        // Proses respons sukses sesuai kebutuhan
                        int status = registerResponse.getStatus();
                        String message = registerResponse.getMessage();

                        if (status == 201) {
                            // Registrasi berhasil, tampilkan pesan berhasil
                            Toast.makeText(getApplicationContext(), "Registrasi Berhasil, Silakan login dahulu " + usernameInput, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Tampilkan pesan error jika respons status bukan 201
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Respons tidak sukses (status tidak 2xx)
                    // Handle error jika diperlukan
                    Toast.makeText(getApplicationContext(), "Registrasi gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Handle ketika terjadi error pada proses jaringan atau lainnya
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}