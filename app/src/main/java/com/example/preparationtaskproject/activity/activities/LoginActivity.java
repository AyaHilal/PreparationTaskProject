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
import android.text.TextUtils;
import android.util.Patterns;
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
            //presenterInt.sendData(LoginActivity.this, this);
            userNameInput = userName.getText().toString();
            passwordInput = password.getText().toString();
            if (TextUtils.isEmpty(userNameInput) || TextUtils.isEmpty(passwordInput)) {

                emptyFields();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(userNameInput).matches()) {
                invalidMail();
            }
            //el invalid bta3 el check mn el json
            else {

                presenterInt.sendData(userNameInput,passwordInput,this);

            }
        }
        else
        {
            Toast.makeText(this,getResources().getString(R.string.network_error_message),Toast.LENGTH_LONG).show();
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
                presenterInt = new PresenterImpl(this, this);
            }
    }


    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this,getResources().getString(R.string.invalid_login),Toast.LENGTH_LONG).show();
    }

    @Override
    public void emptyFields() {
        Toast.makeText(this,getResources().getString(R.string.empty_fields),Toast.LENGTH_LONG).show();
    }

    @Override
    public void invalidMail() {
        Toast.makeText(this,getResources().getString(R.string.invalid_mail),Toast.LENGTH_LONG).show();

    }

//    @Override
//    public String getMail(Context context) {
//        userNameInput  = userName.getText().toString();
//        return userNameInput;
//    }
//
//    @Override
//    public String getPassword(Context context) {
//        passwordInput = password.getText().toString();
//        return passwordInput;
//    }

    @Override
    public void waiting() {
        Toast.makeText(this,getResources().getString(R.string.load_retrofit),Toast.LENGTH_LONG).show();

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
