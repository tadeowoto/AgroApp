package com.example.agroapp.menu.ui.gestion.campo.detalleCampo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.model.campo.Campo;

public class DetalleCampoViewModel extends AndroidViewModel {

    MutableLiveData<Campo> mCampo = new MutableLiveData<>();

    public DetalleCampoViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Campo> getCampo() {
        return mCampo;
    }

    public void cargarElCampo(Campo c) {
        mCampo.setValue(c);
    }
}