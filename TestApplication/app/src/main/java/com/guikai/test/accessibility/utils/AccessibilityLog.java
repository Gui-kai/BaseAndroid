package com.guikai.test.accessibility.utils;

import android.util.Log;

import com.guikai.test.BuildConfig;

public class AccessibilityLog {

    private static final String TAG = "AccessibilityService";
    public static void printLog(String logMsg) {
        if (!BuildConfig.DEBUG) return;
        Log.e(TAG, logMsg);
    }
}
