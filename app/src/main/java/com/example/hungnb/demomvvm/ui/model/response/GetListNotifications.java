package com.example.hungnb.demomvvm.ui.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetListNotifications {
    @Expose
    @SerializedName("IsRead")
    private boolean IsRead;
    @Expose
    @SerializedName("TimeAgo")
    private String TimeAgo;
    @Expose
    @SerializedName("Time")
    private String Time;
    @Expose
    @SerializedName("PropertyName")
    private String PropertyName;
    @Expose
    @SerializedName("Icon")
    private String Icon;
    @Expose
    @SerializedName("Link")
    private String Link;
    @Expose
    @SerializedName("Text")
    private String Text;
    @Expose
    @SerializedName("EventCategoryCode")
    private String EventCategoryCode;
    @Expose
    @SerializedName("EventCode")
    private String EventCode;
    @Expose
    @SerializedName("MessageId")
    private int MessageId;
    @Expose
    @SerializedName("NotificationId")
    private int NotificationId;

    public GetListNotifications(String timeAgo, String propertyName, String icon, String text, int notificationId) {
        TimeAgo = timeAgo;
        PropertyName = propertyName;
        Icon = icon;
        Text = text;
        NotificationId = notificationId;
    }

    public boolean isRead() {
        return IsRead;
    }

    public String getTimeAgo() {
        return TimeAgo;
    }

    public String getTime() {
        return Time;
    }

    public String getPropertyName() {
        return PropertyName;
    }

    public String getIcon() {
        return Icon;
    }

    public String getLink() {
        return Link;
    }

    public String getText() {
        return Text;
    }

    public String getEventCategoryCode() {
        return EventCategoryCode;
    }

    public String getEventCode() {
        return EventCode;
    }

    public int getMessageId() {
        return MessageId;
    }

    public int getNotificationId() {
        return NotificationId;
    }
}
