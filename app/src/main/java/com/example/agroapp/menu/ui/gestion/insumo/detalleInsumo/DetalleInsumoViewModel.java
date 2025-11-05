package com.example.agroapp.menu.ui.gestion.insumo.detalleInsumo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.model.insumo.Insumo;

import java.util.Arrays;
import java.util.List;

public class DetalleInsumoViewModel extends AndroidViewModel {

    private MutableLiveData<Insumo> mInsumo = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    private MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    private MutableLiveData<Boolean> mGuardarInsumo  = new MutableLiveData<>();
    private MutableLiveData<List<String>> mListaTipo = new MutableLiveData<>();
    private MutableLiveData<List<String>> mListaUnidad = new MutableLiveData<>();






    public DetalleInsumoViewModel(@NonNull Application application) {
        super(application);
        cargarListas();
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



    public void editarInsumo(String nombre, String tipo, String unidad, String stock, String fechaVenc) {
    }
}