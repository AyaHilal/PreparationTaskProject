package com.example.preparationtaskproject.activity.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preparationtaskproject.R;
import com.example.preparationtaskproject.activity.model.PresenterImpl;
import com.example.preparationtaskproject.activity.presenter.PresenterInt;
import com.example.preparationtaskproject.activity.view.ViewInt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ViewInt {


    int x;
    PresenterInt presenterInt;
    @BindView(R.id.username) EditText userName;
    @BindView(R.id.password) EditText password;
    @OnClick(R.id.loginBtn)
    public void submit()
    {
      String userNameInput = userName.getText().toString();
      String passwordInput = password.getText().toString();
      presenterInt.validateInput(userNameInput,passwordInput);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences("tag",MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag",false);
        if(flag)
        {
            loginSuccess();
        }
        else {
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

}
