package com.example.hungnb.demomvvm.ui.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.hungnb.demomvvm.App;
import com.example.hungnb.demomvvm.R;
import com.example.hungnb.demomvvm.di.component.DaggerMainComponent;
import com.example.hungnb.demomvvm.ui.base.BaseActivity;
import com.example.hungnb.demomvvm.ui.model.response.LoginResponse;
import com.example.hungnb.demomvvm.ui.viewmodel.LoginViewModel;
import com.example.hungnb.demomvvm.util.Utils;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class LoginActivity extends BaseActivity {
    @Inject
    Retrofit retrofit;
    private LoginViewModel mLoginViewModel;
    private EditText mEdtEmail;
    private EditText mEdtPassWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        DaggerMainComponent.builder().netComponent(App.getNetComponent()).build().inject(this);
        setTitle(R.string.text_login);
        initLoginModel();
        initView();
    }

    private void initLoginModel() {
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mLoginViewModel.setRetrofit(retrofit);
        mLoginViewModel.loadResultInput().observe(this, this::showMessage);
        mLoginViewModel.isShowingDialog().observe(this,this::checkDialog);
        mLoginViewModel.loadLogin().observe(this, this::startActivity);
    }

    private void initView() {
        mEdtEmail = findViewById(R.id.edtEmail);
        mEdtPassWord = findViewById(R.id.edtPassWord);
        mEdtEmail.setText("handover10@yopmail.com");
        mEdtPassWord.setText("Test@1234");
        findViewById(R.id.btnLogin).setOnClickListener(view -> mLoginViewModel.validateInput(mEdtEmail.getText().toString(), mEdtPassWord.getText().toString()));
    }

    private void startActivity(LoginResponse loginResponse) {
        Intent intent = new Intent(LoginActivity.this, GetNotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Utils.NamePassDataIntent.OBJECT_USER,loginResponse.getData());
        startActivity(intent);
        finish();
    }

}
