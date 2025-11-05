package com.example.agroapp.menu.ui.gestion.insumo.detalleInsumo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentDetalleInsumoBinding;

public class DetalleInsumoFragment extends Fragment {

    private DetalleInsumoViewModel vm;
    private FragmentDetalleInsumoBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(DetalleInsumoViewModel.class);

        binding = FragmentDetalleInsumoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }



}