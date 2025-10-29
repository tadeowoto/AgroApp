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
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String fecha = sdf.format(lote.getFecha_creacion());
            binding.etFechaCreacion.setText(fecha);


        });

        binding.btnEditarLote.setOnClickListener(v -> {
            vm.procesarBoton(binding.btnEditarLote.getText().toString());
        });


        vm.mHabilitarCampos.observe(getViewLifecycleOwner(), habilitar -> {
            binding.etNombreLote.setEnabled(habilitar);
            binding.etCultivoLote.setEnabled(habilitar);
            binding.etSuperficieLote.setEnabled(habilitar);
            binding.etFechaCreacion.setEnabled(habilitar);
        });

        vm.mGuardarLote.observe(getViewLifecycleOwner(), guardar -> {
            String nombre = binding.etNombreLote.getText().toString();
            String cultivo = binding.etCultivoLote.getText().toString();
            double superficie = Double.parseDouble(binding.etSuperficieLote.getText().toString());
            String fecha = binding.etFechaCreacion.getText().toString();

            vm.actualizarLote(nombre, cultivo, superficie, fecha);
        });

        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditarLote.setText(texto);
        });

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
        });
        vm.getExito().observe(getViewLifecycleOwner(), exito -> {
            Snackbar.make(binding.getRoot(), exito, Snackbar.LENGTH_SHORT).show();
        });

        return root;
    }


}