package com.example.agroapp.menu.ui.gestion.lote.infoLote;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.dto.LoteDto;
import com.example.agroapp.model.lote.Lote;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionLoteViewModel extends AndroidViewModel {


    MutableLiveData<String> mError = new MutableLiveData<>();
    MutableLiveData<String> mExito = new MutableLiveData<>();
    MutableLiveData<Lote> mLote = new MutableLiveData<>();
    MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();
    MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    MutableLiveData<Boolean> mGuardarLote = new MutableLiveData<>();
    MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    MutableLiveData<String> mErrorCultivo = new MutableLiveData<>();
    MutableLiveData<String> mErrorSuperficie = new MutableLiveData<>();
    MutableLiveData<String> mErrorFecha = new MutableLiveData<>();









    public InformacionLoteViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getError() {
        return mError;
    }
    public LiveData<String> getExito() {
        return mExito;
    }
    public LiveData<Lote> getmLote() {
        return mLote;
    }
    public LiveData<Boolean> getHabilitarCampos() {
        return mHabilitarCampos;
    }
    public LiveData<String> getTextoBoton() {
        return mTextoBoton;
    }
    public LiveData<Boolean> getGuardarLote() {
        return mGuardarLote;
    }
    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }
    public LiveData<String> getErrorCultivo() {
        return mErrorCultivo;
    }
    public LiveData<String> getErrorSuperficie() {
        return mErrorSuperficie;
    }
    public LiveData<String> getErrorFecha() {
        return mErrorFecha;
    }






    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarLote.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }




    public void cargarLote(Lote l) {
        mLote.setValue(l);
    }

    public boolean validar(String nombre, String cultivo, String superficie, String fecha){
        boolean valido = true;

        if (nombre == null || nombre.trim().isEmpty()){
            mErrorNombre.setValue("Ingrese un nombre");
            valido = false;
        }
        if (cultivo == null || cultivo.trim().isEmpty()){
            mErrorCultivo.setValue("Ingrese un cultivo");
            valido = false;
        }
        if (superficie == null || superficie.trim().isEmpty()) {
            mErrorSuperficie.setValue("Ingrese una superficie");
            valido = false;
        }
        if (fecha == null || fecha.trim().isEmpty()) {
            mErrorFecha.setValue("Ingrese una fecha");
            valido = false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            sdf.setLenient(false);

            try {
                Date date = sdf.parse(fecha.trim());
            } catch (ParseException e) {
                mErrorFecha.setValue("Formato inválido (use dd/MM/yyyy)");
                valido = false;
            }
        }

        return valido;

    }

    public void actualizarLote(String nombre, String cultivo, String superficie, String fecha) {
        boolean valido = validar(nombre, cultivo, superficie, fecha);
        if (valido) {
            try{
                String token = Services.leerToken(getApplication());
                ApiCLient.appService service = ApiCLient.getService();
                Double superficieDouble = Double.parseDouble(superficie);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date fechaDate = formatter.parse(fecha.trim());

                LoteDto l = new LoteDto(nombre, cultivo, superficieDouble, fechaDate);
                Call<Lote> call = service.actualizarLote("Bearer " + token, mLote.getValue().getId_lote() , l);

                call.enqueue(new Callback<Lote>() {
                    @Override
                    public void onResponse(Call<Lote> call, Response<Lote> response) {
                        mExito.postValue("Lote actualizado");
                    }

                    @Override
                    public void onFailure(Call<Lote> call, Throwable t) {
                        mError.postValue("Error al actualizar el lote");
                    }
                });

            }catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
