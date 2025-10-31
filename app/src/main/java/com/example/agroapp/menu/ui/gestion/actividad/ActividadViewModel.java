package com.example.agroapp.menu.ui.gestion.actividad;

import android.app.Application;
import android.app.Service;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.actividad.Actividad;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadViewModel extends AndroidViewModel {

    MutableLiveData<List<Actividad>> mListaActividades = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();

    public ActividadViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Actividad>> getListaActividades() {
        return mListaActividades;
    }

    public LiveData<String> getmError() {
        return mError;
    }




    public void cargarActividades() {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        try{

            Call<List<Actividad>> call = service.obtenerActividadesDelUsuario("Bearer " + token);

            call.enqueue(new Callback<List<Actividad>>() {
                @Override
                public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                    mListaActividades.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Actividad>> call, Throwable t) {
                    mError.postValue("Error de conexión: " + t.getMessage());
                }
            });

        }catch (Exception e){
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

}