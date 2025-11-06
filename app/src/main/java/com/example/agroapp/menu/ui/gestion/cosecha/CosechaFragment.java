package com.example.agroapp.menu.ui.gestion.cosecha;

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
import com.example.agroapp.databinding.FragmentCosechaBinding;
import com.example.agroapp.model.lote.LoteAdapter;
import com.google.android.material.snackbar.Snackbar;

public class CosechaFragment extends Fragment {

    private CosechaViewModel vm;
    private FragmentCosechaBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CosechaViewModel.class);

        binding = FragmentCosechaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.cargarLista();

        vm.getListaLotes().observe(getViewLifecycleOwner(), lotes -> {
            LoteAdapter adapter = new LoteAdapter(lotes, getLayoutInflater(), lote -> {

                Bundle bundle = new Bundle();
                bundle.putInt("idLote", lote.getId_lote());
                bundle.putString("nombreLote", lote.getNombre());
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_cosechaFragment_to_cosechasDeUnLoteFragment, bundle);
            });

            binding.lista.setLayoutManager(new GridLayoutManager(getContext(), 1));
            binding.lista.setAdapter(adapter);
        });


        vm.getError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(root, error, Snackbar.LENGTH_SHORT).show();
        });




        return root;
    }



}