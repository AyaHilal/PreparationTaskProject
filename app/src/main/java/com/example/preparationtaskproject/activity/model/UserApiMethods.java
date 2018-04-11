package com.example.preparationtaskproject.activity.model;

import com.example.preparationtaskproject.activity.pojo.User;
import com.example.preparationtaskproject.activity.pojo.UserResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Aya on 08/04/2018.
 */

public interface UserApiMethods {
    @POST("Tamini/login")
    Call<UserResponse> postUser (@Body User user);
}
