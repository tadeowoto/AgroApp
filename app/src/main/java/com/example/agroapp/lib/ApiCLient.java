package com.example.agroapp.lib;

import com.example.agroapp.model.Usuario;
import com.example.agroapp.model.actividad.Actividad;
import com.example.agroapp.model.campo.Campo;
import com.example.agroapp.model.cosecha.Cosecha;
import com.example.agroapp.model.dto.LoteDto;
import com.example.agroapp.model.insumo.Insumo;
import com.example.agroapp.model.lote.Lote;
import com.example.agroapp.model.recurso.Recurso;
import com.example.agroapp.model.tipoActividad.TipoActividad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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


        @GET("/api/campos/usuario")
        Call<List<Campo>> obtenerCampos(@Header("Authorization") String token);

        @GET("api/campos/{id_campo}")
        Call<Campo> obtenerCampoPorId(@Header("Authorization") String token, @Path("id_campo") int id_campo);


        @POST("/api/campos/editar/{id_campo}")
        Call<Campo> actualizarCampo(@Header("Authorization") String token, @Path("id_campo") int id_campo, @Body Campo campo);

        @POST("/api/campos/agregar")
        Call<JsonObject> agregarCampo(@Header("Authorization") String token, @Body Campo campo);

        @GET("/api/lotes/{id_campo}")
        Call<List<Lote>> obtenerLotesPorIdCampo(@Header("Authorization") String token, @Path("id_campo") int id_campo);

        @POST("/api/lotes/editar/{id_lote}")
        Call<Lote> actualizarLote(@Header("Authorization") String token, @Path("id_lote") int id_lote, @Body LoteDto lote);

        //TODO --> PONERLE DATETIME NOW A LA FECHA DE CREACION
        @POST("/api/lotes/agregar")
        Call<JsonObject> agregarLote(@Header("Authorization") String token, @Body LoteDto lote);


        @GET("/api/actividades/usuario")
        Call<List<Actividad>> obtenerActividadesDelUsuario(@Header("Authorization") String token);

        @GET("/api/insumos/{id_insumo}")
        Call<Insumo> obtenerInsumoPorId(@Header("Authorization") String token, @Path("id_insumo") int id_insumo);

        @GET("/api/recursos/{id_recurso}")
        Call<Recurso> obtenerRecursoPorId(@Header("Authorization") String token, @Path("id_recurso") int id_recurso);

        @GET("/api/lote/{id_lote}")
        Call<Lote> obtenerLotePorId(@Header("Authorization") String token, @Path("id_lote") int id_lote);

        @GET("/api/tipoactividades/{id_tipo_actividad}")
        Call<TipoActividad> obtenerActividadPorId(@Header("Authorization") String token, @Path("id_tipo_actividad") int id_tipo_actividad);


        @GET("/api/recursos")
        Call<List<Recurso>> obtenerRecursosDelUsuario(@Header("Authorization") String token);

        @GET("/api/lotes")
        Call<List<Lote>> obtenerLotesDelUsuario(@Header("Authorization") String token);

        @GET("/api/insumos")
        Call<List<Insumo>> obtenerInsumosDelUsuario(@Header("Authorization") String token);

        @GET("/api/tipoactividades")
        Call<List<TipoActividad>> obtenerTipoActividades(@Header("Authorization") String token);

        @POST("api/actividades/agregar")
        Call<JsonObject> agregarActividad(@Header("Authorization") String token, @Body Actividad actividad);

        @POST("/api/recursos/agregar")
        Call<JsonObject> agregarRecurso(@Header("Authorization") String token, @Body Recurso recurso);

        @PUT("/api/recursos/actualizar/{id_recurso}")
        Call<JsonObject> actualizarRecurso(@Header("Authorization") String token, @Path("id_recurso") int id_recurso, @Body Recurso recurso);

        @PUT("/api/insumos/actualizar/{id_insumo}")
        Call<JsonObject> actualizarInsumo(@Header("Authorization") String token, @Path("id_insumo") int id_insumo, @Body Insumo insumo);


        @POST("/api/insumos/crear")
        Call<Insumo> crearInsumo(@Header("Authorization") String token, @Body Insumo insumo);

        @GET("/api/cosechas/{id_lote}")
        Call<List<Cosecha>> obtenerCosechasDelLote(@Header("Authorization") String token, @Path("id_lote") int id_lote);

        @PUT("/api/cosechas/actualizar/{id_cosecha}")
        Call<JsonObject> actualizarCosecha(@Header("Authorization") String token, @Path("id_cosecha") int id_cosecha, @Body Cosecha cosecha);

        @POST("/api/cosechas/agregar")
        Call<JsonObject> agregarCosecha(@Header("Authorization") String token, @Body Cosecha cosecha);




    }

}
