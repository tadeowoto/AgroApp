package com.example.agroapp.menu.ui.salir;

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
import com.example.agroapp.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private SalirViewModel vm;
    private FragmentSalirBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(SalirViewModel.class);
        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mostrarDialogoSalir();
        return root;
    }

    private void mostrarDialogoSalir() {
        new AlertDialog.Builder(getContext())
                .setTitle("Salir de la aplicación")
                .setMessage("¿Estás seguro de que deseas salir?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    requireActivity().finishAffinity();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    requireActivity().onBackPressed();
                })
                .show();
    }


}