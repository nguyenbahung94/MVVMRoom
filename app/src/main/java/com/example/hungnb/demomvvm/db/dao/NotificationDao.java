package com.example.hungnb.demomvvm.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.hungnb.demomvvm.db.entity.NotificationEntity;

import java.util.List;

@Dao
public interface NotificationDao {
    @Query("SELECT * FROM notificaton_table")
    LiveData<List<NotificationEntity>> loadAllNotifications();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NotificationEntity> listNotification);

    @Query("DELETE FROM notificaton_table")
    void deleteAll();
}
