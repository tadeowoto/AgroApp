package com.example.agroapp.menu.ui.gestion.cosecha.cosechasDeUnLote;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.cosecha.Cosecha;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CosechasDeUnLoteViewModel extends AndroidViewModel {

    MutableLiveData<List<Cosecha>> listaCosechas = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();
    MutableLiveData<String> mExito = new MutableLiveData<>();






    public CosechasDeUnLoteViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<List<Cosecha>> getListaCosechas() {
        return listaCosechas;
    }
    public MutableLiveData<String> getError() {
        return mError;
    }
    public MutableLiveData<String> getExito() {
        return mExito;
    }





    public void cargarCosechas(int id_lote) {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();


        try{
            Call<List<Cosecha>> call = service.obtenerCosechasDelLote("Bearer " + token, id_lote);

            call.enqueue(new Callback<List<Cosecha>>() {
                @Override
                public void onResponse(Call<List<Cosecha>> call, Response<List<Cosecha>> response) {
                    listaCosechas.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Cosecha>> call, Throwable t) {
                    mError.postValue("Error al obtener las cosechas: " + t.getMessage());
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}