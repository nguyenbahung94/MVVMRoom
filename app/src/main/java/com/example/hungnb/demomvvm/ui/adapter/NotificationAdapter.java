package com.example.hungnb.demomvvm.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hungnb.demomvvm.R;
import com.example.hungnb.demomvvm.db.entity.NotificationEntity;
import com.example.hungnb.demomvvm.ui.listener.OnBottomReachedListener;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {
    private List<NotificationEntity> mNotificationList;
    private Context context;
    private OnBottomReachedListener mOnBottomReachedListener;
    private boolean canCallMore = true;

    public void setCanCallMore() {
        this.canCallMore = false;
    }

    public void setmOnBottomReachedListener(OnBottomReachedListener mOnBottomReachedListener) {
        this.mOnBottomReachedListener = mOnBottomReachedListener;
    }

    public NotificationAdapter(List<NotificationEntity> mNotificationList, Context context) {
        this.mNotificationList = mNotificationList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        NotificationEntity currentNotification = mNotificationList.get(position);
        holder.tvMain.setText(currentNotification.getContent());
        holder.tvTimeAgo.setText(currentNotification.getTimeAgo());
        holder.tvPhase.setText(currentNotification.getPropertyName());
        if (currentNotification.getPropertyName().isEmpty()) {
            holder.llContainImvHome.setVisibility(View.INVISIBLE);
        } else {
            holder.tvPhase.setText(currentNotification.getPropertyName());
        }
        if (currentNotification.getLinkIcon().contains("icn-tick-circle")) {
            holder.imvChecked.setVisibility(View.VISIBLE);
            holder.imvError.setVisibility(View.GONE);
        } else {
            holder.imvChecked.setVisibility(View.GONE);
            holder.imvError.setVisibility(View.VISIBLE);
        }
        if (canCallMore) {
            if (position == mNotificationList.size() - 1) {
                mOnBottomReachedListener.onBottomReached(mNotificationList.size());
            }
        }


    }

    @Override
    public int getItemCount() {
        return mNotificationList == null ? 0 : mNotificationList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imvChecked;
        private TextView tvMain;
        private TextView tvTimeAgo;
        private TextView tvPhase;
        private ImageView imvError;
        private LinearLayout llContainImvHome;

        private Holder(View itemView) {
            super(itemView);
            imvChecked = itemView.findViewById(R.id.imvChecked);
            tvMain = itemView.findViewById(R.id.tvMain);
            tvTimeAgo = itemView.findViewById(R.id.tvTimeAgo);
            tvPhase = itemView.findViewById(R.id.tvPhase);
            imvError = itemView.findViewById(R.id.imvError);
            llContainImvHome = itemView.findViewById(R.id.llContainImvHome);
        }
    }
}
