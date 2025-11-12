package com.example.agroapp.menu.ui.gestion.actividad.detalleActividad;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentDetalleActividadBinding;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.actividad.Actividad;
import com.google.android.material.snackbar.Snackbar;

public class DetalleActividadFragment extends Fragment {

    private DetalleActividadViewModel vm;
    private FragmentDetalleActividadBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleActividadViewModel.class);

        binding = FragmentDetalleActividadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Actividad a = (Actividad) getArguments().getSerializable("actividad");
        vm.cargarVista(a);

        vm.getTipoActividad().observe(getViewLifecycleOwner(), tipoActividad -> {
                binding.tvTipoActividad.setText("Tipo de actividad: " + tipoActividad.getNombre());
        });

        vm.getActividad().observe(getViewLifecycleOwner(), actividad -> {

                binding.tvDescripcion.setText(actividad.getDescripcion());
                binding.tvFechaInicio.setText(Services.formatFechaDisplay(actividad.getFecha_inicio()));
                binding.tvFechaFin.setText(Services.formatFechaDisplay(actividad.getFecha_fin()));
                int cantidad = (int) actividad.getCantidad_insumo();
                binding.tvCantidadInsumo.setText(String.valueOf(cantidad));
                binding.tvCosto.setText(String.format("%.2f", actividad.getCosto()));

        });

        vm.getLote().observe(getViewLifecycleOwner(), lote -> {

                binding.tvNombreLote.setText("Nombre del lote: " + lote.getNombre());
                binding.tvCultivoLote.setText("Cultivo: " + lote.getCultivo());
                binding.tvSuperficieLote.setText("Superficie: " + String.format("%.2f ha", lote.getSuperficie_ha()));

        });

        vm.getInsumo().observe(getViewLifecycleOwner(), insumo -> {

                binding.tvNombreInsumo.setText("Nombre del insumo: " + insumo.getNombre());
                binding.tvTipoInsumo.setText("Tipo: " + insumo.getTipo());
                binding.tvStockInsumo.setText("Stock actual: " + String.format("%.1f unidades", insumo.getStock_actual()));

        });

        vm.getRecurso().observe(getViewLifecycleOwner(), recurso -> {
            binding.tvRecursoNombre.setText("Nombre del recurso: " + recurso.getNombre());
            binding.tvMarca.setText("Marca: " + recurso.getMarca());
        });

        vm.getHabilitarCampos().observe(getViewLifecycleOwner(), habilitar -> {
            binding.tvDescripcion.setEnabled(habilitar);
            binding.tvFechaInicio.setEnabled(habilitar);
            binding.tvFechaFin.setEnabled(habilitar);
            binding.tvCantidadInsumo.setEnabled(habilitar);
            binding.tvCosto.setEnabled(habilitar);
        });

        vm.getGuardarActividad().observe(getViewLifecycleOwner(), guardar -> {
            String descripcion = binding.tvDescripcion.getText().toString();
            String fechaInicio = binding.tvFechaInicio.getText().toString();
            String fechaFin = binding.tvFechaFin.getText().toString();
            String cantidadInsumo = binding.tvCantidadInsumo.getText().toString();
            String costo = binding.tvCosto.getText().toString();

            vm.actualizarActividad(descripcion, fechaInicio, fechaFin, cantidadInsumo, costo);
        });

        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditar.setText(texto);
        });

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
        });
        vm.getExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(binding.getRoot(), exito, Snackbar.LENGTH_LONG).show();
        });
        binding.btnEditar.setOnClickListener(v -> {
            vm.procesarBoton(binding.btnEditar.getText().toString());
        });

        vm.getErrorCosto().observe(getViewLifecycleOwner(), error -> {
            binding.tvCosto.setError(error);
        });
        vm.getErrorCantidadInsumo().observe(getViewLifecycleOwner(), error -> {
            binding.tvCantidadInsumo.setError(error);
        });
        vm.getErrorFechaFin().observe(getViewLifecycleOwner(), error -> {
            binding.tvFechaFin.setError(error);
        });
        vm.getErrorFechaInicio().observe(getViewLifecycleOwner(), error -> {
            binding.tvFechaInicio.setError(error);
        });
        vm.getErrorDescripcion().observe(getViewLifecycleOwner(), error -> {
            binding.tvDescripcion.setError(error);
        });







        return root;
    }



}