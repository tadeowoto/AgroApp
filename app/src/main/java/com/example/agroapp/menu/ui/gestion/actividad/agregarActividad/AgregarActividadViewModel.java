package com.example.agroapp.menu.ui.gestion.actividad.agregarActividad;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.actividad.Actividad;
import com.example.agroapp.model.insumo.Insumo;
import com.example.agroapp.model.lote.Lote;
import com.example.agroapp.model.recurso.Recurso;
import com.example.agroapp.model.tipoActividad.TipoActividad;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarActividadViewModel extends AndroidViewModel {


    private MutableLiveData<List<Lote>> mLotes = new MutableLiveData<>();
    private MutableLiveData<List<Insumo>> mInsumos = new MutableLiveData<>();
    private MutableLiveData<List<Recurso>> mRecursos = new MutableLiveData<>();
    private MutableLiveData<List<TipoActividad>> mTipos = new MutableLiveData<>();
    private MutableLiveData<List<String>> mLotesNombres = new MutableLiveData<>();
    private MutableLiveData<List<String>> mInsumosNombres = new MutableLiveData<>();
    private MutableLiveData<List<String>> mRecursosNombres = new MutableLiveData<>();
    private MutableLiveData<List<String>> mTiposNombres = new MutableLiveData<>();
    private MutableLiveData<Integer> idLoteSeleccionado = new MutableLiveData<>();
    private MutableLiveData<Integer> idInsumoSeleccionado = new MutableLiveData<>();
    private MutableLiveData<Integer> idRecursoSeleccionado = new MutableLiveData<>();
    private MutableLiveData<Integer> idTipoSeleccionado = new MutableLiveData<>();
    private MutableLiveData<String> mExito = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<String> mErrorFechaInicio = new MutableLiveData<>();
    private MutableLiveData<String> mErrorFechaFin = new MutableLiveData<>();
    private MutableLiveData<String> mErrorCantidad = new MutableLiveData<>();
    private MutableLiveData<String> mErrorCosto = new MutableLiveData<>();
    private MutableLiveData<String> mErrorDescripcion = new MutableLiveData<>();


    public LiveData<String> getErrorFechaInicio() {
        return mErrorFechaInicio;
    }
    public LiveData<String> getErrorFechaFin() {
        return mErrorFechaFin;
    }
    public LiveData<String> getErrorCantidad() {
        return mErrorCantidad;
    }
    public LiveData<String> getErrorCosto() {
        return mErrorCosto;
    }
    public LiveData<String> getErrorDescripcion() {
        return mErrorDescripcion;
    }

    public LiveData<List<Lote>> getLotes() {
        return mLotes;
    }
    public LiveData<List<Insumo>> getInsumos() {
        return mInsumos;
    }
    public LiveData<List<Recurso>> getRecursos() {
        return mRecursos;
    }
    public LiveData<List<TipoActividad>> getTipos() {
        return mTipos;
    }
    public LiveData<List<String>> getLotesNombres() {
        return mLotesNombres;
    }
    public LiveData<List<String>> getInsumosNombres() {
        return mInsumosNombres;
    }
    public LiveData<List<String>> getRecursosNombres() {
        return mRecursosNombres;
    }
    public LiveData<List<String>> getTiposNombres() {
        return mTiposNombres;
    }

    public LiveData<String> getmExito() {
        return mExito;
    }
    public LiveData<String> getmError() {
        return mError;
    }






    public AgregarActividadViewModel(@NonNull Application application) {
        super(application);
    }


    public void cargarVistaAgregarActividad() {
        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        try {
            Call<List<Lote>> call = service.obtenerLotesDelUsuario("Bearer " + token);

            call.enqueue(new Callback<List<Lote>>() {
                @Override
                public void onResponse(Call<List<Lote>> call, Response<List<Lote>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Lote> lotes = response.body();
                        mLotes.postValue(lotes);
                        List<String> nombres = new ArrayList<>();
                        for (Lote l : lotes) nombres.add(l.getNombre());
                        mLotesNombres.postValue(nombres);
                    } else {
                        mError.postValue("No se pudieron cargar los lotes");
                    }
                }

                @Override
                public void onFailure(Call<List<Lote>> call, Throwable t) {
                    mError.postValue("Error de conexión: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Call<List<Insumo>> callInsumo = service.obtenerInsumosDelUsuario("Bearer " + token);
        callInsumo.enqueue(new Callback<List<Insumo>>() {
            @Override
            public void onResponse(Call<List<Insumo>> call, Response<List<Insumo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Insumo> insumos = response.body();
                    mInsumos.postValue(insumos);
                    List<String> nombres = new ArrayList<>();
                    for (Insumo i : insumos) nombres.add(i.getNombre());
                    mInsumosNombres.postValue(nombres);
                }
            }
            @Override public void onFailure(Call<List<Insumo>> call, Throwable t) { mError.postValue("Error insumos: "+t.getMessage()); }
        });


        Call<List<Recurso>> callRec = service.obtenerRecursosDelUsuario("Bearer " + token);
        callRec.enqueue(new Callback<List<Recurso>>() {
            @Override
            public void onResponse(Call<List<Recurso>> call, Response<List<Recurso>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Recurso> recursos = response.body();
                    mRecursos.postValue(recursos);
                    List<String> nombres = new ArrayList<>();
                    for (Recurso r : recursos) nombres.add(r.getNombre());
                    mRecursosNombres.postValue(nombres);
                }
            }
            @Override public void onFailure(Call<List<Recurso>> call, Throwable t) { mError.postValue("Error recursos: "+t.getMessage()); }
        });

        Call<List<TipoActividad>> callTipos = service.obtenerTipoActividades("Bearer " + token);
        callTipos.enqueue(new Callback<List<TipoActividad>>() {
            @Override
            public void onResponse(Call<List<TipoActividad>> call, Response<List<TipoActividad>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TipoActividad> tipos = response.body();
                    mTipos.postValue(tipos);
                    List<String> nombres = new ArrayList<>();
                    for (TipoActividad t : tipos) nombres.add(t.getNombre());
                    mTiposNombres.postValue(nombres);
                }
            }
            @Override public void onFailure(Call<List<TipoActividad>> call, Throwable t) { mError.postValue("Error tipos: "+t.getMessage()); }
        });

    }

    public boolean validarCampos(String descripcion, String fechaInicioStr, String fechaFinStr, String cantidadStr, String costoStr) {
        boolean valido = true;


        if (descripcion == null || descripcion.trim().isEmpty()) {
            mErrorDescripcion.postValue("Debe ingresar una descripción");
            valido = false;
        }
        if (cantidadStr == null || cantidadStr.trim().isEmpty()) {
            mErrorCantidad.postValue("Debe ingresar una cantidad");
            valido = false;
        }


        if (costoStr == null || costoStr.trim().isEmpty()) {
            mErrorCosto.postValue("Debe ingresar un costo");
            valido = false;
        }


        Date fechaInicio = null;
        Date fechaFin = null;

        if (fechaInicioStr == null || fechaInicioStr.trim().isEmpty()) {
            mErrorFechaInicio.postValue("Debe ingresar una fecha de inicio");
            valido = false;
        } else {
            fechaInicio = Services.parseFecha(fechaInicioStr);
            if (fechaInicio == null) {
                mErrorFechaInicio.postValue("Formato de fecha inválido (use dd/MM/yyyy)");
                valido = false;
            } else {
                mErrorFechaInicio.postValue(null);
            }
        }
        if (fechaFinStr == null || fechaFinStr.trim().isEmpty()) {
            mErrorFechaFin.postValue("Debe ingresar una fecha de fin");
            valido = false;
        } else {
            fechaFin = Services.parseFecha(fechaFinStr);
            if (fechaFin == null) {
                mErrorFechaFin.postValue("Formato de fecha inválido (use dd/MM/yyyy)");
                valido = false;
            } else {
                mErrorFechaFin.postValue(null);
            }
        }

        if (fechaInicio != null && fechaFin != null) {
            if (fechaFin.before(fechaInicio)) {
                mErrorFechaFin.postValue("La fecha de fin no puede ser anterior a la fecha de inicio");
                valido = false;
            }
        }

        return valido;
    }


    public void guardarActividad(String descripcion, String fechaInicioStr, String fechaFinStr, String cantidadStr, String costoStr) {
        if (idLoteSeleccionado.getValue() == null || idTipoSeleccionado.getValue() == null) {
            mError.postValue("Debe seleccionar al menos lote y tipo de actividad");
            return;
        }
        boolean valido = validarCampos(descripcion, fechaInicioStr, fechaFinStr, cantidadStr, costoStr);
        if (valido){
            Double cantidad = (cantidadStr == null || cantidadStr.isEmpty()) ? null : Double.parseDouble(cantidadStr);
            Double costo = (costoStr == null || costoStr.isEmpty()) ? null : Double.parseDouble(costoStr);

            Date fechaInicio = Services.parseFecha(fechaInicioStr);
            Date fechaFin = Services.parseFecha(fechaFinStr);

            Integer idLote = idLoteSeleccionado.getValue();
            Integer idTipoActividad = idTipoSeleccionado.getValue();
            Integer idInsumo = idInsumoSeleccionado.getValue();
            Integer idRecurso = idRecursoSeleccionado.getValue();

            Actividad nuevaActividad = new Actividad(
                    0,
                    idLote != null ? idLote : 0,
                    idInsumo,
                    idRecurso,
                    idTipoActividad != null ? idTipoActividad : 0,
                    descripcion,
                    fechaInicio,
                    fechaFin,
                    cantidad != null ? cantidad : 0,
                    costo != null ? costo : 0
            );

            agregarActividad(nuevaActividad);
        };
    }

    private void agregarActividad(Actividad nuevaActividad) {

        String token = Services.leerToken(getApplication());
        ApiCLient.appService service = ApiCLient.getService();

        try {
            Call<JsonObject> call = service.agregarActividad("Bearer " + token, nuevaActividad);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    mExito.postValue("Actividad agregada correctamente");
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    mError.postValue("Error al agregar actividad: " + t.getMessage());
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }



    public void seleccionarLote(String nombreSeleccionado) {
        List<Lote> lotes = mLotes.getValue();
        if (lotes != null) {
            for (Lote l : lotes) {
                if (l.getNombre().equals(nombreSeleccionado)) {
                    idLoteSeleccionado.postValue(l.getId_campo());
                    break;
                }
            }
        }
    }

    public void seleccionarInsumo(String nombreSeleccionado) {
        List<Insumo> insumos = mInsumos.getValue();
        if (insumos != null) {
            for (Insumo i : insumos) {
                if (i.getNombre().equals(nombreSeleccionado)) {
                    idInsumoSeleccionado.postValue(i.getId_insumo());
                    break;
                }
            }
        }
    }

    public void seleccionarRecurso(String nombreSeleccionado) {
        List<Recurso> recursos = mRecursos.getValue();
        if (recursos != null) {
            for (Recurso r : recursos) {
                if (r.getNombre().equals(nombreSeleccionado)) {
                    idRecursoSeleccionado.postValue(r.getId_recurso());
                    break;
                }
            }
        }
    }

    public void seleccionarTipoActividad(String nombreSeleccionado) {
        List<TipoActividad> tipos = mTipos.getValue();
        if (tipos != null) {
            for (TipoActividad t : tipos) {
                if (t.getNombre().equals(nombreSeleccionado)) {
                    idTipoSeleccionado.postValue(t.getId_tipo_actividad());
                    break;
                }
            }
        }
    }


}