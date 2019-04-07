package com.android.apimultiscreen.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class InternetCheckUtil {
    private ConnectivityManager connectivityManager;
    private NetworkInfo info;

    /**
     * method to check internet connection
     * @param context context
     * @return boolean value true or false accordingly
     */
    public boolean checkConnection(Context context) {
        boolean flag = false;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = connectivityManager.getActiveNetworkInfo();

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                flag = true;
            }
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                flag = true;
            }
        } catch (Exception exception) {
            System.out.println("Exception at network connection....."
                    + exception);
        }
        return flag;
    }

}
