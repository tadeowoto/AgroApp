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
import com.example.agroapp.model.dto.ActividadDto;
import com.example.agroapp.model.insumo.Insumo;
import com.example.agroapp.model.lote.Lote;
import com.example.agroapp.model.recurso.Recurso;
import com.example.agroapp.model.tipoActividad.TipoActividad;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.Date;

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
    MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    MutableLiveData<Boolean> mGuardarActividad = new MutableLiveData<>();

    MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    MutableLiveData<String> mErrorCosto = new MutableLiveData<>();
    MutableLiveData<String> mErrorSuperficie = new MutableLiveData<>();
    MutableLiveData<String> mErrorFechaInicio = new MutableLiveData<>();
    MutableLiveData<String> mErrorFechaFin = new MutableLiveData<>();
    MutableLiveData<String> mErrorCantidadInsumo = new MutableLiveData<>();
    MutableLiveData<String> mErrorDescripcion = new MutableLiveData<>();
    MutableLiveData<String> mExito = new MutableLiveData<>();













    public DetalleActividadViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Actividad> getActividad() {
        return mActividad;
    }
    public LiveData<Boolean> getHabilitarCampos() {
        return mHabilitarCampos;
    }
    public LiveData<String> getTextoBoton() {
        return mTextoBoton;
    }
    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getErrorDescripcion() {
        return mErrorDescripcion;
    }
    public LiveData<String> getErrorSuperficie() {
        return mErrorSuperficie;
    }
    public LiveData<String> getErrorFechaInicio() {
        return mErrorFechaInicio;
    }
    public LiveData<String> getErrorFechaFin() {
        return mErrorFechaFin;
    }
    public LiveData<String> getExito() {
        return mExito;
    }
    public LiveData<String> getErrorCantidadInsumo() {
        return mErrorCantidadInsumo;
    }
    public LiveData<String> getErrorCosto() {
        return mErrorCosto;
    }
    public LiveData<Boolean> getGuardarActividad() {
        return mGuardarActividad;
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

    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarActividad.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }

    private boolean valido(String descripcion, String fechaInicioStr, String fechaFinStr, String cantidadInsumoStr, String costoStr) {
        boolean esValido = true;


        if (descripcion == null || descripcion.trim().isEmpty()) {
            mErrorDescripcion.setValue("Debe ingresar una descripción");
            esValido = false;
        }

        Date fechaInicio = null;
        if (fechaInicioStr == null || fechaInicioStr.trim().isEmpty()) {
            mErrorFechaInicio.setValue("Debe ingresar una fecha de inicio");
            esValido = false;
        } else {
            fechaInicio = Services.parseFecha(fechaInicioStr);
            if (fechaInicio == null) {
                mErrorFechaInicio.setValue("Formato de fecha inválido (dd/MM/yyyy)");
                esValido = false;
            }
        }

        Date fechaFin = null;
        if (fechaFinStr == null || fechaFinStr.trim().isEmpty()) {
            mErrorFechaFin.setValue("Debe ingresar una fecha de fin");
            esValido = false;
        } else {
            fechaFin = Services.parseFecha(fechaFinStr);
            if (fechaFin == null) {
                mErrorFechaFin.setValue("Formato de fecha inválido (dd/MM/yyyy)");
                esValido = false;
            }
        }
        if (fechaInicio != null && fechaFin != null) {
            if (fechaFin.before(fechaInicio)) {
                mErrorFechaFin.setValue("La fecha de fin no puede ser anterior a la de inicio");
                esValido = false;
            }
        }

        if (cantidadInsumoStr != null && !cantidadInsumoStr.trim().isEmpty()) {
            try {
                Double.parseDouble(cantidadInsumoStr);
            } catch (NumberFormatException e) {
                mErrorCantidadInsumo.setValue("Debe ser un número válido");
                esValido = false;
            }
        }
        if (costoStr == null || costoStr.trim().isEmpty()) {
            mErrorCosto.setValue("Debe ingresar un costo");
            esValido = false;
        } else {
            try {
                Double.parseDouble(costoStr);
            } catch (NumberFormatException e) {
                mErrorCosto.setValue("Debe ser un número válido (ej: 12500.50)");
                esValido = false;
            }
        }

        return esValido;
    }




    public void actualizarActividad(String descripcion, String fechaInicio, String fechaFin, String cantidadInsumo, String costo) {
        boolean valido = valido(descripcion, fechaInicio, fechaFin, cantidadInsumo, costo);
        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        try{
            if (valido){
                ActividadDto actividadDto = new ActividadDto(descripcion, Services.parseFecha(fechaInicio), Services.parseFecha(fechaFin), Double.parseDouble(cantidadInsumo), Double.parseDouble(costo));
                Call<JsonObject> call = service.actualizarActividad("Bearer " + token, mActividad.getValue().getIdActividad(),actividadDto);

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        mExito.postValue("Actividad actualizada");
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mError.postValue("Error de conexión: " + t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}