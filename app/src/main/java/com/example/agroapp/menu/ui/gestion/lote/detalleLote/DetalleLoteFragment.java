package com.example.agroapp.menu.ui.gestion.lote.detalleLote;

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
import com.example.agroapp.databinding.FragmentDetalleLoteBinding;
import com.example.agroapp.model.lote.LoteAdapter;
import com.google.android.material.snackbar.Snackbar;

public class DetalleLoteFragment extends Fragment {

    private DetalleLoteViewModel vm;
    private FragmentDetalleLoteBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleLoteViewModel.class);

        binding = FragmentDetalleLoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        int idCampo = getArguments().getInt("idCampo");
        vm.cargarLotes(idCampo);

        vm.getListaLotes().observe(getViewLifecycleOwner(), lotes -> {
            LoteAdapter adapter = new LoteAdapter(lotes, getLayoutInflater(), lote -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("lote", lote);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detalleLoteFragment_to_informacionLoteFragment, bundle);
            });

            binding.lista.setLayoutManager(new GridLayoutManager(getContext(), 1));
            binding.lista.setAdapter(adapter);
        });


        vm.getmError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(root, error, Snackbar.LENGTH_SHORT).show();
        });

        binding.btnAgregarLote.setOnClickListener( v -> {

            Bundle bundle = new Bundle();
            bundle.putInt("idCampo", idCampo);
            Navigation.findNavController(root).navigate(R.id.action_detalleLoteFragment_to_agregarLoteFragment, bundle);
        });



        return root;

    }

}