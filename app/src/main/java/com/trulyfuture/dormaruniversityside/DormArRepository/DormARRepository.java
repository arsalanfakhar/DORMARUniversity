package com.trulyfuture.dormaruniversityside.DormArRepository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.trulyfuture.dormaruniversityside.model.DormArResults;
import com.trulyfuture.dormaruniversityside.model.Results;
import com.trulyfuture.dormaruniversityside.retrofit.DormArApiInterface;
import com.trulyfuture.dormaruniversityside.retrofit.RetrofitService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DormARRepository {

    private Application application;
    private DormArApiInterface apiInterface;

    public DormARRepository(Application application) {
        this.application = application;
        apiInterface = RetrofitService.getInterface();
    }


    public MutableLiveData<DormArResults> loginAgent(HashMap<String,Object> userMap){
        MutableLiveData<DormArResults> data = new MutableLiveData<>();

        Call<DormArResults> dormArResultsCall=apiInterface.loginUser(userMap);

        dormArResultsCall.enqueue(new Callback<DormArResults>() {
            @Override
            public void onResponse(Call<DormArResults> call, Response<DormArResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.postValue(response.body());
                } else {
                    Toast.makeText(application.getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DormArResults> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return data;
    }

    public MutableLiveData<DormArResults> addAgent(HashMap<String,Object> userMap){
        MutableLiveData<DormArResults> data = new MutableLiveData<>();

        Call<DormArResults> dormArResultsCall=apiInterface.addUser(userMap);

        dormArResultsCall.enqueue(new Callback<DormArResults>() {
            @Override
            public void onResponse(Call<DormArResults> call, Response<DormArResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.postValue(response.body());
                } else {
                    Toast.makeText(application.getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DormArResults> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return data;

    }

    public MutableLiveData<DormArResults> addSession(HashMap<String,Object> sessionMap){
        MutableLiveData<DormArResults> data = new MutableLiveData<>();

        Call<DormArResults> dormArResultsCall=apiInterface.addSession(sessionMap);

        dormArResultsCall.enqueue(new Callback<DormArResults>() {
            @Override
            public void onResponse(Call<DormArResults> call, Response<DormArResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.postValue(response.body());
                } else {
                    Toast.makeText(application.getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DormArResults> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return data;

    }

}
