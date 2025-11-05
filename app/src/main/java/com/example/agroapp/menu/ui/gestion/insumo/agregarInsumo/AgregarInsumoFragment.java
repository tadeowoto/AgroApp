package com.example.agroapp.menu.ui.gestion.insumo.agregarInsumo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentAgregarInsumoBinding;

public class AgregarInsumoFragment extends Fragment {

    private AgregarInsumoViewModel vm;
    private FragmentAgregarInsumoBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(AgregarInsumoViewModel.class);

        binding = FragmentAgregarInsumoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }



}