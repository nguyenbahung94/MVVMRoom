package com.example.hungnb.demomvvm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hungnb.demomvvm.db.dao.NotificationDao;
import com.example.hungnb.demomvvm.db.entity.NotificationEntity;

@Database(entities = {NotificationEntity.class}, version = 1)
public abstract class NotificationRoomDatabase extends RoomDatabase {
    public abstract NotificationDao notificationDao();

    private static NotificationRoomDatabase INSTANCE;

    public static NotificationRoomDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotificationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotificationRoomDatabase.class, "notification_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
