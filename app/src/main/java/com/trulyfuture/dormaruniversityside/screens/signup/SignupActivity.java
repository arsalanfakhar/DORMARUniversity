package com.trulyfuture.dormaruniversityside.screens.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.trulyfuture.dormaruniversityside.MainActivity;
import com.trulyfuture.dormaruniversityside.R;
import com.trulyfuture.dormaruniversityside.databinding.ActivitySignupBinding;
import com.trulyfuture.dormaruniversityside.screens.login.LoginActivity;
import com.trulyfuture.dormaruniversityside.screens.login.LoginViewModel;
import com.trulyfuture.dormaruniversityside.utils.ProgressDialog;
import com.trulyfuture.dormaruniversityside.utils.SharedPreferenceClass;

import java.util.Calendar;
import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private SignupViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViews();
    }

    private void setupViews() {

        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);


        binding.signupBtn.setOnClickListener(v -> {
            if (!isFieldEmpty()) {
                ProgressDialog.showLoader(this);

                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("firstName", binding.firstName.getText().toString());
                userMap.put("lastName", binding.lastName.getText().toString());
                userMap.put("email", binding.email.getText().toString());
                userMap.put("password", binding.password.getText().toString());
                userMap.put("uniId", binding.universityId.getText().toString());
                userMap.put("phNum", binding.phoneNum.getText().toString());

                viewModel.addUser(userMap).observe(this, dormArResults -> {
                    ProgressDialog.hideLoader();
                    Toast.makeText(this,dormArResults.getResults().getMessage(),Toast.LENGTH_SHORT).show();
                    if (dormArResults.getResults().getCode()==1){
                        addToSharedPrefs(dormArResults.getResults().getId());
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        finish();
                    }
                });


            }
        });

        binding.loginBtn.setOnClickListener(v -> {

            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });


    }

    private boolean isFieldEmpty() {
        if (TextUtils.isEmpty(binding.firstName.getText().toString())
                || TextUtils.isEmpty(binding.lastName.getText().toString())
                || TextUtils.isEmpty(binding.email.getText().toString())
                || TextUtils.isEmpty(binding.password.getText().toString())
                || TextUtils.isEmpty(binding.universityId.getText())
                || TextUtils.isEmpty(binding.phoneNum.getText())) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    private void addToSharedPrefs(int userId){
        SharedPreferenceClass sharedPreferenceClass = new SharedPreferenceClass(this, SharedPreferenceClass.UserDetails);
        sharedPreferenceClass.SetIntegerEditor("userId",userId);
        sharedPreferenceClass.DoCommit();
    }

}