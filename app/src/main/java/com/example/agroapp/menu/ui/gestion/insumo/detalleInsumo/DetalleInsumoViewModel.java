package com.example.agroapp.menu.ui.gestion.insumo.detalleInsumo;

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
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInsumoViewModel extends AndroidViewModel {

    private MutableLiveData<Insumo> mInsumo = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    private MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    private MutableLiveData<Boolean> mGuardarInsumo  = new MutableLiveData<>();
    private MutableLiveData<List<String>> mListaTipo = new MutableLiveData<>();
    private MutableLiveData<List<String>> mListaUnidad = new MutableLiveData<>();
    private MutableLiveData<String> mExito = new MutableLiveData<>();

    private MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    private MutableLiveData<String> mErrorTipo = new MutableLiveData<>();
    private MutableLiveData<String> mErrorUnidad = new MutableLiveData<>();
    private MutableLiveData<String> mErrorStock = new MutableLiveData<>();
    private MutableLiveData<String> mErrorFechaVenc = new MutableLiveData<>();









    public DetalleInsumoViewModel(@NonNull Application application) {
        super(application);
        cargarListas();
    }

    public LiveData<String> getmErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getmErrorTipo() {
        return mErrorTipo;
    }
    public LiveData<String> getmErrorUnidad() {
        return mErrorUnidad;
    }
    public LiveData<String> getmExito() {
        return mExito;
    }
    public LiveData<String> getmErrorStock() {
        return mErrorStock;
    }
    public LiveData<String> getmErrorFechaVenc() {
        return mErrorFechaVenc;
    }

    public LiveData<List<String>> getmListaTipo() {
        return mListaTipo;
    }
    public LiveData<List<String>> getmListaUnidad() {
        return mListaUnidad;
    }
    public LiveData<Insumo> getInsumo() {
        return mInsumo;
    }
    public LiveData<String> getError() {
        return mError;
    }
    public LiveData<Boolean> getHabilitarCampos() {
        return mHabilitarCampos;
    }
    public LiveData<String> getTextoBoton() {
        return mTextoBoton;
    }
    public LiveData<Boolean> getGuardarInsumo() {
        return mGuardarInsumo;
    }


    private void cargarListas() {
        List<String> unidades = Arrays.asList("Kilos", "Libras", "Gramos", "Litros", "Metros", "Bolsas");
        mListaUnidad.setValue(unidades);
        List<String> tipos = Arrays.asList("Alimento", "Herbicida", "Fungicida", "Fertilizante", "Semillas");
        mListaTipo.setValue(tipos);
    }

    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarInsumo.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }



    public void cargarInsumo(Insumo insumo) {
        mInsumo.setValue(insumo);
    }


    public boolean valido ( String nombre, String tipo, String unidad, String stock, String fechaVenc){
        boolean valido = true;
        if (nombre.isEmpty()){
            mErrorNombre.postValue("El nombre no puede estar vacio");
            valido = false;
        }
        if (tipo.isEmpty()){
            mErrorTipo.postValue("El tipo no puede estar vacio");
            valido = false;
        }
        if (unidad.isEmpty()) {
            mErrorUnidad.postValue("La unidad no puede estar vacio");
            valido = false;
        }

        Date fechaVencimiento = null;

        if (fechaVenc == null || fechaVenc.trim().isEmpty()) {
            mErrorFechaVenc.postValue("Debe ingresar una fecha de fin");
            valido = false;
        } else {
            fechaVencimiento = Services.parseFecha(fechaVenc);
            if (fechaVencimiento == null) {
                mErrorFechaVenc.postValue("Formato de fecha inválido (use dd/MM/yyyy)");
                valido = false;
            }
            mErrorFechaVenc.postValue(null);
        }
        return valido;
    }


    public void editarInsumo(String nombre, String tipo, String unidad, String stock, String fechaVenc) {
        boolean valido = valido(nombre, tipo, unidad, stock, fechaVenc);
        if (valido){
            Double stockActual = (stock == null || stock.isEmpty()) ? null : Double.parseDouble(stock);
            Date fechaVencimiento = Services.parseFecha(fechaVenc);

            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();
            try{
                Insumo i = new Insumo(mInsumo.getValue().getId_insumo(), nombre, tipo, unidad, stockActual, fechaVencimiento);
                Call<JsonObject> call = service.actualizarInsumo("Bearer " + token, mInsumo.getValue().getId_insumo(),i);

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        mExito.postValue("Insumo actualizado correctamente");
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mError.postValue("Error al actualizar insumo: " + t.getMessage());
                    }
                });

            }catch (Exception e){
                Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}