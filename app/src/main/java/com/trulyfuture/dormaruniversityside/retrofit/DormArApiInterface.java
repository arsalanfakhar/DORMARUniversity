package com.trulyfuture.dormaruniversityside.retrofit;

import com.trulyfuture.dormaruniversityside.model.DormArResults;

import java.io.StringReader;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DormArApiInterface {

    @POST("agents/login")
    Call<DormArResults> loginUser(@Body HashMap<String,Object> userMap);

    @POST("agents")
    Call<DormArResults> addUser(@Body HashMap<String,Object> userMap);

    @POST("sessions")
    Call<DormArResults> addSession(@Body HashMap<String,Object> sessionMap);


}
