package com.example.preparationtaskproject.activity.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.preparationtaskproject.activity.model.ModelImpl;
import com.example.preparationtaskproject.activity.model.ModelInt;
import com.example.preparationtaskproject.activity.pojo.UserResponse;
import com.example.preparationtaskproject.activity.view.ViewInt;

/**
 * Created by Aya on 12/04/2018.
 */

public class PresenterImpl implements PresenterInt{

    String mail;
    String password;
    ViewInt viewInt;
    ModelInt modelInt;
    Context context;
    int validationValue;
    int res;
    public PresenterImpl(ViewInt viewInt, Context context) {
        this.viewInt = viewInt;
        this.context=context;
        modelInt=new ModelImpl(this);
    }

    @Override
    public void sendData(String mail,String password,Context context) {
        modelInt.validateInput(mail,password,context);
    }


    @Override
    public void returnResult(int result) {
        if(result==2)
        {
            viewInt.loginSuccess();
        }
        else if(result==3)
        {
            viewInt.loginFailed();
        }
        else
        {
            viewInt.waiting();
        }

    }
}
