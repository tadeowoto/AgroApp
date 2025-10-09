package com.example.agroapp.menu.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private PerfilViewModel vm;
    private FragmentPerfilBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        return root;



    }


}
