package com.example.agroapp.menu.ui.gestion;

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
import com.example.agroapp.databinding.FragmentGestionBinding;

public class GestionFragment extends Fragment {

    private GestionViewModel vm;
    private FragmentGestionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(GestionViewModel.class);
        binding = FragmentGestionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.btnActividades.setOnClickListener(v -> {
            // abrir el fragment de actividades
            Bundle bundle = new Bundle();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_gestionFragment_to_actividadFragment);
        });

        binding.btnCampos.setOnClickListener(v -> {
            // abrir el fragment de campos
            Bundle bundle = new Bundle();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_gestionFragment_to_campoFragment);
        });

        binding.btnCosechas.setOnClickListener(v -> {
            // abrir el fragment de cosechas
            Bundle bundle = new Bundle();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_gestionFragment_to_cosechaFragment);
        });

        binding.btnInsumos.setOnClickListener(v -> {
            // abrir el fragment de insumos
            Bundle bundle = new Bundle();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_gestionFragment_to_insumoFragment);
        });

        binding.btnLotes.setOnClickListener(v -> {
            // abrir el fragment de lotes
            Bundle bundle = new Bundle();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_gestionFragment_to_loteFragment);
        });

        binding.btnRecursos.setOnClickListener(v -> {
            // abrir el fragment de recursos
            Bundle bundle = new Bundle();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_gestionFragment_to_recursoFragment);
        });



        return root;

    }



}