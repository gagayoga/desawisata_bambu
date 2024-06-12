package com.example.desawisatabambu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.desawisatabambu.Model.Product;
import com.google.android.material.button.MaterialButton;

public class DetailWisata extends AppCompatActivity {

    private TextView txtLoading;

    private FrameLayout frameImage;

    private NestedScrollView kontenMain;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        frameImage = findViewById(R.id.frameImage);
        kontenMain = findViewById(R.id.kontenWisata);
        progressBar = findViewById(R.id.progressBar);
        txtLoading = findViewById(R.id.txtLoading);

        startLoadingTextAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                frameImage.setVisibility(View.VISIBLE);
                kontenMain.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                txtLoading.setVisibility(View.GONE);
            }
        }, 1500);

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
}