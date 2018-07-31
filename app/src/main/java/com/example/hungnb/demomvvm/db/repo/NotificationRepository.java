package com.example.hungnb.demomvvm.db.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.hungnb.demomvvm.db.NotificationRoomDatabase;
import com.example.hungnb.demomvvm.db.dao.NotificationDao;
import com.example.hungnb.demomvvm.db.entity.NotificationEntity;

import java.util.List;

public class NotificationRepository {
    private NotificationDao notificationDao;
    private LiveData<List<NotificationEntity>> listNotification;

    public NotificationRepository(Application application) {
        NotificationRoomDatabase db = NotificationRoomDatabase.getINSTANCE(application);
        notificationDao = db.notificationDao();
        listNotification = notificationDao.loadAllNotifications();
    }

    public LiveData<List<NotificationEntity>> getAllNotification() {
        return listNotification;
    }

    public void insert(List<NotificationEntity> listNotification) {
        new insertAsyncTask(notificationDao).execute(listNotification);

    }

    public void deleteAll() {
        notificationDao.deleteAll();
    }

    private static class insertAsyncTask extends AsyncTask<List<NotificationEntity>, Void, Void> {
        NotificationDao notificationDao;

        public insertAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<NotificationEntity>... lists) {
            notificationDao.insertAll(lists[0]);
            return null;
        }
    }
}
