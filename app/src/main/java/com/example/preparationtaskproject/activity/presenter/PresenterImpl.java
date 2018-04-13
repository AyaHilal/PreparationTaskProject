package com.example.preparationtaskproject.activity.presenter;

import android.content.Context;
import android.view.View;

import com.example.preparationtaskproject.activity.model.ModelImpl;
import com.example.preparationtaskproject.activity.model.ModelInt;
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
    public PresenterImpl(ViewInt viewInt, Context context) {
        this.viewInt = viewInt;
        this.context=context;
        modelInt=new ModelImpl();
    }

    @Override
    public void sendData(ViewInt viewInt,Context context) {
        mail=viewInt.getMail(context);
        password=viewInt.getPassword(context);
        validationValue= modelInt.validateInput(mail,password,context);
        if(validationValue==0)
        {
            viewInt.emptyFields();
        }
        else if(validationValue==1)
        {
            viewInt.invalidMail();
        }
        else if(validationValue==2)
        {
            viewInt.loginSuccess();
        }
        else if(validationValue==3)
        {
            viewInt.loginFailed();
        }
    }
}
