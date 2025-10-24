package com.example.agroapp.menu.ui.gestion.campo.detalleCampo;

import androidx.lifecycle.ViewModelProvider;

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
            binding.tvNombreDetalle.setText(campo.getNombre());
            binding.tvUbicacionDetalle.setText(campo.getUbicacion());
            binding.tvValorExtension.setText(String.valueOf(campo.getExtension_ha()));
            binding.tvValorLatitud.setText(String.valueOf(campo.getLatitud()));
            binding.tvValorLongitud.setText(String.valueOf(campo.getLongitud()));
        });

        return root;
    }





}