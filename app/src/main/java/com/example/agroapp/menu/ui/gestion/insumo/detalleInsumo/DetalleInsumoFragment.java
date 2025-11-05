package com.example.agroapp.menu.ui.gestion.insumo.detalleInsumo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentDetalleInsumoBinding;
import com.example.agroapp.model.insumo.Insumo;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalleInsumoFragment extends Fragment {

    private DetalleInsumoViewModel vm;
    private FragmentDetalleInsumoBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(DetalleInsumoViewModel.class);

        binding = FragmentDetalleInsumoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Insumo insumo = (Insumo) getArguments().getSerializable("insumo");

        vm.cargarInsumo(insumo);

        vm.getInsumo().observe(getViewLifecycleOwner(), i -> {

                binding.etNombre.setText(i.getNombre());
                binding.spTipo.setText(i.getTipo(), false);
                binding.spUnidad.setText(i.getUnidad(), false);
                binding.etStock.setText(String.format(Locale.getDefault(), "%.2f", i.getStock_actual()));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String fechaFormateada = sdf.format(i.getFecha_vencimiento());
                    binding.etFechaVenc.setText(fechaFormateada);
        });

        vm.getmListaTipo().observe(getViewLifecycleOwner(), tipos -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, tipos);
            binding.spTipo.setAdapter(adapter);
        });
        vm.getmListaUnidad().observe(getViewLifecycleOwner(), unidades -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, unidades);
            binding.spUnidad.setAdapter(adapter);
        });


        vm.getHabilitarCampos().observe(getViewLifecycleOwner(), habilitar -> {
            binding.etNombre.setEnabled(habilitar);
            binding.spTipo.setEnabled(habilitar);
            binding.spUnidad.setEnabled(habilitar);
            binding.etStock.setEnabled(habilitar);
            binding.etFechaVenc.setEnabled(habilitar);
        });

        vm.getGuardarInsumo().observe(getViewLifecycleOwner(), guardar -> {
            String nombre = binding.etNombre.getText().toString();
            String tipo = binding.spTipo.getText().toString();
            String unidad = binding.spUnidad.getText().toString();
            String stock = binding.etStock.getText().toString();
            String fechaVenc = binding.etFechaVenc.getText().toString();

            vm.editarInsumo(nombre, tipo, unidad, stock, fechaVenc);

        });

        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditarInsumo.setText(texto);
        });

        binding.btnEditarInsumo.setOnClickListener(v -> {
            vm.procesarBoton(binding.btnEditarInsumo.getText().toString());
        });



        return root;
    }



}