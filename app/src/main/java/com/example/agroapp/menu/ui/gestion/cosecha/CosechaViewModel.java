package com.example.agroapp.menu.ui.gestion.cosecha;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CosechaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Lote>> mLotes = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();


    public CosechaViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Lote>> getListaLotes() {
        return mLotes;
    }
    public LiveData<String> getError() {
        return mError;
    }







    public void cargarLista() {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        try {
            Call<List<Lote>> call = service.obtenerLotesDelUsuario("Bearer " + token);

            call.enqueue(new Callback<List<Lote>>() {
                @Override
                public void onResponse(Call<List<Lote>> call, Response<List<Lote>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Lote> lotes = response.body();
                        mLotes.postValue(lotes);
                    } else {
                        mError.postValue("No se pudieron cargar los lotes");
                    }
                }

                @Override
                public void onFailure(Call<List<Lote>> call, Throwable t) {
                    mError.postValue("Error de conexión: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}