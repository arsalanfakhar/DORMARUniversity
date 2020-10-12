package com.trulyfuture.dormaruniversityside.screens.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.trulyfuture.dormaruniversityside.DormArRepository.DormARRepository;
import com.trulyfuture.dormaruniversityside.model.DormArResults;

import java.util.HashMap;

public class LoginViewModel extends AndroidViewModel {

    private DormARRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository=new DormARRepository(application);
    }

    public MutableLiveData<DormArResults> loginUser(HashMap<String,Object> userMap){
        return repository.loginAgent(userMap);
    }



}
