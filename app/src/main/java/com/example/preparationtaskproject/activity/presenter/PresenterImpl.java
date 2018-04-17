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
    public PresenterImpl(ViewInt viewInt, Context context) {
        this.viewInt = viewInt;
        this.context=context;
        modelInt=new ModelImpl(this);
    }

    @Override
    public void sendData(String mail,String password,Context context) {
        validationValue= modelInt.validateInput(mail,password,context);
        modelInt.resetResult();
        //Toast.makeText(context,"mail is"+ mail +"password is"+password+"validation value is"+validationValue,Toast.LENGTH_LONG).show();
//        if(validationValue==0)
//        {
//           viewInt.emptyFields();
//        }
//        else if(validationValue==1)
//        {
//            viewInt.invalidMail();
//        }
        if(validationValue==2)
        {
            viewInt.loginSuccess();
        }
        else if(validationValue==3)
        {
           viewInt.loginFailed();
        }
        else{
           viewInt.waiting();
        }

    }

    @Override
    public void onSuccess(UserResponse userResponse) {

    }
}
