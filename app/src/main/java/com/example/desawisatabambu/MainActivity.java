package com.example.desawisatabambu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desawisatabambu.Adapter.PaketWisataAdapter;
import com.example.desawisatabambu.Adapter.RekomendasiAdapter;
import com.example.desawisatabambu.Interface.ProductItemClickListener;
import com.example.desawisatabambu.Koneksi.ApiClient;
import com.example.desawisatabambu.Koneksi.UserServices;
import com.example.desawisatabambu.Model.Product;
import com.example.desawisatabambu.Request.LogoutTask;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MaterialButton buttonLoghin;
    private List<Product> productItemList;
    private TextView username, txtLoading;
    private ImageView logoutButton;
    private SharedPreferences sharedPreferences;
    private RelativeLayout appBar;
    private LinearLayout kontenMain;
    private ProgressBar progressBar;
    private String userToken, idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get data user
        // Get Username
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String usernameUserr = sharedPreferences.getString("nama_user", "");
        idUser = sharedPreferences.getString("id_user", "");
        int UserID = Integer.parseInt(idUser);
        userToken = getUserToken();

        appBar = findViewById(R.id.appBar);
        kontenMain = findViewById(R.id.kontenMain);
        progressBar = findViewById(R.id.progressBar);
        txtLoading = findViewById(R.id.txtLoading);

        startLoadingTextAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appBar.setVisibility(View.VISIBLE);
                kontenMain.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                txtLoading.setVisibility(View.GONE);
            }
        }, 4000);

        // Rekomendasi Slider
        RecyclerView listViewProducts = findViewById(R.id.recyclerview);
        listViewProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Inisialisasi list produk
        productItemList = new ArrayList<>();
        // Tambahkan produk ke productItemList
        productItemList.add(new Product("Pak Supri Homestay", "Homestay paling nyaman di desa bambu Karangasem, punya e pak Supri Homestay, peralatane lengkap, wes pokok e enak dinggo penginapan selama liburan ng desa Karangasem.", "3 Kamar", "Rp. 500.000", R.drawable.rekomendasi1));

        productItemList.add(new Product("Homestay Deswita Karangasem", "Homestay yang terdiri dari kamar mandi bersama, Kipas Angin, " +
                "Musholla, Perlengkapan Mandi, Sarapan Pagi, dan Televisi." +
                "Cocok buat kalian untuk menginap di desa bambu Karangasem.", "2 Kamar", "Rp. 350.000", R.drawable.rekomendasi2));

        productItemList.add(new Product("Almiya Homestay", "Homestay yang terdiri dari kamar mandi bersama, Kipas Angin, " +
                "Musholla, Perlengkapan Mandi, Sarapan Pagi." +
                "Cocok buat kalian untuk menginap di desa bambu Karangasem.", "4 Kamar", "Rp. 200.000", R.drawable.rekomendasi3));

        // Inisialisasi adapter
        RekomendasiAdapter adapterItem = new RekomendasiAdapter(this, productItemList, new ProductItemClickListener() {

            @Override
            public void onProductItemClick(Product product) {
                // Navigasi ke halaman detail produk dan kirimkan data produk yang dipilih
                Intent intent = new Intent(MainActivity.this, DetailWisata.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });

        listViewProducts.setAdapter(adapterItem);

        // Paket Wisata Slider
        RecyclerView listViewProductsWisata = findViewById(R.id.recyclerviewWisata);
        listViewProductsWisata.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Inisialisasi list produk
        productItemList = new ArrayList<>();
        // Tambahkan produk ke productItemList
        productItemList.add(new Product("Paket Outbound", "Welcome Outbound | Snack 1X | OutBound Bambu di Hutan Pinus | Egrang Segitiga | Estafet Bambu | Makan dan Minum 1X.", "3 Kamar", "Rp. 100.000", R.drawable.paket1));

        productItemList.add(new Product("Paket Gamelan", "Paket wisata edukasi gamelan" +
                "Belajar gamelan 1 jam | 1 paket 1 orang | beli 10 Paket gratis 1 paket", "2 Kamar", "Rp. 25.000", R.drawable.paket2));

        productItemList.add(new Product("Paket Menganyam Bambu", "Paket wisata edukasi kerajinan bambu" +
                "Belajar membuat kerajinan bambu | 1 Paket 1 orang | 1 paket 3 kerajinan | kerajinan boleh dibawa pulang", "4 Kamar", "Rp. 25.000", R.drawable.paket3));

        // Inisialisasi adapter
        PaketWisataAdapter adapterItemWisata = new PaketWisataAdapter(this, productItemList, new ProductItemClickListener() {

            @Override
            public void onProductItemClick(Product product) {
                // Navigasi ke halaman detail produk dan kirimkan data produk yang dipilih
                Intent intent = new Intent(MainActivity.this, DetailProduk.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });

        listViewProductsWisata.setAdapter(adapterItemWisata);

        ImageView fab = findViewById(R.id.logout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        ImageView profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileUser.class);
                startActivity(intent);
            }
        });
    }

    private void logoutUser() {
        String token = "Bearer " + userToken;

        UserServices userServices = ApiClient.getUserServices();
        Call<Void> call = userServices.userLogout("Bearer " + token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Clear user data from SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();

                    String message = "Logout berhasil, silakan login kembali";
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Pemberitahuan")
                            .setMessage(message)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Redirect to login activity
                                    Intent intentLogout = new Intent(MainActivity.this, Onboarding.class);
                                    startActivity(intentLogout);
                                    finish();
                                }
                            })
                            .setIcon(R.drawable.icon_success)
                            .show();

                } else {
                    Toast.makeText(MainActivity.this, "Failed to logout: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // get token user
    private String getUserToken() {
        return sharedPreferences.getString("user_token", "");
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