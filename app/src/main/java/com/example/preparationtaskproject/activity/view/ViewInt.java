package com.example.preparationtaskproject.activity.view;

import android.content.Context;

/**
 * Created by Aya on 08/04/2018.
 */

public interface ViewInt {
    public void loginSuccess();
    public void loginFailed();
    public void emptyFields();
    public void invalidMail();
//    public String getMail(Context context);
//    public String getPassword(Context context);
    public void waiting ();
}
