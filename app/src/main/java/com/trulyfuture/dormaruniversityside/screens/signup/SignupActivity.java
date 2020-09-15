package com.trulyfuture.dormaruniversityside.screens.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.trulyfuture.dormaruniversityside.R;
import com.trulyfuture.dormaruniversityside.databinding.ActivitySignupBinding;
import com.trulyfuture.dormaruniversityside.screens.login.LoginActivity;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       setupViews();
    }

    private void setupViews(){

        binding.loginBtn.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            finish();
        });
    }



}