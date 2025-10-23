package com.example.agroapp.menu.ui.perfil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    MutableLiveData<Usuario> mUsuario = new MutableLiveData< Usuario>();
    MutableLiveData<String> mError = new MutableLiveData<String>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getmUsuario(){
        return mUsuario;
    }

    public LiveData<String> getmError(){
        return mError;
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

}