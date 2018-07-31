package com.example.hungnb.demomvvm.ui.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @Expose
    @SerializedName("Success")
    private boolean Success;
    @Expose
    @SerializedName("Data")
    private LoginData Data;
    @Expose
    @SerializedName("ErrorMessage")
    private String ErrorMessage;
    @Expose
    @SerializedName("ErrorCode")
    private int ErrorCode;

    public boolean isSuccess() {
        return Success;
    }

    public LoginData getData() {
        return Data;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public int getErrorCode() {
        return ErrorCode;
    }
}
