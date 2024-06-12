package com.example.desawisatabambu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ProfileUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        // Tambahkan onClickListener pada ImageView back
        ImageView backIcon = findViewById(R.id.iconBack);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke MainActivity
                finish();
            }
        });

        // Tambahkan onClickListener pada ImageView back
        MaterialButton buttonLokasi = findViewById(R.id.buttonLokasi);
        buttonLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileUser.this, MapsView.class);
                startActivity(intent);

                // Intent untuk membuka Google Maps dengan lokasi yang ditentukan
                //Uri gmmIntentUri = Uri.parse("https://goo.gl/maps/rSW1J1XzjTyABQgRA?g_st=aw");
                //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                //mapIntent.setPackage("com.google.android.apps.maps");

                // Pastikan terdapat aplikasi Google Maps di perangkat sebelum membuka Intent
                //if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    //startActivity(mapIntent);
                } //else {
                    // Jika tidak ditemukan aplikasi Google Maps, tampilkan pesan
                    //Toast.makeText(ProfileUser.this, "Aplikasi Google Maps tidak ditemukan", Toast.LENGTH_SHORT).show();
                //}
        });
    }
}