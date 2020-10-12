package com.trulyfuture.dormaruniversityside.screens.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.trulyfuture.dormaruniversityside.DormArRepository.DormARRepository;
import com.trulyfuture.dormaruniversityside.model.DormArResults;

import java.util.HashMap;

public class SignupViewModel extends AndroidViewModel {

    private DormARRepository repository;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        repository=new DormARRepository(application);
    }

    public MutableLiveData<DormArResults> addUser(HashMap<String,Object> userMap){
        return repository.addAgent(userMap);
    }

}
