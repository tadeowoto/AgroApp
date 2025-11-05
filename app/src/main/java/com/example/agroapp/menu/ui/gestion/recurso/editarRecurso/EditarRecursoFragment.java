package com.example.agroapp.menu.ui.gestion.recurso.editarRecurso;

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
import com.example.agroapp.databinding.FragmentEditarRecursoBinding;
import com.example.agroapp.model.recurso.Recurso;
import com.google.android.material.snackbar.Snackbar;

public class EditarRecursoFragment extends Fragment {

    private EditarRecursoViewModel vm;
    private FragmentEditarRecursoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(EditarRecursoViewModel.class);
        binding = FragmentEditarRecursoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Recurso r = (Recurso) getArguments().getSerializable("recurso");
        vm.cargarElRecurso(r);

        vm.getRecurso().observe(getViewLifecycleOwner(), recurso -> {
            binding.etNombre.setText(recurso.getNombre());
            binding.spTipo.setText(recurso.getTipo());
            binding.etMarca.setText(recurso.getMarca());
            binding.etModelo.setText(recurso.getModelo());
            binding.spEstado.setText(recurso.getEstado());
        });


        vm.getHabilitarCampos().observe(getViewLifecycleOwner(), habilitar -> {
            binding.etNombre.setEnabled(habilitar);
            binding.spTipo.setEnabled(habilitar);
            binding.etMarca.setEnabled(habilitar);
            binding.etModelo.setEnabled(habilitar);
            binding.spEstado.setEnabled(habilitar);
        });

        vm.getGuardarRecurso().observe(getViewLifecycleOwner(), guardar -> {
            String nombre = binding.etNombre.getText().toString();
            String tipo = binding.spTipo.getText().toString();
            String marca = binding.etMarca.getText().toString();
            String modelo = binding.etModelo.getText().toString();
            String estado = binding.spEstado.getText().toString();
            vm.editarRecurso(nombre, tipo, marca, modelo, estado);
        });

        vm.getmErrorMarca().observe(getViewLifecycleOwner(), error -> {
            binding.tilMarca.setError(error);
        });
        vm.getmErrorModelo().observe(getViewLifecycleOwner(), error -> {
            binding.tilModelo.setError(error);
        });
        vm.getmErrorNombre().observe(getViewLifecycleOwner(), error -> {
            binding.tilNombre.setError(error);
        });
        vm.getmErrorTipo().observe(getViewLifecycleOwner(), error -> {
            binding.tilTipo.setError(error);
        });
        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditarRecurso.setText(texto);
        });


        vm.getmListaEstado().observe(getViewLifecycleOwner(), estados -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, estados);
            binding.spEstado.setAdapter(adapter);
        });
        vm.getmListaTipo().observe(getViewLifecycleOwner(), tipos -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, tipos);
            binding.spTipo.setAdapter(adapter);
        });

        vm.getmError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
        });
        vm.getmExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(binding.getRoot(), exito, Snackbar.LENGTH_SHORT).show();
        });




        binding.btnEditarRecurso.setOnClickListener(v -> {
            vm.procesarBoton(binding.btnEditarRecurso.getText().toString());
        });

        return root;
    }



}