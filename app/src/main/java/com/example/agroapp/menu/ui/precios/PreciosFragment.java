package com.example.agroapp.menu.ui.precios;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agroapp.R;
import com.example.agroapp.databinding.FragmentPreciosBinding;

public class PreciosFragment extends Fragment {

    private PreciosViewModel vm;
    private FragmentPreciosBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PreciosViewModel.class);
        binding = FragmentPreciosBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        return root;


    }


}