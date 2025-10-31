package com.example.agroapp.menu.ui.gestion.actividad.detalleActividad;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentDetalleActividadBinding;
import com.example.agroapp.model.actividad.Actividad;

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
                binding.tvFechaInicio.setText(actividad.getFecha_inicio().toString());
                binding.tvFechaFin.setText(actividad.getFecha_fin().toString());
                binding.tvCosto.setText("$" + String.format("%.2f", actividad.getCosto()));

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



        return root;
    }



}