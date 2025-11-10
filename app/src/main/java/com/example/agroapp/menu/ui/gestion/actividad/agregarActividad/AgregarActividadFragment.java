package com.example.agroapp.menu.ui.gestion.actividad.agregarActividad;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentAgregarActividadBinding;
import com.google.android.material.snackbar.Snackbar;

public class AgregarActividadFragment extends Fragment {

    private AgregarActividadViewModel vm;
    private FragmentAgregarActividadBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarActividadViewModel.class);

        binding = FragmentAgregarActividadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getLotesNombres().observe(getViewLifecycleOwner(), nombres -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, nombres);
            binding.autoLote.setAdapter(adapter);
        });

        vm.getTiposNombres().observe(getViewLifecycleOwner(), nombres -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, nombres);
            binding.autoTipoActividad.setAdapter(adapter);
        });

        vm.getInsumosNombres().observe(getViewLifecycleOwner(), nombres -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, nombres);
            binding.autoInsumo.setAdapter(adapter);
        });

        vm.getRecursosNombres().observe(getViewLifecycleOwner(), nombres -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, nombres);
            binding.autoRecurso.setAdapter(adapter);
        });

        vm.getmExito().observe(getViewLifecycleOwner(), msg -> {
            Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show();
        });
        vm.getmError().observe(getViewLifecycleOwner(), msg -> {
            Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_LONG).show();
        });
        vm.getExitoRestarInsumo().observe(getViewLifecycleOwner(), msg -> {
            Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show();
        });

        binding.autoLote.setOnItemClickListener((parent, view, position, id) -> {
            String seleccionado = parent.getItemAtPosition(position).toString();
            vm.seleccionarLote(seleccionado);
        });

        binding.autoInsumo.setOnItemClickListener((parent, view, position, id) -> {
            String seleccionado = parent.getItemAtPosition(position).toString();
            vm.seleccionarInsumo(seleccionado);
        });

        binding.autoRecurso.setOnItemClickListener((parent, view, position, id) -> {
            String seleccionado = parent.getItemAtPosition(position).toString();
            vm.seleccionarRecurso(seleccionado);
        });

        binding.autoTipoActividad.setOnItemClickListener((parent, view, position, id) -> {
            String seleccionado = parent.getItemAtPosition(position).toString();
            vm.seleccionarTipoActividad(seleccionado);
        });

        vm.getErrorFechaInicio().observe(getViewLifecycleOwner(), msg -> {
            binding.etFechaInicio.setError(msg);
        });
        vm.getErrorFechaFin().observe(getViewLifecycleOwner(), msg -> {
            binding.etFechaFin.setError(msg);
        });
        vm.getErrorCantidad().observe(getViewLifecycleOwner(), msg -> {
            binding.etCantidadInsumo.setError(msg);
        });
        vm.getErrorCosto().observe(getViewLifecycleOwner(), msg -> {
            binding.etCosto.setError(msg);
        });


        vm.cargarVistaAgregarActividad();


        binding.btnGuardarActividad.setOnClickListener(v -> {
            String descripcion = binding.etDescripcion.getText().toString();
            String fechaInicio = binding.etFechaInicio.getText().toString();
            String fechaFin = binding.etFechaFin.getText().toString();
            String cantidad = binding.etCantidadInsumo.getText().toString();
            String costo = binding.etCosto.getText().toString();

            vm.guardarActividad(descripcion, fechaInicio, fechaFin, cantidad, costo);
        });
        return root;
    }
}