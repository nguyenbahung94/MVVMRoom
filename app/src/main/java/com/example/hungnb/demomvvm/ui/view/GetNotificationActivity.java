package com.example.hungnb.demomvvm.ui.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.hungnb.demomvvm.App;
import com.example.hungnb.demomvvm.R;
import com.example.hungnb.demomvvm.db.entity.NotificationEntity;
import com.example.hungnb.demomvvm.di.component.DaggerMainComponent;
import com.example.hungnb.demomvvm.ui.adapter.NotificationAdapter;
import com.example.hungnb.demomvvm.ui.base.BaseActivity;
import com.example.hungnb.demomvvm.ui.listener.OnBottomReachedListener;
import com.example.hungnb.demomvvm.ui.model.response.GetListNotifications;
import com.example.hungnb.demomvvm.ui.model.response.LoginData;
import com.example.hungnb.demomvvm.ui.viewmodel.GetNotificationViewModel;
import com.example.hungnb.demomvvm.util.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class GetNotificationActivity extends BaseActivity implements OnBottomReachedListener {
    @Inject
    Retrofit retrofit;
    private List<NotificationEntity> mListNotificaton = new ArrayList<>();
    private NotificationAdapter mNotificationAdapter;
    private GetNotificationViewModel mViewModel;
    private boolean isCallingApi = false;
    private LoginData loginData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);
        setTitle(getString(R.string.text_notifications));
        DaggerMainComponent.builder().netComponent(App.getNetComponent()).build().inject(this);
        if (getIntent() != null) {
            loginData = getIntent().getParcelableExtra(Utils.NamePassDataIntent.OBJECT_USER);
        }
        initView();
        initNotificationViewModel();
    }

    private void initNotificationViewModel() {
        mViewModel = ViewModelProviders.of(this).get(GetNotificationViewModel.class);
        mViewModel.setRetrofit(retrofit);
        mViewModel.loadMessage().observe(this, this::showMessage);
        mViewModel.loadNotifications().observe(this, this::updateListNotification);
        mViewModel.loadDialogShowing().observe(this, this::checkDialog);
        isCallingApi = true;
        mViewModel.getListNotification(loginData.getCustomerID(), Utils.Name.COUNT_DEFAULT, Utils.Name.FIRST_NOTIFICATION_ID_DEFAULT, Utils.Name.LAST_NOTIFICATION_ID_DEFAULT);

    }

    private void initView() {
        RecyclerView mRecycleView = findViewById(R.id.mRecycleView);
        mRecycleView.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mNotificationAdapter = new NotificationAdapter(mListNotificaton, this);
        mNotificationAdapter.setmOnBottomReachedListener(this);
        mRecycleView.setAdapter(mNotificationAdapter);

    }
   /*
   load more when scroll at the bottom
   * */
    @Override
    public void onBottomReached(int position) {
        if (!isCallingApi) {
            Toast.makeText(this, R.string.message_loading, Toast.LENGTH_SHORT).show();
            isCallingApi = true;
            mViewModel.getListNotification(loginData.getCustomerID(), Utils.Name.COUNT_DEFAULT, mListNotificaton.get(0).getNotificationId(), mListNotificaton.get(mListNotificaton.size() - 1).getNotificationId());
        }
    }

    private void updateListNotification(List<NotificationEntity> listNotifications) {
        isCallingApi = false;
        int sizeBeforeAll = mListNotificaton.size();
        mListNotificaton.addAll(listNotifications);
        mNotificationAdapter.notifyDataSetChanged();
        hideMasterialDialog();
        if (sizeBeforeAll == mListNotificaton.size()){
            mNotificationAdapter.setCanCallMore();
        }
    }
}
