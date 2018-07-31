package com.example.hungnb.demomvvm.di.component;

import com.example.hungnb.demomvvm.di.component.scope.CustomScope;
import com.example.hungnb.demomvvm.ui.view.GetNotificationActivity;
import com.example.hungnb.demomvvm.ui.view.LoginActivity;

import dagger.Component;

@CustomScope
@Component(dependencies = {NetComponent.class})
public interface MainComponent {
    void inject(LoginActivity loginActivity);

    void inject(GetNotificationActivity getNotificationActivity);
}
