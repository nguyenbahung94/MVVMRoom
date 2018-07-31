package com.example.hungnb.demomvvm.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notificaton_table")
public class NotificationEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    private int NotificationId;
    private String content;
    private String timeAgo;
    private String propertyName;
    private String linkIcon;

    public NotificationEntity(@NonNull int id) {
        this.id = id;
    }

    public NotificationEntity(int notificationId, String content, String timeAgo, String propertyName, String linkIcon) {
        NotificationId = notificationId;
        this.content = content;
        this.timeAgo = timeAgo;
        this.propertyName = propertyName;
        this.linkIcon = linkIcon;
    }

    public void setNotificationId(int notificationId) {
        NotificationId = notificationId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setLinkIcon(String linkIcon) {
        this.linkIcon = linkIcon;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public int getNotificationId() {
        return NotificationId;
    }

    public String getContent() {
        return content;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getLinkIcon() {
        return linkIcon;
    }
}
