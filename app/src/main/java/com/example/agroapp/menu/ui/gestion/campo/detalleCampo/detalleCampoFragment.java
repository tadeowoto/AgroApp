package com.example.agroapp.menu.ui.gestion.campo.detalleCampo;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentDetalleCampoBinding;
import com.example.agroapp.model.campo.Campo;
import com.google.android.material.snackbar.Snackbar;

public class detalleCampoFragment extends Fragment {

    private DetalleCampoViewModel vm;
    private FragmentDetalleCampoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleCampoViewModel.class);
        binding = FragmentDetalleCampoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Campo c = (Campo) getArguments().getSerializable("campo");
        vm.cargarElCampo(c);

        vm.getCampo().observe(getViewLifecycleOwner(), campo -> {
            binding.etNombreCampo.setText(campo.getNombre());
            binding.etUbicacionCampo.setText(campo.getUbicacion());
            binding.etExtensionCampo.setText(String.valueOf(campo.getExtension_ha()));
            binding.etLatitudCampo.setText(String.valueOf(campo.getLatitud()));
            binding.etLongitudCampo.setText(String.valueOf(campo.getLongitud()));
        });



        vm.getHabilitarCampos().observe(getViewLifecycleOwner(), habilitar -> {
            binding.etNombreCampo.setEnabled(habilitar);
            binding.etUbicacionCampo.setEnabled(habilitar);
            binding.etExtensionCampo.setEnabled(habilitar);
            binding.etLatitudCampo.setEnabled(habilitar);
            binding.etLongitudCampo.setEnabled(habilitar);
        });

        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditarCampo.setText(texto);
        });

        vm.getExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(binding.getRoot(), exito, Snackbar.LENGTH_SHORT).show();
        });

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
        });

        vm.getErrorLatitud().observe(getViewLifecycleOwner(), error -> {
            binding.etLatitudCampo.setError(error);
        });
        vm.getErrorLongitud().observe(getViewLifecycleOwner(), error -> {
            binding.etLongitudCampo.setError(error);
        });
        vm.getErrorExtension().observe(getViewLifecycleOwner(), error -> {
            binding.etExtensionCampo.setError(error);
        });
        vm.getErrorUbicacion().observe(getViewLifecycleOwner(), error -> {
            binding.etUbicacionCampo.setError(error);
        });



        vm.getGuardarCampo().observe(getViewLifecycleOwner(), guardar -> {

            String nombre = binding.etNombreCampo.getText().toString();
            String ubicacion = binding.etUbicacionCampo.getText().toString();
            String extension = binding.etExtensionCampo.getText().toString();
            String latitud = binding.etLatitudCampo.getText().toString();
            String longitud = binding.etLongitudCampo.getText().toString();

            vm.actualizarCampo( nombre, ubicacion, extension, latitud, longitud);
        });

        binding.btnEditarCampo.setOnClickListener(v ->{
            vm.procesarBoton(binding.btnEditarCampo.getText().toString());
        });




        return root;
    }





}