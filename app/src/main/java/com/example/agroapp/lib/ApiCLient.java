package com.example.agroapp.lib;

import com.example.agroapp.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class ApiCLient {

    private static final String BASE_URL = "";

    public static appService getService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(appService.class);
    }

    public interface appService {

        //todo --> Tengo que implementarlo
        @FormUrlEncoded
        @POST("api/usuarios/login")
        Call<String> login(@Field("Usuario") String email, @Field("Password") String password);
        @GET("api/usuarios")
        Call<Usuario> obtenerUsuario(@Header("Authorization") String token);
    }
}