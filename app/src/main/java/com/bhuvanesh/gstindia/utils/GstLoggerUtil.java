package com.bhuvanesh.gstindia.utils;


import android.util.Log;

import com.bhuvanesh.gstindia.Config;


public class GstLoggerUtil {

    private static boolean mLogEnabled = Config.DEBUG;

    public static void debug(Object tag, String msg) {
        if (mLogEnabled) {
            //log tag becomes a package name
            Log.d(tag.toString(), msg);
        }
    }

    public static void error(Object tag, String msg) {
        if (mLogEnabled) {
            Log.e(tag.toString(), msg);
        }
    }

    public static void println(String msg) {
        if (mLogEnabled) {
            System.out.println(msg);
        }
    }
}
