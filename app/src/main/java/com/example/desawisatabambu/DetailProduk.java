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

public class DetailProduk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        // Ambil data produk dari intent
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");

        // Set judul
        String judul = product.getJudul();
        String judulText;

        if ("Paket 1".equals(judul)) {
            judulText = "Paket OutBound";
        } else if ("Paket 2".equals(judul)) {
            judulText = "Paket Gamelan";
        } else if ("Paket 3".equals(judul)) {
            judulText = "Paket Menganyam Bambu";
        } else {
            judulText = judul;
        }

        TextView judulTextView = findViewById(R.id.judul_item);
        judulTextView.setText(judulText);

        TextView namaPaketView = findViewById(R.id.nama_paket);
        namaPaketView.setText(judulText);

        // Set deskripsi
        TextView deskripsiTextView = findViewById(R.id.deskripsi);
        deskripsiTextView.setText(product.getDeskripsi());

        // Set Jumlah Kamar
        //TextView jmlahKamar = findViewById(R.id.jumlah_kamar);
        //jmlahKamar.setText(product.getJumlahKamar());

        // Set harga
        TextView hargaTextView = findViewById(R.id.harga_item);
        hargaTextView.setText(product.getHarga());

        // Set gambar
        ImageView gambarImageView = findViewById(R.id.gambar_item_wisata);
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