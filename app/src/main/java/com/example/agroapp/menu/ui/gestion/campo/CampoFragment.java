package com.example.agroapp.menu.ui.gestion.campo;

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
import com.example.agroapp.databinding.FragmentCampoBinding;
import com.example.agroapp.model.campo.CampoAdapter;

public class CampoFragment extends Fragment {

    private CampoViewModel vm;
    private FragmentCampoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CampoViewModel.class);

        binding = FragmentCampoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getListaCampos().observe(getViewLifecycleOwner(), campos -> {
            CampoAdapter adapter = new CampoAdapter(campos, getContext(), getLayoutInflater());
            GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.lista.setLayoutManager(glm);
            binding.lista.setAdapter(adapter);

        });

        binding.btnAgregarCampo.setOnClickListener(v -> {
            Navigation.findNavController( root).navigate(R.id.action_campoFragment_to_agregarCampoFragment);
        });
        vm.cargarCampos();


        return root;
    }


}