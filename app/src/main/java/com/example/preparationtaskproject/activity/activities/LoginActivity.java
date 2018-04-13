package com.example.preparationtaskproject.activity.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preparationtaskproject.R;
import com.example.preparationtaskproject.activity.model.ModelImpl;
import com.example.preparationtaskproject.activity.presenter.PresenterImpl;
import com.example.preparationtaskproject.activity.presenter.PresenterInt;
import com.example.preparationtaskproject.activity.view.ViewInt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ViewInt {
    int x;
    String userNameInput;
    String passwordInput;
    PresenterInt presenterInt;
    @BindView(R.id.username) EditText userName;
    @BindView(R.id.password) EditText password;
    @OnClick(R.id.loginBtn)
    public void submit() {
        boolean resultConnection =checkInternetConnection();
        if(resultConnection==true) {
            presenterInt.sendData(LoginActivity.this, this);
        }
        else
        {
            Toast.makeText(this,"please check your internet connection",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
            SharedPreferences sharedPreferences = getSharedPreferences("tag", MODE_PRIVATE);
            boolean flag = sharedPreferences.getBoolean("flag", false);
            if (flag) {
                loginSuccess();
            } else {
                presenterInt = new PresenterImpl(LoginActivity.this, this);
            }
    }


    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this,"email or password is incorrect",Toast.LENGTH_LONG).show();
    }

    @Override
    public void emptyFields() {
        Toast.makeText(this,"please enter mail and password",Toast.LENGTH_LONG).show();
    }

    @Override
    public void invalidMail() {
        Toast.makeText(this,"please enter valid mail",Toast.LENGTH_LONG).show();

    }

    @Override
    public String getMail(Context context) {
        userNameInput  = userName.getText().toString();
        return userNameInput;
    }

    @Override
    public String getPassword(Context context) {
        passwordInput = password.getText().toString();
        return passwordInput;
    }

    public boolean checkInternetConnection()
    {
        boolean status = false;
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected())
        {
            status=true;
        }else {
            status=false;
         }
        return status;
    }
}
