package com.example.agroapp.menu.ui.mapa;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.campo.Campo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Campo>> mCampos = new MutableLiveData<>();

    public MapaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Campo>> getCampos() {
        return mCampos;
    }

    public void cargarCampos(){
        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        Call<List<Campo>> call = service.obtenerCampos("Bearer " + token);

        try{
            call.enqueue(new Callback<List<Campo>>() {
                @Override
                public void onResponse(Call<List<Campo>> call, Response<List<Campo>> response) {
                    if(response.isSuccessful() && response.body() != null){
                        mCampos.postValue(response.body());
                    } else {
                        Toast.makeText(getApplication(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<Campo>> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error al obtener los campos: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}