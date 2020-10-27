package com.trulyfuture.dormaruniversityside.screens.liveSessions;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.trulyfuture.dormaruniversityside.DormArRepository.DormARRepository;
import com.trulyfuture.dormaruniversityside.model.DormArResults;

import java.util.HashMap;

public class LiveSessionsViewModel extends AndroidViewModel {

    private DormARRepository repository;

    public LiveSessionsViewModel(@NonNull Application application) {
        super(application);
        repository=new DormARRepository(application);
    }

    public MutableLiveData<DormArResults> addSessions(HashMap<String,Object> sessionMap){
        return repository.addSession(sessionMap);
    }
}
