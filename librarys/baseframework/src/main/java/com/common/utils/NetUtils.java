package com.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class NetUtils {
    private NetUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    /**
     *
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否有网络，需要加上访问网络状态的权限
     */
    public static boolean hasNetwork(Context context) {
        ConnectivityManager con = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        if (workinfo == null || !workinfo.isAvailable()) {
            return false;
        }
        return true;
    }
    /**
     * 判断是否是WiFi网络
     */
    public static boolean isWifiNet(Context context) {
        ConnectivityManager con = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        return workinfo != null
                && workinfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        //整体
        activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

    }

}