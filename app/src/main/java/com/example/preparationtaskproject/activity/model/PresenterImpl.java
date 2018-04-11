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

public class PresenterImpl implements PresenterInt {
    ViewInt viewInt;
    UserApiMethods userApiMethods;
    Context context;

    public PresenterImpl(ViewInt viewInt, Context context) {
        this.viewInt = viewInt;
        this.context=context;
    }

    @Override
    public void validateInput(final String name, final String password) {
       User user = new User(name,password);
        userApiMethods = UserApi.getApiUser().create(UserApiMethods.class);
        Call<UserResponse> responseCall=userApiMethods.postUser(user);
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(password))
        {
            viewInt.emptyFields();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(name).matches())
        {
            viewInt.invalidMail();
        }
        //el invalid bta3 el check mn el json
        else
        {
            responseCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        UserResponse userResponse = response.body();
                        Log.i("result", new Gson().toJson(response) + response.code());
                        if (userResponse.getMessage().equals("login success")) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("tag", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("flag", true);
                            editor.commit();
                            viewInt.loginSuccess();
                        } else {
                            viewInt.loginFailed();
                        }
                    }
                }


                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    viewInt.loginFailed();
                }
            });
            }
        }

    }


