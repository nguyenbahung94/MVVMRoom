package com.example.hungnb.demomvvm.di.component;

import android.app.Application;

import com.example.hungnb.demomvvm.di.module.AppModule;
import com.example.hungnb.demomvvm.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetComponent {
    Application context();

    Retrofit retrofit();
}
