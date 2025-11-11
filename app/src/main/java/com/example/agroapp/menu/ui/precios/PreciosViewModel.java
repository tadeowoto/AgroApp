package com.example.agroapp.menu.ui.precios;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.model.precioGrano.PrecioGrano;

import java.util.ArrayList;
import java.util.List;

public class PreciosViewModel extends AndroidViewModel {
    private MutableLiveData<List<PrecioGrano>> mPrecios = new MutableLiveData<>();

    public PreciosViewModel(@NonNull Application application) {
        super(application);
    }



    public LiveData<List<PrecioGrano>> getPrecios() {
        return mPrecios;
    }

    /* QUEDA PROPUESTO CARGAR LOS DATOS DESDE WEBSCRAPPING O ALGO SIMILAR. LA ESTRUCTURA SERIA ESTA
    LEYENDO LOS CSV LLEGUE AL MODEL QUE IMPLEMENTE. TAMBIEN SE PUEDEN HACER CHARTS.
    public void cargarPrecios() {
        List<PrecioGrano> precios = new ArrayList<>();
        precios.add(new PrecioGrano("Soja", "por Tonelada", 350.50, "🌱"));
        precios.add(new PrecioGrano("Maíz", "por Tonelada", 180.20, "🌽"));
        precios.add(new PrecioGrano("Trigo", "por Tonelada", 220.00, "🌾"));
        precios.add(new PrecioGrano("Girasol", "por Tonelada", 310.00, "🌻"));
        precios.add(new PrecioGrano("Sorgo", "por Tonelada", 165.75, "🍂"));

        mPrecios.postValue(precios);
    }

     */
}