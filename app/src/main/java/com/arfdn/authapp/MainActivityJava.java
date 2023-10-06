package com.arfdn.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arfdn.authapp.databinding.ActivityMainJavaBinding;

public class MainActivityJava extends AppCompatActivity {

    private ActivityMainJavaBinding binding;
    private PrefManagerJava prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefManager = PrefManagerJava.getInstance(this);
        checkLoginStatus();

        binding.txtUsername.setText(prefManager.getUsername());
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setLoggedIn(false);
                startActivity(new Intent(MainActivityJava.this, LoginActivityJava.class));
                finish();
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.clear();
                finish();
            }
        });
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = prefManager.isLoggedIn();
        if (!isLoggedIn) {
            startActivity(new Intent(MainActivityJava.this, LoginActivityJava.class));
            finish();
        }
    }
}