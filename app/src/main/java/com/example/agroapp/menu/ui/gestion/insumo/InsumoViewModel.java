package com.example.agroapp.menu.ui.gestion.insumo;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.insumo.Insumo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsumoViewModel extends AndroidViewModel {

    MutableLiveData<List<Insumo>> mListaInsumo = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();



    public InsumoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Insumo>> getListaInsumo() {
        return mListaInsumo;
    }



    public void cargarLista() {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();
        Call<List<Insumo>> call = service.obtenerInsumosDelUsuario("Bearer " + token);

        try{

            call.enqueue(new Callback<List<Insumo>>() {
                @Override
                public void onResponse(Call<List<Insumo>> call, Response<List<Insumo>> response) {
                    if (response.isSuccessful()){
                        mListaInsumo.setValue(response.body());
                    }else{
                        mError.setValue("La respuesta no fue exitosa");
                    }
                }

                @Override
                public void onFailure(Call<List<Insumo>> call, Throwable t) {
                    mError.setValue("Error al obtener los insumos");
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplication(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }
}