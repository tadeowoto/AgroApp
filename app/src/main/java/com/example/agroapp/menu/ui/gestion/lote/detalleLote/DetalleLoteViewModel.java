package com.example.agroapp.menu.ui.gestion.lote.detalleLote;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.lote.Lote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleLoteViewModel extends AndroidViewModel {

    MutableLiveData<List<Lote>> mListaLotes = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();


    public DetalleLoteViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Lote>> getListaLotes() {
        return mListaLotes;
    }
    public LiveData<String> getmError(){
        return mError;
    }





    public void cargarLotes(int idCampo) {

        try{
            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();

            Call<List<Lote>> call = service.obtenerLotesPorIdCampo("Bearer " + token, idCampo);

            call.enqueue(new Callback<List<Lote>>() {
                @Override
                public void onResponse(Call<List<Lote>> call, Response<List<Lote>> response) {
                    if (response.isSuccessful()) {
                        mListaLotes.postValue(response.body());
                    }else{
                        mError.postValue("Error al obtener los lotes");
                    }

                }

                @Override
                public void onFailure(Call<List<Lote>> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}