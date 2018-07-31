package com.example.hungnb.demomvvm;

import android.app.Application;

import com.example.hungnb.demomvvm.di.component.DaggerNetComponent;
import com.example.hungnb.demomvvm.di.component.NetComponent;
import com.example.hungnb.demomvvm.di.module.AppModule;
import com.example.hungnb.demomvvm.di.module.NetworkModule;

public class App extends Application {
    private static App instane;
    private static NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instane = this;
        netComponent = DaggerNetComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .build();
    }

    public static NetComponent getNetComponent() {
        return netComponent;
    }

    public static Application getInstance() {
        return instane;
    }
}
