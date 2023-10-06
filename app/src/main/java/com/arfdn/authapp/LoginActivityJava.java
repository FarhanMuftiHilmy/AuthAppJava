package com.arfdn.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arfdn.authapp.databinding.ActivityLoginJavaBinding;

public class LoginActivityJava extends AppCompatActivity {

    private ActivityLoginJavaBinding binding;
    private PrefManagerJava prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefManager = PrefManagerJava.getInstance(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.edtUsername.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivityJava.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                } else {
                    if (isValidUsernamePassword()) {
                        prefManager.setLoggedIn(true);
                        checkLoginStatus();
                    } else {
                        Toast.makeText(LoginActivityJava.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityJava.this, RegisterActivityJava.class));
            }
        });
    }

    private boolean isValidUsernamePassword() {
        String username = prefManager.getUsername();
        String password = prefManager.getPassword();
        String inputUsername = binding.edtUsername.getText().toString();
        String inputPassword = binding.edtPassword.getText().toString();

        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = prefManager.isLoggedIn();
        if (isLoggedIn) {
            Toast.makeText(LoginActivityJava.this, "Login berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivityJava.this, MainActivityJava.class));
            finish();
        } else {
            Toast.makeText(LoginActivityJava.this, "Login gagal", Toast.LENGTH_SHORT).show();
        }
    }
}