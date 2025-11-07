package com.example.agroapp.menu.ui.gestion.cosecha.cosechasDeUnLote;

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
import com.example.agroapp.databinding.FragmentCosechasDeUnLoteBinding;
import com.example.agroapp.model.cosecha.CosechaAdapter;

public class CosechasDeUnLoteFragment extends Fragment {

    private CosechasDeUnLoteViewModel vm;
    private FragmentCosechasDeUnLoteBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CosechasDeUnLoteViewModel.class);
        binding = FragmentCosechasDeUnLoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        int idLote = getArguments().getInt("idLote");

        vm.cargarCosechas(idLote);

        vm.getListaCosechas().observe(getViewLifecycleOwner(), lista -> {
            CosechaAdapter adapter = new CosechaAdapter(lista, getLayoutInflater());
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            binding.lista.setLayoutManager(manager);
            binding.lista.setAdapter(adapter);
        });

        binding.btnGuardarNuevaCosecha.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("idLote", idLote);
            Navigation.findNavController(root).navigate(R.id.action_cosechasDeUnLoteFragment_to_agregarCosechaFragment2, bundle);
        });

        return root;
    }

}