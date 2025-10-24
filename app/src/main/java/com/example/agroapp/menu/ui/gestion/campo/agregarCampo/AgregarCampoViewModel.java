package com.example.agroapp.menu.ui.gestion.campo.agregarCampo;

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
import com.example.agroapp.model.campo.Campo;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarCampoViewModel extends AndroidViewModel {

    MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    MutableLiveData<String> mErrorUbicacion = new MutableLiveData<>();
    MutableLiveData<String> mErrorArea = new MutableLiveData<>();
    MutableLiveData<String> mExito = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();
    MutableLiveData<String> mErrorLatitud = new MutableLiveData<>();
    MutableLiveData<String> mErrorLongitud = new MutableLiveData<>();




    public AgregarCampoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getErrorUbicacion() {
        return mErrorUbicacion;
    }
    public LiveData<String> getErrorArea() {
        return mErrorArea;
    }
    public LiveData<String> getExito() {
        return mExito;
    }
    public LiveData<String> getError() {
        return mError;
    }
    public LiveData<String> getErrorLatitud() {
        return mErrorLatitud;
    }
    public LiveData<String> getErrorLongitud() {
        return mErrorLongitud;
    }





    public boolean validar(String nombre, String ubicacion, String area, String latitud, String longitud) {
        boolean valido = true;


        if (nombre == null || nombre.trim().isEmpty()) {
            mErrorNombre.setValue("Ingrese un nombre");
            valido = false;
        }


        if (ubicacion == null || ubicacion.trim().isEmpty()) {
            mErrorUbicacion.setValue("Ingrese una ubicación");
            valido = false;
        }


        if (area == null || area.trim().isEmpty()) {
            mErrorArea.setValue("Ingrese un área");
            valido = false;
        } else {
            try {
                double areaVal = Double.parseDouble(area);
                if (areaVal <= 0) {
                    mErrorArea.setValue("El área debe ser mayor a 0");
                    valido = false;
                }
            } catch (NumberFormatException e) {
                mErrorArea.setValue("El área debe ser un número válido");
                valido = false;
            }
        }


        if (latitud == null || latitud.trim().isEmpty()) {
            mErrorLatitud.setValue("Ingrese una latitud");
            valido = false;
        } else {
            try {
                double lat = Double.parseDouble(latitud);
                if (lat < -90 || lat > 90) {
                    mErrorLatitud.setValue("La latitud debe estar entre -90 y 90");
                    valido = false;
                }
            } catch (NumberFormatException e) {
                mErrorLatitud.setValue("Latitud inválida");
                valido = false;
            }
        }


        if (longitud == null || longitud.trim().isEmpty()) {
            mErrorLongitud.setValue("Ingrese una longitud");
            valido = false;
        } else {
            try {
                double lon = Double.parseDouble(longitud);
                if (lon < -180 || lon > 180) {
                    mErrorLongitud.setValue("La longitud debe estar entre -180 y 180");
                    valido = false;
                }
            } catch (NumberFormatException e) {
                mErrorLongitud.setValue("Longitud inválida");
                valido = false;
            }
        }

        return valido;
    }


    public void guardarCampo(String nombre, String ubicacion, String area, String latitud, String longitud) {

        boolean valido = validar(nombre, ubicacion, area, latitud, longitud);
        double areaDouble = Double.parseDouble(area);
        double latitudDouble = Double.parseDouble(latitud);
        double longitudDouble = Double.parseDouble(longitud);


        if (valido){
            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();
            Call<JsonObject> call = service.agregarCampo("Bearer " + token, new Campo(nombre, ubicacion, areaDouble, longitudDouble,latitudDouble));

           call.enqueue(new Callback<JsonObject>() {
               @Override
               public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                   if (response.isSuccessful()){
                       mExito.postValue("Campo agregado");
                   }else{
                       mError.postValue("Error al agregar el campo");
                       Log.d("Error", response.message());
                   }
               }

               @Override
               public void onFailure(Call<JsonObject> call, Throwable t) {
                   Toast.makeText(getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
               }
           });
        }

    }
}