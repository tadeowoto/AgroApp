package com.example.agroapp.menu.ui.gestion.actividad;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentActividadBinding;
import com.example.agroapp.model.actividad.ActividadAdapter;

public class ActividadFragment extends Fragment {

    private ActividadViewModel vm;
    private FragmentActividadBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ActividadViewModel.class);

        binding = FragmentActividadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm.cargarActividades();

        vm.getListaActividades().observe(getViewLifecycleOwner(), actividades -> {
            ActividadAdapter adapter = new ActividadAdapter(actividades, getLayoutInflater());
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            binding.lista.setLayoutManager(manager);
            binding.lista.setAdapter(adapter);
        });




        return root;
    }



}