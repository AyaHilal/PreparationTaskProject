package com.example.preparationtaskproject.activity.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import com.example.preparationtaskproject.activity.pojo.User;
import com.example.preparationtaskproject.activity.pojo.UserResponse;
import com.example.preparationtaskproject.activity.presenter.PresenterInt;
import com.example.preparationtaskproject.activity.view.ViewInt;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Aya on 08/04/2018.
 */

public class ModelImpl implements ModelInt {

    UserApiMethods userApiMethods;
    PresenterInt presenterInt;
    int result;
    @Override
    public int validateInput(final String name, final String password, final Context context) {
        User user = new User(name, password);
        userApiMethods = UserApi.getApiUser().create(UserApiMethods.class);
        Call<UserResponse> responseCall = userApiMethods.postUser(user);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            result = 0;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            result = 1;
        }
        //el invalid bta3 el check mn el json
        else {
            responseCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        UserResponse userResponse = response.body();
                        Log.i("result", new Gson().toJson(response) + response.code());
                        if (userResponse.getMessage().equals("Login Success")) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("tag", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("flag", true);
                            editor.commit();
                            result = 2;
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    result = 3;
                }
            });
        }
        return result;
    }

}


