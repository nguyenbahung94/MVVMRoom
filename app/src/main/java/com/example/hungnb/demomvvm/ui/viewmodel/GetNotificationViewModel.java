package com.example.hungnb.demomvvm.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.hungnb.demomvvm.api.Api;
import com.example.hungnb.demomvvm.db.entity.NotificationEntity;
import com.example.hungnb.demomvvm.db.repo.NotificationRepository;
import com.example.hungnb.demomvvm.ui.model.response.GetListNotiResponse;
import com.example.hungnb.demomvvm.ui.model.response.GetListNotifications;
import com.example.hungnb.demomvvm.util.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class GetNotificationViewModel extends AndroidViewModel {
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    private Retrofit retrofit;
    private LiveData<List<NotificationEntity>> listNotification;
    private MutableLiveData<DialogIsShowing> isDialogShowing;
    private DialogIsShowing isShowing = new DialogIsShowing();
    private MutableLiveData<String> message;
    private NotificationRepository repository;

    public GetNotificationViewModel(@NonNull Application application) {
        super(application);
        repository = new NotificationRepository(application);
        listNotification = repository.getAllNotification();

    }

    public LiveData<List<NotificationEntity>> loadNotifications() {
        return listNotification;
    }

    public LiveData<String> loadMessage() {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        return message;
    }

    public LiveData<DialogIsShowing> loadDialogShowing() {
        if (isDialogShowing == null) {
            isDialogShowing = new MutableLiveData<>();
        }
        return isDialogShowing;
    }

    public void getListNotification(String cusomterID, int count, int firstNoti, int lastNoti) {
        retrofit.create(Api.class).getListNoti(cusomterID,
                Utils.Name.LANGUAGE_EN, count, firstNoti, lastNoti)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetListNotiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetListNotiResponse getListNotiResponse) {
                        if (getListNotiResponse.isSuccess()) {
                            insert(getListNotiResponse, repository);
                        } else {
                            message.setValue(getListNotiResponse.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideDialog(e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void hideDialog(String str) {
        isShowing.isShowing = false;
        isDialogShowing.setValue(isShowing);
        message.setValue(str);
    }

    private void insert(GetListNotiResponse listNotiResponse, NotificationRepository repository) {
        List<NotificationEntity> listEntity = new ArrayList<>();
        for (GetListNotifications notification : listNotiResponse.getData().getNotifications()) {
            listEntity.add(new NotificationEntity(notification.getNotificationId(), notification.getText(), notification.getTimeAgo(), notification.getPropertyName(), notification.getIcon()));
        }
        repository.insert(listEntity);
    }

}
