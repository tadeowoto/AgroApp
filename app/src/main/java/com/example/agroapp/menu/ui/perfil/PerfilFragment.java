package com.example.agroapp.menu.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentPerfilBinding;
import com.google.android.material.snackbar.Snackbar;

public class PerfilFragment extends Fragment {

    private PerfilViewModel vm;
    private FragmentPerfilBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getmError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
        });

        vm.getmUsuario().observe(getViewLifecycleOwner(), usuario -> {
            binding.tvEmail.setText(usuario.getEmail());
            binding.tvNombre.setText(usuario.getNombre());
            binding.tvTelefono.setText(usuario.getTelefono());
        });

        binding.btnCambiarPassword.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_perfilFragment_to_cambiarPasswordFragment);
        });

        binding.btnEditar.setOnClickListener(v -> {
            vm.procesarBoton(binding.btnEditar.getText().toString());
        });
        vm.getGuardarPerfil().observe(getViewLifecycleOwner(), guardar -> {
            String nombre = binding.tvNombre.getText().toString();
            String email = binding.tvEmail.getText().toString();
            String telefono = binding.tvTelefono.getText().toString();

            vm.actualizarPerfil(nombre, email,telefono);
        });

        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditar.setText(texto);
        });
        vm.getHabilitarCampos().observe(getViewLifecycleOwner(), habilitar -> {
            binding.tvNombre.setEnabled(habilitar);
            binding.tvEmail.setEnabled(habilitar);
            binding.tvTelefono.setEnabled(habilitar);
        });

        vm.getmErrorEmail().observe(getViewLifecycleOwner(), error -> {
            binding.tilEmail.setError(error);
        });
        vm.getmErrorTelefono().observe(getViewLifecycleOwner(), error -> {
            binding.tilTelefono.setError(error);
        });
        vm.getmExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(binding.getRoot(), exito, Snackbar.LENGTH_LONG).show();
        });
        vm.getmError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
        });





        vm.llenarCampos();

        return root;



    }


}
