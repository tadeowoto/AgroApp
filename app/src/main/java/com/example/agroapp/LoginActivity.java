package com.example.agroapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.agroapp.databinding.ActivityLoginBinding;
import com.example.agroapp.menu.MenuActivity;

public class LoginActivity extends AppCompatActivity {

    LoginActivityViewModel vm;
    ActivityLoginBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());


        binding.btnLogin.setOnClickListener(v -> {
            String usuario = binding.etUsuario.getText().toString();
            String password = binding.etPassword.getText().toString();
            vm.validarUsuario(usuario, password);
        });

        vm.getErrorNombre().observe(this, error -> {
            binding.tvErrorUsuario.setText(error);
            binding.tvErrorUsuario.setVisibility((View.VISIBLE));
        });

        vm.getErrorPassword().observe(this, error -> {
            binding.tvErrorPassword.setText(error);
            binding.tvErrorPassword.setVisibility(View.VISIBLE);
        });

        vm.getLoginExitoso().observe(this, loginExitoso -> {
            Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });


        setContentView(binding.getRoot());

    }
}