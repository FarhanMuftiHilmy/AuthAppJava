package com.arfdn.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arfdn.authapp.databinding.ActivityRegisterJavaBinding;

public class RegisterActivityJava extends AppCompatActivity {

    private ActivityRegisterJavaBinding binding;
    private PrefManagerJava prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefManager = PrefManagerJava.getInstance(this);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.edtUsername.getText().toString();
                String password = binding.edtPassword.getText().toString();
                String confirmPassword = binding.edtPasswordConfirm.getText().toString();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterActivityJava.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivityJava.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
                } else {
                    prefManager.saveUsername(username);
                    prefManager.savePassword(password);
                    prefManager.setLoggedIn(true);
                    checkLoginStatus();
                }
            }
        });

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivityJava.this, LoginActivityJava.class));
            }
        });
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = prefManager.isLoggedIn();
        if (isLoggedIn) {
            Toast.makeText(RegisterActivityJava.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivityJava.this, MainActivityJava.class));
            finish();
        } else {
            Toast.makeText(RegisterActivityJava.this, "Registrasi gagal", Toast.LENGTH_SHORT).show();
        }
    }
}