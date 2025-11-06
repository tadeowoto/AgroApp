package com.example.agroapp.menu.ui.gestion.insumo.agregarInsumo;

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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInsumoViewModel extends AndroidViewModel {
    private MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    private MutableLiveData<String> mErrorTipo = new MutableLiveData<>();
    private MutableLiveData<String> mErrorUnidad = new MutableLiveData<>();
    private MutableLiveData<String> mErrorStock = new MutableLiveData<>();
    private MutableLiveData<String> mErrorFechaVenc = new MutableLiveData<>();
    private MutableLiveData<String> mExito = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<List<String>> mListaTipo = new MutableLiveData<>();
    private MutableLiveData<List<String>> mListaUnidad = new MutableLiveData<>();





    public AgregarInsumoViewModel(@NonNull Application application) {
        super(application);
        cargarListas();
    }

    public LiveData<String> getmExito() {
        return mExito;
    }
    public LiveData<String> getmError() {
        return mError;
    }
    public LiveData<List<String>> getmListaTipo() {
        return mListaTipo;
    }
    public LiveData<List<String>> getmListaUnidad() {
        return mListaUnidad;
    }

    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getErrorTipo() {
        return mErrorTipo;
    }
    public LiveData<String> getErrorUnidad() {
        return mErrorUnidad;
    }
    public LiveData<String> getErrorStock() {
        return mErrorStock;
    }
    public LiveData<String> getErrorFechaVenc() {
        return mErrorFechaVenc;
    }

    public boolean valido(String nombre, String tipo, String unidad, String stock, String fechaVenc) {
        boolean valido = true;

        // Validaciones básicas
        if (nombre == null || nombre.trim().isEmpty()) {
            mErrorNombre.postValue("El nombre no puede estar vacío");
            valido = false;
        } else {
            mErrorNombre.postValue(null);
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            mErrorTipo.postValue("El tipo no puede estar vacío");
            valido = false;
        } else {
            mErrorTipo.postValue(null);
        }

        if (unidad == null || unidad.trim().isEmpty()) {
            mErrorUnidad.postValue("La unidad no puede estar vacía");
            valido = false;
        } else {
            mErrorUnidad.postValue(null);
        }

        // Validación de fecha
        if (fechaVenc == null || fechaVenc.trim().isEmpty()) {
            mErrorFechaVenc.postValue("Debe ingresar una fecha de vencimiento");
            valido = false;
        } else {
            Date fechaVencimiento = Services.parseFecha(fechaVenc);
            if (fechaVencimiento == null) {
                mErrorFechaVenc.postValue("Formato de fecha inválido (use dd/MM/yyyy)");
                valido = false;
            } else {
                Date hoy = new Date();
                if (!fechaVencimiento.after(hoy)) {
                    mErrorFechaVenc.postValue("La fecha debe ser posterior al día de hoy");
                    valido = false;
                } else {
                    mErrorFechaVenc.postValue(null);
                }
            }
        }
        if (stock == null || stock.trim().isEmpty()) {
            mErrorStock.postValue("Debe ingresar el stock actual");
            valido = false;
        } else {
            try {
                double valorStock = Double.parseDouble(stock);
                if (valorStock < 0) {
                    mErrorStock.postValue("El stock no puede ser negativo");
                    valido = false;
                } else {
                    mErrorStock.postValue(null);
                }
            } catch (NumberFormatException e) {
                mErrorStock.postValue("Stock inválido, ingrese un número válido");
                valido = false;
            }
        }

        return valido;
    }


    private void cargarListas() {
        List<String> unidades = Arrays.asList("Kilos", "Libras", "Gramos", "Litros", "Metros", "Bolsas");
        mListaUnidad.setValue(unidades);
        List<String> tipos = Arrays.asList("Alimento", "Herbicida", "Fungicida", "Fertilizante", "Semillas");
        mListaTipo.setValue(tipos);
    }

    public void agregarInsumo(String nombre, String tipo, String unidad, String stock, String fechaVenc) {

        boolean valido = valido(nombre, tipo, unidad, stock, fechaVenc);
        if (valido){

            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();

            try{
                Double stockActual = (stock == null || stock.isEmpty()) ? null : Double.parseDouble(stock);
                Date fechaVencimiento = Services.parseFecha(fechaVenc);
                Insumo i = new Insumo( nombre, tipo, unidad, stockActual, fechaVencimiento);
                Call<Insumo> call = service.crearInsumo("Bearer " + token,i);

                call.enqueue(new Callback<Insumo>() {
                    @Override
                    public void onResponse(Call<Insumo> call, Response<Insumo> response) {
                        mExito.postValue("Insumo agregado correctamente");
                    }

                    @Override
                    public void onFailure(Call<Insumo> call, Throwable t) {
                        mError.postValue("Error al agregar insumo: " + t.getMessage());
                    }
                });
            } catch (Exception e) {
                Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}