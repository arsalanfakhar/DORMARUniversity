package com.trulyfuture.dormaruniversityside.screens.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.trulyfuture.dormaruniversityside.MainActivity;
import com.trulyfuture.dormaruniversityside.databinding.ActivityLoginBinding;
import com.trulyfuture.dormaruniversityside.screens.signup.SignupActivity;
import com.trulyfuture.dormaruniversityside.utils.ProgressDialog;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViews();
    }

    private void setupViews() {

        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);

        binding.loginBtn.setOnClickListener(v -> {

            if (!isFieldEmpty()){
                ProgressDialog.showLoader(this);

                HashMap<String,Object> hashMap=new HashMap<>();

                hashMap.put("email",binding.usernameText.getText().toString());
                hashMap.put("password",binding.passwordText.getText().toString());

                loginViewModel.loginUser(hashMap).observe(this,dormArResults -> {
                    ProgressDialog.hideLoader();

                    if (dormArResults.getResults().getCode()==1){
                        Toast.makeText(this,"Login Sucessfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(this,dormArResults.getResults().getMessage(),Toast.LENGTH_SHORT).show();
                    }



                });

            }

        });


        binding.signupBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        });


    }

    private boolean isFieldEmpty() {
        if (TextUtils.isEmpty(binding.usernameText.getText())
                || TextUtils.isEmpty(binding.passwordText.getText())) {
            Toast.makeText(this,"Fields are empty",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }


}