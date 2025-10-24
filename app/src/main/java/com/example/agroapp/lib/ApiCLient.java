package com.example.agroapp.lib;

import com.example.agroapp.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ApiCLient {

    private static final String BASE_URL = "http://10.0.2.2:5133/";


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

        @FormUrlEncoded
        @POST("/api/usuarios/login")
        Call<String> login(@Field("email") String email, @Field("password") String password);
        @GET("api/usuarios/logged")
        Call<Usuario> obtenerUsuario(@Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("/api/usuarios/CambiarPassword")
        Call<JsonObject> cambiarPassword(@Header("Authorization") String token, @Field("currentPassword") String contraseniaActual, @Field("newPassword") String nuevaContrasenia);

    }

}
