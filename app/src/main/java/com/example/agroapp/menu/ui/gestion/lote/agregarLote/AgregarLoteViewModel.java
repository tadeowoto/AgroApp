package com.example.agroapp.menu.ui.gestion.lote.agregarLote;

import android.app.Application;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.campo.Campo;
import com.example.agroapp.model.dto.LoteDto;
import com.example.agroapp.model.lote.Lote;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarLoteViewModel extends AndroidViewModel {

    MutableLiveData<String> mNombreCampo = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();
    MutableLiveData<String> mExito = new MutableLiveData<>();

    MutableLiveData<String> mErrorSupeficie = new MutableLiveData<>();
    MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    MutableLiveData<String> mErrorCultivo = new MutableLiveData<>();


    private int idCampoAsignado;



    public AgregarLoteViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getmExito() {
        return mExito;
    }

    public LiveData<String> getmNombreCampo() {
        return mNombreCampo;
    }
    public LiveData<String> getmError(){
        return mError;
    }
    public LiveData<String> getmErrorSupeficie() {
        return mErrorSupeficie;
    }
    public LiveData<String> getmErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getmErrorCultivo() {
        return mErrorCultivo;
    }







    public void cargarNombreCampo(int idCampo) {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        Call<Campo> call = service.obtenerCampoPorId("Bearer " + token, idCampo);

        call.enqueue(new Callback<Campo>() {
            @Override
            public void onResponse(Call<Campo> call, Response<Campo> response) {
                mNombreCampo.postValue(response.body().getNombre());
                idCampoAsignado = response.body().getId_campo();
            }

            @Override
            public void onFailure(Call<Campo> call, Throwable t) {
                mError.postValue("Error al obtener el nombre del campo");
            }
        });

    }

    public boolean valido( String nombre, String cultivo, String superficie){
        boolean valido = true;

        if(nombre.isEmpty()){
            mErrorNombre.postValue("El nombre del lote no puede estar vacío");
            valido = false;
        }
        if(cultivo.isEmpty()){
            mErrorCultivo.postValue("El cultivo no puede estar vacío");
            valido = false;
        }
        if(superficie.isEmpty()){
            mErrorSupeficie.postValue("La superficie no puede estar vacía");
            valido = false;
        }
        return valido;
    }


    public void agregarLote( String nombre, String cultivo, String superficie) {

        boolean valido = valido(nombre, cultivo, superficie);

        if (valido) {
            try{
                String token = Services.leerToken(getApplication());
                ApiCLient.appService service = ApiCLient.getService();
                double superficieDouble = Double.parseDouble(superficie);
                LoteDto l = new LoteDto(idCampoAsignado, nombre, cultivo, superficieDouble);

                Call<JsonObject> call = service.agregarLote("Bearer " + token, l);

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            mExito.postValue("Lote agregado");
                        }else{
                            Log.e("Error", response.message());
                            mError.postValue("Error al agregar el lote");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        mError.postValue("Error al agregar el lote");
                    }
                });
            }catch (Exception e){
                Toast.makeText(getApplication(), "Error de conexión: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        }
}