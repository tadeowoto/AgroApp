package com.example.agroapp.menu.ui.gestion.campo.detalleCampo;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.campo.Campo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleCampoViewModel extends AndroidViewModel {

    private MutableLiveData<Campo> mCampo = new MutableLiveData<>();
    private MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    private MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    private MutableLiveData<Boolean> mGuardarCampo = new MutableLiveData<>();
    private MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    private MutableLiveData<String> mErrorUbicacion = new MutableLiveData<>();
    private MutableLiveData<String> mErrorExtension = new MutableLiveData<>();
    private MutableLiveData<String> mErrorLatitud = new MutableLiveData<>();
    private MutableLiveData<String> mErrorLongitud = new MutableLiveData<>();
    private MutableLiveData<String> mExito = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();







    public DetalleCampoViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getError() {
        return mError;
    }

    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getExito() {
        return mExito;
    }
    public LiveData<String> getErrorUbicacion() {
        return mErrorUbicacion;
    }
    public LiveData<String> getErrorExtension() {
        return mErrorExtension;
    }
    public LiveData<String> getErrorLatitud() {
        return mErrorLatitud;
    }
    public LiveData<String> getErrorLongitud() {
        return mErrorLongitud;
    }





    public LiveData<Campo> getCampo() {
        return mCampo;
    }

    public LiveData<String> getTextoBoton() {
        return mTextoBoton;
    }
    public LiveData<Boolean> getGuardarCampo() {
        return mGuardarCampo;
    }


    public LiveData<Boolean> getHabilitarCampos() {
        return mHabilitarCampos;
    }


    public void cargarElCampo(Campo c) {
        mCampo.setValue(c);
    }

    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarCampo.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }

    public void actualizarCampo(String nombre, String ubicacion, String extension, String latitud, String longitud) {

        boolean valido = validar(nombre, ubicacion, extension, latitud, longitud);

        if (valido){
            double extensionDouble = Double.parseDouble(extension);
            double latitudDouble = Double.parseDouble(latitud);
            double longitudDouble = Double.parseDouble(longitud);
            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();
            Campo c = new Campo( nombre, ubicacion, extensionDouble, latitudDouble, longitudDouble);
            Call<Campo> call = service.actualizarCampo("Bearer " + token, mCampo.getValue().getId_campo(), c);

            call.enqueue(new Callback<Campo>() {
                @Override
                public void onResponse(Call<Campo> call, Response<Campo> response) {
                    if (response.isSuccessful()){
                        mExito.postValue("Campo actualizado");
                    }else{
                        mError.postValue("Error al actualizar el campo");
                    }
                }

                @Override
                public void onFailure(Call<Campo> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }


    }


    public boolean validar(String nombre, String ubicacion, String extension, String latitud, String longitud){
        boolean valido = true;

        if (nombre == null || nombre.trim().isEmpty()){
            mErrorNombre.setValue("Ingrese un nombre");
            valido = false;
        }
        if (ubicacion == null || ubicacion.trim().isEmpty()){
            mErrorUbicacion.setValue("Ingrese una ubicación");
            valido = false;
        }
        if (extension == null || extension.trim().isEmpty()){
            mErrorExtension.setValue("Ingrese una extensión");
            valido = false;
        }
        if (latitud == null || latitud.trim().isEmpty()){
            mErrorLatitud.setValue("Ingrese una latitud");
            valido = false;
        }
        if (longitud == null || longitud.trim().isEmpty()){
            mErrorLongitud.setValue("Ingrese una longitud");
            valido = false;
        }

        return valido;
    }
}