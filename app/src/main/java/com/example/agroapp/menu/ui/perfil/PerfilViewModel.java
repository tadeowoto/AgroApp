package com.example.agroapp.menu.ui.perfil;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.Usuario;
import com.example.agroapp.model.dto.UsuarioDto;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    MutableLiveData<Usuario> mUsuario = new MutableLiveData< Usuario>();
    MutableLiveData<String> mError = new MutableLiveData<String>();
    MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    MutableLiveData<Boolean> mGuardarPerfil  = new MutableLiveData<>();
    MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    MutableLiveData<String> mErrorEmail = new MutableLiveData<>();
    MutableLiveData<String> mErrorTelefono = new MutableLiveData<>();
    MutableLiveData<String> mExito = new MutableLiveData<>();








    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getmUsuario(){
        return mUsuario;
    }

    public LiveData<String> getmError(){
        return mError;
    }
    public LiveData<String> getmExito(){
        return mExito;
    }

    public LiveData<String> getTextoBoton(){
        return mTextoBoton;
    }
    public LiveData<String> getmErrorNombre(){
        return mErrorNombre;
    }
    public LiveData<String> getmErrorEmail(){
        return mErrorEmail;
    }
    public LiveData<String> getmErrorTelefono(){
        return mErrorTelefono;
    }
    public LiveData<Boolean> getHabilitarCampos(){
        return mHabilitarCampos;
    }
    public LiveData<Boolean> getGuardarPerfil(){
        return mGuardarPerfil;
    }



    public void llenarCampos(){

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();
        Call<Usuario> call = service.obtenerUsuario("Bearer " + token);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                mUsuario.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mError.postValue("Error " +t.getMessage());
            }
        });
    }

    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarPerfil.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }

    public boolean valido(String nombre,String email,String telefono){
        boolean valido = true;

        if (nombre == null || nombre.trim().isEmpty()){
            mErrorNombre.postValue("El nombre no puede estar vacío");
            valido = false;
        }
        if (email == null || email.trim().isEmpty()){
            mErrorEmail.postValue("El email no puede estar vacío");
            valido = false;
        }
        if (telefono == null || telefono.trim().isEmpty()){
            mErrorTelefono.postValue("El teléfono no puede estar vacío");
            valido = false;
        }
        return valido;
        }



    public void actualizarPerfil(String nombre, String email, String telefono) {
        boolean v = valido(nombre, email, telefono);
        if (v) {

            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();

            UsuarioDto usuario = new UsuarioDto(nombre, email, telefono);
            Call<JsonObject> call = service.actualizarPerfil("Bearer " + token, usuario);

            try{
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                       if (response.isSuccessful()){
                           mExito.postValue("Perfil actualizado correctamente");
                       }else{
                           Log.e("PerfilViewModel", "Error al actualizar perfil: " + response.message());
                           mError.postValue("Error al actualizar perfil: " + response.message());
                       }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mError.postValue("Error al actualizar perfil: " + t.getMessage());
                    }
                });

            } catch (Exception e) {
                Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }

    }


}