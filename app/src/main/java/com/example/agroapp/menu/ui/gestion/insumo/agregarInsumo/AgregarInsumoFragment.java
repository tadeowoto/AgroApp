package com.example.agroapp.menu.ui.gestion.insumo.agregarInsumo;

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
import com.example.agroapp.databinding.FragmentAgregarInsumoBinding;
import com.google.android.material.snackbar.Snackbar;

public class AgregarInsumoFragment extends Fragment {

    private AgregarInsumoViewModel vm;
    private FragmentAgregarInsumoBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(AgregarInsumoViewModel.class);

        binding = FragmentAgregarInsumoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.btnGuardar.setOnClickListener(v -> {
            String nombre = binding.etNombre.getText().toString();
            String tipo = binding.spTipo.getText().toString();
            String unidad = binding.spUnidad.getText().toString();
            String stock = binding.etStock.getText().toString();
            String fechaVenc = binding.etFechaVenc.getText().toString();

            vm.agregarInsumo(nombre, tipo, unidad, stock, fechaVenc);
        });

        vm.getErrorFechaVenc().observe(getViewLifecycleOwner(), msg -> {
            binding.etFechaVenc.setError(msg);
        });
        vm.getErrorStock().observe(getViewLifecycleOwner(), msg -> {
            binding.etStock.setError(msg);
        });
        vm.getErrorUnidad().observe(getViewLifecycleOwner(), msg -> {
            binding.spUnidad.setError(msg);
        });
        vm.getErrorTipo().observe(getViewLifecycleOwner(), msg -> {
            binding.spTipo.setError(msg);
        });
        vm.getErrorNombre().observe(getViewLifecycleOwner(), msg -> {
            binding.etNombre.setError(msg);
        });
        vm.getmExito().observe(getViewLifecycleOwner(), msg -> {
            Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show();
        });
        vm.getmError().observe(getViewLifecycleOwner(), msg -> {
            Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_LONG).show();
        });

        vm.getmListaUnidad().observe(getViewLifecycleOwner(), unidades -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, unidades);
            binding.spUnidad.setAdapter(adapter);
        });
        vm.getmListaTipo().observe(getViewLifecycleOwner(), tipos -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, tipos);
            binding.spTipo.setAdapter(adapter);
        });



        return root;
    }



}