package com.topaz.lockids;

import android.app.Application;

import com.lantern.lantern.RYLA;

/**
 * Created by yky on 2017. 6. 4..
 */

public class LockidsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RYLA.getInstance().setContext("C+/shvCKnAWD6wE9XM04PIOxt2XhFQR1RGo5wRDgIvg=", this).startResDump();
    }
}
