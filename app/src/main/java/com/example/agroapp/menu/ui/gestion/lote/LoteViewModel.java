package com.example.agroapp.menu.ui.gestion.lote;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoteViewModel extends AndroidViewModel {

    MutableLiveData<List<Campo>> mListaCampos = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();


    public LoteViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Campo>> getListaCampos() {
        return mListaCampos;
    }
    public LiveData<String> getmError(){
        return mError;
    }







    public void cargarCampos(){
        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        Call<List<Campo>> call = service.obtenerCampos("Bearer " + token);

        call.enqueue(new Callback<List<Campo>>() {
            @Override
            public void onResponse(Call<List<Campo>> call, Response<List<Campo>> response) {
                if (response.isSuccessful()) {
                    if(response.body().isEmpty()){
                        mError.postValue("No hay campos");
                    }else{
                        mListaCampos.postValue(response.body());
                    }
                }else{
                    mError.postValue("Error al obtener los campos");
                }
            }

            @Override
            public void onFailure(Call<List<Campo>> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}