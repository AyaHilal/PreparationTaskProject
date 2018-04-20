package com.example.preparationtaskproject.activity.presenter;

import android.content.Context;
import android.view.View;

import com.example.preparationtaskproject.activity.pojo.User;
import com.example.preparationtaskproject.activity.pojo.UserResponse;
import com.example.preparationtaskproject.activity.view.ViewInt;

/**
 * Created by Aya on 08/04/2018.
 */

public interface PresenterInt {
    public void sendData(String mail,String password, Context context);
    public void returnResult(int result);
}
