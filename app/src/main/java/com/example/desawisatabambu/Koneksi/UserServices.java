package com.example.desawisatabambu.Koneksi;

import com.example.desawisatabambu.Request.LoginRequest;
import com.example.desawisatabambu.Request.RegisterRequest;
import com.example.desawisatabambu.Response.LoginResponse;
import com.example.desawisatabambu.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserServices {

    @Headers({"Accept: application/json"})

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("registrasi")
    Call<RegisterResponse> postRegister(@Body RegisterRequest registerRequest);
}
