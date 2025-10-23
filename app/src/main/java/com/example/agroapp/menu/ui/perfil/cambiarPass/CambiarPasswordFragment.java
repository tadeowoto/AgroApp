package com.example.agroapp.menu.ui.perfil.cambiarPass;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentCambiarPasswordBinding;

public class CambiarPasswordFragment extends Fragment {

    private CambiarPasswordViewModel vm;
    private FragmentCambiarPasswordBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(CambiarPasswordViewModel.class);

        binding = FragmentCambiarPasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.btnGuardarPass.setOnClickListener(v -> {
            String contraseniaActual = binding.etNuevaPass.getText().toString();
            String nuevaContrasenia = binding.etNuevaPass.getText().toString();
            String repeticionNuevaContrasenia = binding.etRepetirNuevaPass.getText().toString();

            vm.cambiarPassword(contraseniaActual, nuevaContrasenia, repeticionNuevaContrasenia);
        });

        return root;
    }


}