package com.example.preparationtaskproject.activity.pojo;

/**
 * Created by Aya on 08/04/2018.
 */

public class User {
    String email;
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
