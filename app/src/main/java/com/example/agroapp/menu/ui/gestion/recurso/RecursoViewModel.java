package com.example.agroapp.menu.ui.gestion.recurso;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.Usuario;
import com.example.agroapp.model.recurso.Recurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecursoViewModel extends AndroidViewModel {

    MutableLiveData<List<Recurso>> mLista = new MutableLiveData<List<Recurso>>();
    MutableLiveData<String> mError = new MutableLiveData<String>();


    public RecursoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Recurso>> getmLista(){
        return mLista;
    }

    public LiveData<String> getmError(){
        return mError;
    }


    public void cargarLista() {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();
        Call<List<Recurso>> call = service.obtenerRecursosDelUsuario("Bearer " + token);

        try {
            call.enqueue(new Callback<List<Recurso>>() {
                @Override
                public void onResponse(Call<List<Recurso>> call, Response<List<Recurso>> response) {
                    mLista.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Recurso>> call, Throwable t) {
                    mError.postValue("Error al cargar los recursos");
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }
}