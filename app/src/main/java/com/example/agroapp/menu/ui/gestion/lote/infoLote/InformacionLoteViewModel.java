package com.example.agroapp.menu.ui.gestion.lote.infoLote;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.model.lote.Lote;

public class InformacionLoteViewModel extends AndroidViewModel {

    MutableLiveData<Lote> mLote = new MutableLiveData<>();
    MutableLiveData<String> mError = new MutableLiveData<>();


    public InformacionLoteViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Lote> getmLote() {
        return mLote;
    }
    public LiveData<String> getmError(){
        return mError;
    }



    public void cargarLote(Lote l) {
        mLote.postValue(l);
    }

}