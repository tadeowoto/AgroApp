package com.example.agroapp.menu.ui.gestion.cosecha.agregarCosecha;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentAgregarCosechaBinding;
import com.google.android.material.snackbar.Snackbar;

public class AgregarCosechaFragment extends Fragment {

    private AgregarCosechaViewModel vm;
    private FragmentAgregarCosechaBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarCosechaViewModel.class);

        binding = FragmentAgregarCosechaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        int idLote = getArguments().getInt("idLote");


        binding.btnGuardarCosecha.setOnClickListener(v ->{
            String fechaInicio = binding.etFechaInicio.getText().toString();
            String fechaFin = binding.etFechaFin.getText().toString();
            String rendimiento = binding.etRendimiento.getText().toString();
            String observaciones = binding.etObservaciones.getText().toString();

            vm.guardarCosecha(fechaInicio, fechaFin, rendimiento, observaciones, idLote);
        });

        vm.getErrorFechaInicio().observe(getViewLifecycleOwner(), error -> {
            binding.tilFechaInicio.setError(error);
        });
        vm.getErrorFechaFin().observe(getViewLifecycleOwner(), error -> {
            binding.tilFechaFin.setError(error);
        });
        vm.getErrorRendimiento().observe(getViewLifecycleOwner(), error -> {
            binding.tilRendimiento.setError(error);
        });
        vm.getErrorObservaciones().observe(getViewLifecycleOwner(), error -> {
            binding.tilObservaciones.setError(error);
        });
        vm.getmExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(root, exito, Snackbar.LENGTH_LONG).show();
        });
        vm.getmError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
        });






        return root;

    }



}