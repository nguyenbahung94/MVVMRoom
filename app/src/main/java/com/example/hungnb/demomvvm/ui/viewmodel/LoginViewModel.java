package com.example.hungnb.demomvvm.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.hungnb.demomvvm.App;
import com.example.hungnb.demomvvm.R;
import com.example.hungnb.demomvvm.api.Api;
import com.example.hungnb.demomvvm.db.repo.NotificationRepository;
import com.example.hungnb.demomvvm.ui.model.request.LoginBody;
import com.example.hungnb.demomvvm.ui.model.response.LoginResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginViewModel extends ViewModel {
    private Retrofit retrofit;
    private MutableLiveData<String> message;
    private MutableLiveData<DialogIsShowing> isShowingDialog;
    private MutableLiveData<LoginResponse> resultLogin;
    private DialogIsShowing isShowing = new DialogIsShowing();


    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public LiveData<String> loadResultInput() {
        if (message == null) {
            message = new MutableLiveData<>();

        }
        return message;
    }

    public LiveData<DialogIsShowing> isShowingDialog() {
        if (isShowingDialog == null) {
            isShowingDialog = new MutableLiveData<>();
        }
        return isShowingDialog;
    }

    public LiveData<LoginResponse> loadLogin() {
        if (resultLogin == null) {
            resultLogin = new MutableLiveData<>();
        }
        return resultLogin;
    }

    public void validateInput(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            message.setValue(App.getInstance().getString(R.string.error_email_and_password_cant_be_empty));
        } else {
            if (email.contains("@")) {
                login(email, password);
            } else {
                message.setValue(App.getInstance().getString(R.string.error_email_invalid));
            }
        }
    }

    private void login(final String email, String password) {
        isShowing.isShowing = true;
        isShowingDialog.setValue(isShowing);
        retrofit.create(Api.class).login(new LoginBody(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if (loginResponse.isSuccess()) {
                            resultLogin.setValue(loginResponse);
                            hideDialog(App.getInstance().getString(R.string.message_success));
                        } else {
                            hideDialog(loginResponse.getErrorMessage());
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
        isShowingDialog.setValue(isShowing);
        message.setValue(str);
    }
}
