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




    ;
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
    }
}
