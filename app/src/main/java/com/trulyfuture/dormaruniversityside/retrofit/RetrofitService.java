package com.trulyfuture.dormaruniversityside.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {


    private static final String BASE_URL="https://dormar.herokuapp.com/";
    public static HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static OkHttpClient client=new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public static DormArApiInterface getInterface(){
        return retrofit.create(DormArApiInterface.class);
    }


}
