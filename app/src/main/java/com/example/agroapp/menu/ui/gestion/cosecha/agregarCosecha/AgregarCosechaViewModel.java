package com.example.agroapp.menu.ui.gestion.cosecha.agregarCosecha;

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

public class AgregarCosechaViewModel extends AndroidViewModel {

    private MutableLiveData<String> mErrorFechaInicio = new MutableLiveData<>();
    private MutableLiveData<String> mErrorFechaFin = new MutableLiveData<>();
    private MutableLiveData<String> mErrorRendimiento = new MutableLiveData<>();
    private MutableLiveData<String> mErrorObservaciones = new MutableLiveData<>();
    private MutableLiveData<String> mExito = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();





    public AgregarCosechaViewModel(@NonNull Application application) {
        super(application);
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
    public LiveData<String> getmExito() {
        return mExito;
    }
    public LiveData<String> getmError() {
        return mError;
    }






    public void guardarCosecha(String fechaInicio, String fechaFin, String rendimiento, String observaciones, int idLote) {
        boolean valido = valido(fechaInicio, fechaFin, rendimiento, observaciones);
        Log.d("salida", String.valueOf(idLote));
        if (valido) {
            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();

            Date fechad= Services.parseFecha(fechaInicio);
            Date fechaFinDate = Services.parseFecha(fechaFin);
            Double rendimientoDouble = Double.parseDouble(rendimiento);

            try{
                Cosecha c = new Cosecha(idLote, fechad, fechaFinDate, rendimientoDouble, observaciones);

                Call<JsonObject> call = service.agregarCosecha("Bearer " + token, c);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            mExito.postValue("Cosecha agregada con éxito");
                        }else{
                            mError.postValue("no se pudo guardar");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mError.postValue("Error al agregar cosecha");
                    }
                });


            } catch (Exception e) {
                Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
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
}