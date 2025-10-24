package com.example.agroapp.menu.ui.perfil.cambiarPass;

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
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPasswordViewModel extends AndroidViewModel {


    private MutableLiveData<String> mErrorContraseniaActual = new MutableLiveData<>();
    private MutableLiveData<String> mErrorNuevaContrasenia = new MutableLiveData<>();
    private MutableLiveData<String> mErrorRepetirNuevaContrasenia = new MutableLiveData<>();

    private MutableLiveData<String> mExito = new MutableLiveData<>();


    public CambiarPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getExito() {
        return mExito;
    }


    public LiveData<String> getErrorContraseniaActual() {
        return mErrorContraseniaActual;
    }
    public LiveData<String> getErrorNuevaContrasenia() {
        return mErrorNuevaContrasenia;
    }

    public LiveData<String> getErrorRepetirNuevaContrasenia() {
        return mErrorRepetirNuevaContrasenia;
    }




    public void cambiarPassword(String contraseniaActual, String nuevaContrasenia, String repeticionNuevaContrasenia){

        boolean valido = validarPasswords(contraseniaActual, nuevaContrasenia, repeticionNuevaContrasenia);

        if (valido) {
            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();
            Call<JsonObject> call = service.cambiarPassword("Bearer " + token, contraseniaActual, nuevaContrasenia);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        mExito.setValue("Contraseña cambiada con éxito");
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }

    }

    public boolean validarPasswords(String contraseniaActual, String nuevaContrasenia, String repeticionNuevaContrasenia) {
        boolean valido = true;


        if (contraseniaActual == null || contraseniaActual.trim().isEmpty()) {
            mErrorContraseniaActual.setValue("Ingrese la contraseña actual");
            valido = false;

        }
        if (nuevaContrasenia == null || nuevaContrasenia.trim().isEmpty()) {
            mErrorNuevaContrasenia.setValue("Ingrese una nueva contraseña");
            valido = false;
        }
        if (repeticionNuevaContrasenia == null || repeticionNuevaContrasenia.trim().isEmpty()) {
            mErrorRepetirNuevaContrasenia.setValue("Ingrese nuevamente la nueva contraseña");
            valido = false;
        }

        //validar que las contraseñas sean iguales
        if (nuevaContrasenia.equals(repeticionNuevaContrasenia) == false) {
            mErrorRepetirNuevaContrasenia.setValue("Las contraseñas no coinciden");
            valido = false;
        }

        return valido;


    }


}