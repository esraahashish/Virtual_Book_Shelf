package com.example.mahmayar.virtualshelfbrowser;

public class AuthenticationSystem {
    final String userName;
    final String password;

    public AuthenticationSystem ()
    {
        userName = "admin";
        password = "123456";
    }

    public boolean verify(String userName, String password)
    {
        return this.userName.equals(userName) && this.password.equals(password);
    }
}