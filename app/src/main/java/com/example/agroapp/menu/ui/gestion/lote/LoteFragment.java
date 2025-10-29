package com.example.agroapp.menu.ui.gestion.lote;

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
import com.example.agroapp.databinding.FragmentLoteBinding;
import com.example.agroapp.model.campo.CampoAdapter;

public class LoteFragment extends Fragment {

    private LoteViewModel vm;
    private FragmentLoteBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(LoteViewModel.class);

        binding = FragmentLoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        vm.getListaCampos().observe(getViewLifecycleOwner() , campos -> {
            CampoAdapter adapter = new CampoAdapter(campos, getLayoutInflater(), (campo, view) -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("idCampo", campo.getId_campo());
                Navigation.findNavController(view).navigate(R.id.action_loteFragment_to_detalleLoteFragment, bundle);
            });

            binding.lista.setLayoutManager(new GridLayoutManager(getContext(), 1));
            binding.lista.setAdapter(adapter);
        });


        vm.cargarCampos();


        return root;

    }



}