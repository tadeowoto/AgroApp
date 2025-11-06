package com.example.agroapp.menu.ui.gestion.insumo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentInsumoBinding;
import com.example.agroapp.model.insumo.InsumoAdapter;

public class InsumoFragment extends Fragment {

    private InsumoViewModel vm;
    private FragmentInsumoBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(InsumoViewModel.class);
        binding = FragmentInsumoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.cargarLista();

        vm.getListaInsumo().observe(getViewLifecycleOwner(), insumos -> {
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            InsumoAdapter adapter = new InsumoAdapter(insumos, getLayoutInflater());
            binding.lista.setLayoutManager(manager);
            binding.lista.setAdapter(adapter);
        });

        binding.btnAgregarInsumo.setOnClickListener(v ->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_insumoFragment_to_agregarInsumoFragment);
        });




        return root;

    }





}