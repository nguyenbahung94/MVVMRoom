package com.example.hungnb.demomvvm.ui.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginBody {
    @Expose
    @SerializedName("UserId")
    private String UserId;
    @Expose
    @SerializedName("Password")
    private String Password;

    public LoginBody(String userId, String password) {
        UserId = userId;
        Password = password;
    }
}
