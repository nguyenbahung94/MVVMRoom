package com.example.hungnb.demomvvm.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hungnb.demomvvm.R;
import com.example.hungnb.demomvvm.ui.viewmodel.DialogIsShowing;


public class BaseActivity extends AppCompatActivity {
    private MaterialDialog mDialog;
    protected Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.left_to_right_enter, R.anim.left_to_right_exit);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }

    protected void setTitle(String title) {
        ((TextView) mToolbar.findViewById(R.id.tvTitle)).setText(title);
    }


    @Override
    public void finish() {
        overridePendingTransition(R.anim.right_to_left_enter, R.anim.right_to_left_exit);
        super.finish();
    }

    protected void showMasterialDialog() {
        if (mDialog == null) {
            mDialog = new MaterialDialog.Builder(this)
                    .content(R.string.message_loading)
                    .cancelable(false)
                    .build();
        }
        mDialog.show();
    }

    protected void hideMasterialDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void hideKeyBroad() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void checkDialog(DialogIsShowing isShowing) {
        if (isShowing.isShowing) {
            showMasterialDialog();
        } else hideMasterialDialog();
    }
}
