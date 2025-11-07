package com.example.agroapp.menu.ui.gestion.cosecha.detalleCosecha;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentDetalleCosechaBinding;
import com.example.agroapp.model.cosecha.Cosecha;
import com.google.android.material.snackbar.Snackbar;

public class DetalleCosechaFragment extends Fragment {

    private DetalleCosechaViewModel vm;

    private FragmentDetalleCosechaBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleCosechaViewModel.class);
        binding = FragmentDetalleCosechaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Cosecha c = (Cosecha) getArguments().getSerializable("cosecha");

        vm.cargarCosecha(c);

        vm.getCosecha().observe(getViewLifecycleOwner(), cosecha -> {
            binding.etFechaFinDetalle.setText("" + cosecha.getFecha_fin());
            binding.etFechaInicioDetalle.setText("" + cosecha.getFecha_inicio());
            binding.etObservacionesDetalle.setText(cosecha.getObservaciones());
            binding.etRendimientoDetalle.setText(String.valueOf(cosecha.getRendimiento()));
        });
        vm.getHabilitarCampos().observe(getViewLifecycleOwner(), habilitar -> {
            binding.etFechaFinDetalle.setEnabled(habilitar);
            binding.etFechaInicioDetalle.setEnabled(habilitar);
            binding.etObservacionesDetalle.setEnabled(habilitar);
            binding.etRendimientoDetalle.setEnabled(habilitar);
        });
        vm.getGuardarCosecha().observe(getViewLifecycleOwner(), guardar -> {
            String fechaInicio = binding.etFechaInicioDetalle.getText().toString();
            String fechaFin = binding.etFechaFinDetalle.getText().toString();
            String rendimiento = binding.etRendimientoDetalle.getText().toString();
            String observaciones = binding.etObservacionesDetalle.getText().toString();

            vm.editarCosecha(fechaInicio, fechaFin, rendimiento, observaciones);
        });

        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditarCosecha.setText(texto);
        });

        vm.getmError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
        });

        vm.getmExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(root, exito, Snackbar.LENGTH_LONG).show();
        });

        binding.btnEditarCosecha.setOnClickListener(v -> {
            vm.procesarBoton(binding.btnEditarCosecha.getText().toString());
        });

        vm.getErrorFechaFin().observe(getViewLifecycleOwner(), error -> {
            binding.etFechaFinDetalle.setError(error);
        });
        vm.getErrorFechaInicio().observe(getViewLifecycleOwner(), error -> {
            binding.etFechaInicioDetalle.setError(error);
        });
        vm.getErrorObservaciones().observe(getViewLifecycleOwner(), error -> {
            binding.etObservacionesDetalle.setError(error);
        });
        vm.getErrorRendimiento().observe(getViewLifecycleOwner(), error -> {
            binding.etRendimientoDetalle.setError(error);
        });





        return root;
    }



}