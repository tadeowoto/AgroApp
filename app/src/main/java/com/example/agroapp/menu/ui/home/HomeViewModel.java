package com.example.agroapp.menu.ui.home;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.actividad.Actividad;
import com.example.agroapp.model.cosecha.Cosecha;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    MutableLiveData<Integer> mRecursos = new MutableLiveData<>();
    MutableLiveData<Integer> mLotes = new MutableLiveData<>();
    MutableLiveData<Integer> mInsumos = new MutableLiveData<>();
    MutableLiveData<Integer> mCampos = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();



    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Integer> getmRecursos() {
        return mRecursos;
    }
    public LiveData<Integer> getmCampos() {
        return mCampos;
    }
    public LiveData<Integer> getmLotes() {
        return mLotes;
    }
    public LiveData<Integer> getmInsumos() {
        return mInsumos;
    }

    public LiveData<String> getmError() {
        return mError;
    }


    public void cargarHome() {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        Call<Integer> callRecursos = service.obtenerRecursosPorCantidad("Bearer " + token);

        try{
            callRecursos.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        mRecursos.postValue(response.body());
                    }else{
                        mError.postValue("Error al obtener recursos");
                        mRecursos.postValue(0);
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    mError.postValue("Error al obtener recursos");
                    mRecursos.postValue(0);
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error en recursos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Call<Integer> callLotes = service.obtenerLotesPorCantidad("Bearer " + token);
        try{
            callLotes.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        mLotes.postValue(response.body());
                    }else {
                        mError.postValue("Error al obtener lotes");
                        mLotes.postValue(0);
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    mError.postValue("Error al obtener lotes");
                    mLotes.postValue(0);
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error en lotes: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Call<Integer> callInsumos = service.obtenerInsumosPorCantidad("Bearer " + token);
        try {
            callInsumos.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        mInsumos.postValue(response.body());
                    }
                    else {
                        mError.postValue("Error al obtener insumos");
                        mInsumos.postValue(0);
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    mError.postValue("Error al obtener insumos");
                    mInsumos.postValue(0);
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplication(), "Error en insumos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Call<Integer> callCampos = service.obtenercamposPorCantidad("Bearer " + token);
        try{
            callCampos.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        mCampos.postValue(response.body());
                    }else {
                        mError.postValue("Error al obtener campos");
                        mCampos.postValue(0);
                    }

                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    mError.postValue("Error al obtener campos");
                    mCampos.postValue(0);
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplication(), "Error en campos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}