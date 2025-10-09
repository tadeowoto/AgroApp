package com.example.agroapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    private MutableLiveData<String> mErrorPassword = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginExitoso = new MutableLiveData<>();





    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getErrorPassword() {
        return mErrorPassword;
    }

    public LiveData<Boolean> getLoginExitoso() {
        return mLoginExitoso;
    }

    public void validarUsuario(String usuario, String password) {
        boolean valido = true;

        mErrorNombre.setValue(null);
        mErrorPassword.setValue(null);

        if (usuario == null || usuario.trim().isEmpty()) {
            mErrorNombre.setValue("Ingrese un usuario");
            valido = false;
        }

        if (password == null || password.trim().isEmpty()) {
            mErrorPassword.setValue("Ingrese una contraseña");
            valido = false;
        }

        if (valido) {
            if (usuario.trim().equals("juan@perez.com") && password.trim().equals("1234")) {
                mLoginExitoso.setValue(true);
            } else {
                mErrorPassword.setValue("Usuario o contraseña incorrectos");
            }
        }


/*
 todo-->IMPLEMENTAR ESTO CUANDO ARME EL LOGIN DE LOS USUARIOS

        if (valido) {
            ApiCLient.appService service = ApiCLient.getService();
            Call<String> call = service.login(usuario, password);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        String token = response.body();
                        Services.guardarToken(context, token);
                        mLoginExitoso.postValue(true);
                    } else {
                        mErrorPassword.postValue("Usuario o contraseña incorrectos");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        */

    }
}
