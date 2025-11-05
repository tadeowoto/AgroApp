package com.example.agroapp.menu.ui.gestion.recurso.editarRecurso;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.recurso.Recurso;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarRecursoViewModel extends AndroidViewModel {

    private MutableLiveData<List<String>> mListaTipo = new MutableLiveData<List<String>>();
    private MutableLiveData<List<String>> mListaEstado = new MutableLiveData<List<String>>();
    private MutableLiveData<Recurso> mRecurso = new MutableLiveData<>();
    private MutableLiveData<String> mErrorNombre = new MutableLiveData<String>();
    private MutableLiveData<String> mErrorTipo = new MutableLiveData<String>();
    private MutableLiveData<String> mErrorMarca = new MutableLiveData<String>();
    private MutableLiveData<String> mErrorModelo = new MutableLiveData<String>();
    private MutableLiveData<String> mExito = new MutableLiveData<String>();
    private MutableLiveData<String> mError = new MutableLiveData<String>();
    private MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    private MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    private MutableLiveData<Boolean> mGuardarRecurso = new MutableLiveData<>();




    public EditarRecursoViewModel(@NonNull Application application) {
        super(application);
        cargarListas();
    }
    public LiveData<Boolean> getGuardarRecurso() {
        return mGuardarRecurso;
    }
    public LiveData<Recurso> getRecurso() {
        return mRecurso;
    }
    public LiveData<String> getmExito() {
        return mExito;
    }
    public LiveData<String> getmError() {
        return mError;
    }
    public LiveData<Boolean> getHabilitarCampos() {
        return mHabilitarCampos;
    }
    public LiveData<String> getTextoBoton() {
        return mTextoBoton;
    }
    public LiveData<List<String>> getmListaTipo() {
        return mListaTipo;
    }

    public LiveData<List<String>> getmListaEstado() {
        return mListaEstado;
    }
    public LiveData<String> getmErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getmErrorTipo() {
        return mErrorTipo;
    }
    public LiveData<String> getmErrorMarca() {
        return mErrorMarca;
    }
    public LiveData<String> getmErrorModelo() {
        return mErrorModelo;
    }


    public boolean valido( String nombre, String tipo, String marca, String modelo){
        boolean valido = true;
        if (nombre.isEmpty()){
            mErrorNombre.postValue("El nombre no puede estar vacio");
            valido = false;
        }
        if (tipo.isEmpty()){
            mErrorTipo.postValue("El tipo no puede estar vacio");
            valido = false;
        }
        if (marca.isEmpty()){
            mErrorMarca.postValue("La marca no puede estar vacio");
            valido = false;
        }
        if (modelo.isEmpty()){
            mErrorModelo.postValue("El modelo no puede estar vacio");
            valido = false;
        }
        return valido;
    }
    private void cargarListas() {
        List<String> estados = Arrays.asList("Operativo", "En uso", "En Mantenimiento", "Fuera de servicio");
        mListaEstado.setValue(estados);
        List<String> tipos = Arrays.asList("Maquinaria", "Herramienta", "Material", "Vehiculo");
        mListaTipo.setValue(tipos);
    }

    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarRecurso.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }



    public void editarRecurso(String nombre, String tipo, String marca, String modelo, String estado) {

        boolean valido = valido(nombre, tipo, marca, modelo);
        if (valido){
            String token = Services.leerToken(getApplication());
            ApiCLient.appService service = ApiCLient.getService();
            Recurso recurso = new Recurso(nombre, tipo, marca, modelo, estado);

            try{
                Call<JsonObject> call = service.actualizarRecurso("Bearer " + token, mRecurso.getValue().getId_recurso(),recurso);

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        mExito.postValue("Recurso actualizado correctamente");
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mError.postValue("Error al actualizar el recurso");
                    }
                });

            } catch (Exception e) {
                Toast.makeText(getApplication(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void cargarElRecurso(Recurso r) {
        mRecurso.setValue(r);
    }
}