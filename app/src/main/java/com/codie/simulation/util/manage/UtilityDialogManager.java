package com.codie.simulation.util.manage;


import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;

import com.codie.simulation.ui.activity.MainActivity;


public class UtilityDialogManager {
    private static MainActivity mainActivity;
    private static ProgressDialog mProgressDialog;

    public static ProgressDialog getInstance(MainActivity mainActivity, String caption) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mainActivity);
            mProgressDialog.setMessage(caption);
            mProgressDialog.setIndeterminate(true);
        }

        return mProgressDialog;
    }

    private UtilityDialogManager(MainActivity mainActivity) {
    }

    public static void showMessageDialog(String title, String message) {
        AlertDialog ad = new AlertDialog.Builder(mainActivity)
                .setTitle(title)
                .setMessage(message)
                .create();
        ad.show();
    }

    public static void showProgressDialog(String caption) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mainActivity);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.setMessage(caption);
        mProgressDialog.show();
    }

    public static void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
