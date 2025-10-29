package com.example.agroapp.menu.ui.gestion.lote.infoLote;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentInformacionLoteBinding;
import com.example.agroapp.model.lote.Lote;

public class InformacionLoteFragment extends Fragment {

    private InformacionLoteViewModel vm;
    private FragmentInformacionLoteBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(InformacionLoteViewModel.class);

        binding = FragmentInformacionLoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Lote l = (Lote) getArguments().getSerializable("lote");

        vm.cargarLote(l);

        vm.getmLote().observe(getViewLifecycleOwner(), lote -> {
            binding.etNombreLote.setText(lote.getNombre());
            binding.etCultivoLote.setText(lote.getCultivo());
            binding.etSuperficieLote.setText(String.valueOf(lote.getSuperficie_ha()));
            String fecha = lote.getFecha_creacion().getDate() + "/" + lote.getFecha_creacion().getMonth() + "/" + lote.getFecha_creacion().getYear();
            binding.etFechaCreacion.setText(fecha);

        });

        return root;
    }


}