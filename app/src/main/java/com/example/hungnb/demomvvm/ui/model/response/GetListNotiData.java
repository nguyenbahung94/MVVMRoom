package com.example.hungnb.demomvvm.ui.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class GetListNotiData {
    @Expose
    @SerializedName("Notifications")
    private List<GetListNotifications> Notifications;
    @Expose
    @SerializedName("TotalNotificationCount")
    private int TotalNotificationCount;
    @Expose
    @SerializedName("UnreadNotificationCount")
    private int UnreadNotificationCount;

    public List<GetListNotifications> getNotifications() {
        return Notifications;
    }

    public int getTotalNotificationCount() {
        return TotalNotificationCount;
    }

    public int getUnreadNotificationCount() {
        return UnreadNotificationCount;
    }
}
