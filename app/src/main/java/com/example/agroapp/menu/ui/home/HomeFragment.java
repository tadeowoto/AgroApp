package com.example.agroapp.menu.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentHomeBinding;
import com.example.agroapp.model.cosecha.CosechaAdapter;

public class HomeFragment extends Fragment {

    HomeViewModel vm;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm.cargarHome();

        vm.getmRecursos().observe(getViewLifecycleOwner(), recursos -> {
            binding.tvRecursosCantidad.setText(String.valueOf(recursos));
        });
        vm.getmLotes().observe(getViewLifecycleOwner(), lotes -> {
            binding.tvLotesProduccion.setText(String.valueOf(lotes));
        });
        vm.getmInsumos().observe(getViewLifecycleOwner(), insumos -> {
            binding.tvInsumosCantidad.setText(String.valueOf(insumos));
        });
        vm.getmCampos().observe(getViewLifecycleOwner(), campos -> {
            binding.tvCamposActivos.setText(String.valueOf(campos));
        });

        vm.getmCosechas().observe(getViewLifecycleOwner(), cosechas -> {
            CosechaAdapter adapter = new CosechaAdapter(cosechas, getLayoutInflater());
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            binding.rvCosechas.setLayoutManager(manager);
            binding.rvCosechas.setAdapter(adapter);
        });

        binding.btnAgregarActividad.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_home_to_agregarActividadFragment);
        });
        binding.btnVerRecursos.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_home_to_recursoFragment);
        });
        binding.btnVerInsumos.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_home_to_insumoFragment);
        });







        return root;
    }

}