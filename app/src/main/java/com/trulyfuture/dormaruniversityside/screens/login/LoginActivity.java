package com.trulyfuture.dormaruniversityside.screens.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.trulyfuture.dormaruniversityside.databinding.ActivityLoginBinding;
import com.trulyfuture.dormaruniversityside.screens.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViews();
    }

    private void setupViews(){
        binding.signupBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            finish();
        });
    }

}