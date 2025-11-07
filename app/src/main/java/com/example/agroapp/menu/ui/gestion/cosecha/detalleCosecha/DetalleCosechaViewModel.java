package com.example.agroapp.menu.ui.gestion.cosecha.detalleCosecha;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.cosecha.Cosecha;
import com.google.gson.JsonObject;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleCosechaViewModel extends AndroidViewModel {

    private MutableLiveData<Cosecha> mCosecha = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    private MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    private MutableLiveData<Boolean> mGuardarCosecha  = new MutableLiveData<>();
    private MutableLiveData<String> mExito = new MutableLiveData<>();
    private MutableLiveData<String> mErrorFechaInicio = new MutableLiveData<>();
    private MutableLiveData<String> mErrorFechaFin = new MutableLiveData<>();
    private MutableLiveData<String> mErrorRendimiento = new MutableLiveData<>();
    private MutableLiveData<String> mErrorObservaciones = new MutableLiveData<>();





    public DetalleCosechaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getmError() {
        return mError;
    }
    public LiveData<String> getmExito() {
        return mExito;
    }
    public LiveData<Boolean> getHabilitarCampos() {
        return mHabilitarCampos;
    }
    public LiveData<String> getTextoBoton() {
        return mTextoBoton;
    }
    public LiveData<Boolean> getGuardarCosecha() {
        return mGuardarCosecha;
    }
    public LiveData<Cosecha> getCosecha() {
        return mCosecha;
    }
    public LiveData<String> getErrorFechaInicio() {
        return mErrorFechaInicio;
    }
    public LiveData<String> getErrorFechaFin() {
        return mErrorFechaFin;
    }
    public LiveData<String> getErrorRendimiento() {
        return mErrorRendimiento;
    }
    public LiveData<String> getErrorObservaciones() {
        return mErrorObservaciones;
    }










    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarCosecha.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }






    public void cargarCosecha(Cosecha c) {
        mCosecha.setValue(c);
    }

    public boolean valido(String fechaInicio, String fechaFin, String rendimiento, String observaciones) {
        boolean valido = true;

        Date fInicio = Services.parseFecha(fechaInicio);
        Date fFin = Services.parseFecha(fechaFin);
        Date hoy = new Date();


        if (fechaInicio == null || fechaInicio.trim().isEmpty()) {
            mErrorFechaInicio.postValue("Debe ingresar una fecha de inicio");
            valido = false;
        } else if (fInicio == null) {
            mErrorFechaInicio.postValue("Formato de fecha inválido (use dd/MM/yyyy)");
            valido = false;
        } else if (fInicio.after(hoy)) {
            mErrorFechaInicio.postValue("La fecha de inicio no puede ser futura");
            valido = false;
        } else {
            mErrorFechaInicio.postValue(null);
        }

        if (fechaFin == null || fechaFin.trim().isEmpty()) {
            mErrorFechaFin.postValue("Debe ingresar una fecha de fin");
            valido = false;
        } else if (fFin == null) {
            mErrorFechaFin.postValue("Formato de fecha inválido (use dd/MM/yyyy)");
            valido = false;
        } else if (fInicio != null && fFin.before(fInicio)) {
            mErrorFechaFin.postValue("La fecha de fin no puede ser anterior a la fecha de inicio");
            valido = false;
        } else {
            mErrorFechaFin.postValue(null);
        }

        if (rendimiento == null || rendimiento.trim().isEmpty()) {
            mErrorRendimiento.postValue("Debe ingresar un rendimiento");
            valido = false;
        } else {
            try {
                double valor = Double.parseDouble(rendimiento);
                if (valor <= 0) {
                    mErrorRendimiento.postValue("El rendimiento debe ser mayor que 0");
                    valido = false;
                } else {
                    mErrorRendimiento.postValue(null);
                }
            } catch (NumberFormatException e) {
                mErrorRendimiento.postValue("Rendimiento inválido (use números)");
                valido = false;
            }
        }
        if (observaciones == null || observaciones.trim().isEmpty()) {
            mErrorObservaciones.postValue("Debe ingresar observaciones");
            valido = false;
        } else {
            mErrorObservaciones.postValue(null);
        }

        return valido;
    }





    public void editarCosecha(String fechaInicio, String fechaFin, String rendimiento, String observaciones) {

        boolean valido = valido(fechaInicio, fechaFin, rendimiento, observaciones);

        if (valido) {
            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();
            Double rendimientoDouble = Double.parseDouble(rendimiento);

            Date fechad= Services.parseFecha(fechaInicio);
            Date fechaFinDate = Services.parseFecha(fechaFin);

            Cosecha c = new Cosecha(mCosecha.getValue().getId_cosecha(), mCosecha.getValue().getId_lote(), fechad, fechaFinDate, rendimientoDouble, observaciones);

            try{
                Call<JsonObject> call = service.actualizarCosecha("Bearer " + token, mCosecha.getValue().getId_cosecha(),c);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            mExito.postValue("Cosecha actualizada correctamente");
                        } else {
                            Log.d(
                                    "DETALLE_COSECHA",
                                    "Error al actualizar cosecha: " + response.code());
                            mError.postValue("Error al actualizar cosecha: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mError.postValue("Error al actualizar cosecha: " + t.getMessage());
                    }
                });
            } catch (Exception e) {
                Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}