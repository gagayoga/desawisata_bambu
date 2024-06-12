package com.example.desawisatabambu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desawisatabambu.Model.Product;
import com.google.android.material.button.MaterialButton;

public class DetailWisata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        // Ambil data produk dari intent
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");

        // Set judul
        TextView judulTextView = findViewById(R.id.judul_item);
        judulTextView.setText(product.getJudul());

        // Set deskripsi
        TextView deskripsiTextView = findViewById(R.id.deskripsi);
        deskripsiTextView.setText(product.getDeskripsi());

        // Set Jumlah Kamar
        TextView jmlahKamar = findViewById(R.id.jumlah_kamar);
        jmlahKamar.setText(product.getJumlahKamar());

        // Set harga
        TextView hargaTextView = findViewById(R.id.harga_item);
        hargaTextView.setText(product.getHarga());

        // Set gambar
        ImageView gambarImageView = findViewById(R.id.gambar_item);
        gambarImageView.setImageResource(product.getGambarResourceId());

        MaterialButton buttonMessage = findViewById(R.id.buttonCall);
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "+628979341242";
                String message = "Selamat pagi kak" + "\nSaya mau pesan paket homestay selama 5 hari ya kak.";

                // Format URL untuk mengirim pesan langsung di WhatsApp dengan pesan tertentu
                String url = "https://wa.me/" + phoneNumber + "?text=" + Uri.encode(message);

                // Membuat dan memulai Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        // Tambahkan onClickListener pada ImageView back
        ImageView backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke MainActivity
                finish();
            }
        });
    }
}