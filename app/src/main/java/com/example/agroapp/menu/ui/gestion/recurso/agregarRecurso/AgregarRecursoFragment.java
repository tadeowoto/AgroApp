package com.example.agroapp.menu.ui.gestion.recurso.agregarRecurso;

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
import com.example.agroapp.databinding.FragmentAgregarRecursoBinding;
import com.google.android.material.snackbar.Snackbar;

public class AgregarRecursoFragment extends Fragment {

    private AgregarRecursoViewModel vm;
    private FragmentAgregarRecursoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(AgregarRecursoViewModel.class);
        binding = FragmentAgregarRecursoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getmListaEstado().observe(getViewLifecycleOwner(), estados -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, estados);
            binding.spEstado.setAdapter(adapter);
        });
        vm.getmListaTipo().observe(getViewLifecycleOwner(), tipos -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, tipos);
            binding.spTipo.setAdapter(adapter);
        });

        vm.getmErrorNombre().observe(getViewLifecycleOwner(), error -> {
            binding.tilNombre.setError(error);
        });
        vm.getmErrorTipo().observe(getViewLifecycleOwner(), error -> {
            binding.tilTipo.setError(error);
        });
        vm.getmErrorMarca().observe(getViewLifecycleOwner(), error -> {
            binding.tilMarca.setError(error);
        });
        vm.getmErrorModelo().observe(getViewLifecycleOwner(), error -> {
            binding.tilModelo.setError(error);
        });
        vm.getmExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(binding.getRoot(), exito, Snackbar.LENGTH_SHORT).show();
        });
        vm.getmError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
        });




        binding.btnGuardarRecurso.setOnClickListener(v -> {
            String nombre = binding.etNombre.getText().toString();
            String tipo = binding.spTipo.getText().toString();
            String marca = binding.etMarca.getText().toString();
            String modelo = binding.etModelo.getText().toString();
            String estado = binding.spEstado.getText().toString();
            vm.guardarRecurso(nombre, tipo, marca, modelo, estado);
        });




        return root;
    }


}