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
            Call<String> call = service.cambiarPassword("Bearer " + token, contraseniaActual, nuevaContrasenia);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()){
                        mExito.postValue("Contraseña cambiada correctamente");
                    } else {
                        mErrorRepetirNuevaContrasenia.postValue("Error al cambiar la contraseña");
                        Log.e("Error", response.message() + " " + response.code() + " " + response.errorBody() + " " + response.raw());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();


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