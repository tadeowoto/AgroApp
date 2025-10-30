package com.example.agroapp.menu.ui.gestion.lote.agregarLote;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentAgregarLoteBinding;
import com.example.agroapp.lib.ApiCLient;
import com.example.agroapp.lib.Services;
import com.example.agroapp.model.campo.Campo;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarLoteFragment extends Fragment {

    private AgregarLoteViewModel vm;
    private FragmentAgregarLoteBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarLoteViewModel.class);

        binding = FragmentAgregarLoteBinding.inflate(inflater, container, false);

        int idCampo = getArguments().getInt("idCampo");

        vm.cargarNombreCampo(idCampo);

        vm.getmNombreCampo().observe(getViewLifecycleOwner(), nombreCampo -> {
            binding.etNombreCampo.setText(nombreCampo);
        });

        binding.btnGuardarLote.setOnClickListener( v ->{
            String nombre = binding.etNombreLote.getText().toString();
            String cultivo = binding.etCultivo.getText().toString();
            double superficie = Double.parseDouble(binding.etSuperficie.getText().toString());

            vm.agregarLote(nombre, cultivo, superficie);

        });

        vm.getmExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(binding.getRoot(), exito, Snackbar.LENGTH_SHORT).show();
        });
        vm.mError.observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
        });



        View root = binding.getRoot();

        return root;
    }



}