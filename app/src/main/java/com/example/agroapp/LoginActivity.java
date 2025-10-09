package com.example.agroapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.agroapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    LoginActivityViewModel vm;
    ActivityLoginBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_login);

    }
}