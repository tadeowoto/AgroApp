package com.example.agroapp.menu.ui.gestion.actividad.detalleActividad;

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
import com.example.agroapp.model.insumo.Insumo;
import com.example.agroapp.model.lote.Lote;
import com.example.agroapp.model.recurso.Recurso;
import com.example.agroapp.model.tipoActividad.TipoActividad;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleActividadViewModel extends AndroidViewModel {

    MutableLiveData<Actividad> mActividad = new MutableLiveData<>();
    MutableLiveData<Recurso> mRecurso = new MutableLiveData<>();
    MutableLiveData<Insumo> mInsumo = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();
    MutableLiveData<Lote> mLote = new MutableLiveData<>();
    MutableLiveData<TipoActividad> mTipoActividad = new MutableLiveData<>();




    public DetalleActividadViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Actividad> getActividad() {
        return mActividad;
    }
    public LiveData<TipoActividad> getTipoActividad() {
        return mTipoActividad;
    }

    public LiveData<String> getError() {
        return mError;
    }
    public LiveData<Recurso> getRecurso() {
        return mRecurso;
    }
    public LiveData<Insumo> getInsumo() {
        return mInsumo;
    }
    public LiveData<Lote> getLote() {
        return mLote;
    }

    public void cargarVista(Actividad a) {
        mActividad.postValue(a);
        cargarLotePorId(a.getId_lote());
        cargarTipoDeActividadPorId(a.getId_tipo_actividad());
        if(a.getId_insumo() != null){
            cargarInsumoPorId(a.getId_insumo());
        }
        if(a.getId_recurso() != null){
            cargarRecursoPorId(a.getId_recurso());
        }
    }

    private void cargarTipoDeActividadPorId(int idTipoActividad) {
        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();
        try{
            Call<TipoActividad> call = service.obtenerActividadPorId("Bearer " + token, idTipoActividad);
            call.enqueue(new Callback<TipoActividad>() {
                @Override
                public void onResponse(Call<TipoActividad> call, Response<TipoActividad> response) {
                    mTipoActividad.postValue(response.body());
                }

                @Override
                public void onFailure(Call<TipoActividad> call, Throwable t) {
                    mError.postValue("Error de conexión: " + t.getMessage());

                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarRecursoPorId(int idRecurso) {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        try{
            Call<Recurso> call = service.obtenerRecursoPorId("Bearer " + token, idRecurso);

            call.enqueue(new Callback<Recurso>() {
                @Override
                public void onResponse(Call<Recurso> call, Response<Recurso> response) {
                    mRecurso.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Recurso> call, Throwable t) {
                    mError.postValue("Error de conexión: " + t.getMessage());
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarInsumoPorId(int idInsumo) {
        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();
        try{
            Call<Insumo> call = service.obtenerInsumoPorId("Bearer " + token, idInsumo);
            call.enqueue(new Callback<Insumo>() {
                @Override
                public void onResponse(Call<Insumo> call, Response<Insumo> response) {
                    mInsumo.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Insumo> call, Throwable t) {
                    mError.postValue("Error de conexión: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarLotePorId(int idLote) {
        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();
        try{
            Call<Lote> call = service.obtenerLotePorId("Bearer " + token, idLote);
            call.enqueue(new Callback<Lote>() {
                @Override
                public void onResponse(Call<Lote> call, Response<Lote> response) {
                    mLote.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Lote> call, Throwable t) {
                    mError.postValue("Error de conexión: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}