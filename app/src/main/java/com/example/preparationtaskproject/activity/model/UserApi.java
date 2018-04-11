package com.example.preparationtaskproject.activity.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aya on 08/04/2018.
 */

public class UserApi {

    public static final String BASEURL="https://private-d3105-tamini.apiary-mock.com/";
    public static Retrofit retrofit = null;
    public static Retrofit getApiUser()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
