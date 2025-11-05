package com.example.agroapp.menu.ui.gestion.recurso;

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
import com.example.agroapp.databinding.FragmentRecursoBinding;
import com.example.agroapp.menu.ui.gestion.GestionViewModel;
import com.example.agroapp.model.recurso.RecursoAdapter;

public class RecursoFragment extends Fragment {

    private RecursoViewModel vm;
    private FragmentRecursoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(RecursoViewModel.class);
        binding = FragmentRecursoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getmLista().observe(getViewLifecycleOwner(), recursos -> {
            RecursoAdapter adapter = new RecursoAdapter(recursos, getLayoutInflater());
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
            binding.lista.setLayoutManager(layoutManager);
            binding.lista.setAdapter(adapter);
        });


        vm.cargarLista();


        binding.btnAgregarRecurso.setOnClickListener(v -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_recursoFragment_to_agregarRecursoFragment);
        });

        return root;
    }

}