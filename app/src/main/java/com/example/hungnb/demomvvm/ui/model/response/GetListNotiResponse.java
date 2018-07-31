package com.example.hungnb.demomvvm.ui.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hung.nb on 17/07/2018.
 */

public class GetListNotiResponse {

    @Expose
    @SerializedName("Success")
    private boolean Success;
    @Expose
    @SerializedName("Data")
    private GetListNotiData Data;

    @Expose
    @SerializedName("ErrorMessage")
    private String ErrorMessage;

    @Expose
    @SerializedName("ErrorCode")
    private String ErrorCode;

    public boolean isSuccess() {
        return Success;
    }

    public GetListNotiData getData() {
        return Data;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }
}
