package com.example.agroapp.menu.ui.gestion.campo.agregarCampo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentAgregarCampoBinding;
import com.example.agroapp.databinding.FragmentDetalleCampoBinding;
import com.google.android.material.snackbar.Snackbar;

public class AgregarCampoFragment extends Fragment {

    private AgregarCampoViewModel vm;
    private FragmentAgregarCampoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarCampoViewModel.class);
        binding = FragmentAgregarCampoBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        binding.btnGuardarCampo.setOnClickListener( v -> {
            String nombre = binding.etNombreCampo.getText().toString();
            String ubicacion = binding.etUbicacionCampo.getText().toString();
            String area = binding.etAreaCampo.getText().toString();
            String latitud = binding.etLatitudCampo.getText().toString();
            String longitud = binding.etLongitudCampo.getText().toString();


            vm.guardarCampo(nombre, ubicacion, area, latitud, longitud);
        });

        vm.mErrorArea.observe(getViewLifecycleOwner(), error -> {
            binding.layoutAreaCampo.setError(error);
        });
        vm.mErrorLatitud.observe(getViewLifecycleOwner(), error -> {
            binding.layoutLatitudCampo.setError(error);
        });
        vm.mErrorLongitud.observe(getViewLifecycleOwner(), error -> {
            binding.layoutLongitudCampo.setError(error);
        });
        vm.mErrorNombre.observe(getViewLifecycleOwner(), error -> {
            binding.layoutNombreCampo.setError(error);
        });
        vm.mErrorUbicacion.observe(getViewLifecycleOwner(), error -> {
            binding.layoutUbicacionCampo.setError(error);
        });

        vm.mExito.observe(getViewLifecycleOwner(), msg -> {
            Snackbar.make(root, msg, Snackbar.LENGTH_SHORT).show();
        });
        vm.mError.observe(getViewLifecycleOwner(), msg -> {
            Snackbar.make(root, msg, Snackbar.LENGTH_SHORT).show();
        });
        return root;

    }


}