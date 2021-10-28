package com.aceli.bilibililuckdraw.util;//


import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import java.io.File;

public class AndroidUtils {
    private static float density = 1.0F;
    public static Context applicationContext;
    public static volatile Handler applicationHandler;
    private static DispatchQueue workQueue = new DispatchQueue("workQueue");
    private static DispatchQueue storageQueue = new DispatchQueue("storageQueue");

    public AndroidUtils() {
    }

    public static void onCreate(Context context) {
        applicationContext = context;
        applicationHandler = new Handler(applicationContext.getMainLooper());
        Resources resources = applicationContext.getResources();
        if (resources != null) {
            density = applicationContext.getResources().getDisplayMetrics().density;
        }
    }

    public static DispatchQueue getWorkQueue() {
        return workQueue;
    }

    public static DispatchQueue getStorageQueue() {
        return storageQueue;
    }

    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0L);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0L) {
            applicationHandler.post(runnable);
        } else {
            applicationHandler.postDelayed(runnable, delay);
        }

    }

    public static void cancelRunOnUIThread(Runnable runnable) {
        applicationHandler.removeCallbacks(runnable);
    }

    public static int dp(float value) {
        return value == 0.0F ? 0 : (int) Math.ceil((double) (density * value));
    }

    public static int getCPUCount() {
        int ret = Runtime.getRuntime().availableProcessors();
        ret = ret < 2 ? 2 : (ret > 8 ? 8 : ret);
        return ret;
    }

    public static boolean isNetworkAvailable() {
        try {
            ConnectivityManager e = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = e.getActiveNetworkInfo();
            if (netInfo == null || !netInfo.isConnectedOrConnecting() && !netInfo.isAvailable()) {
                netInfo = e.getNetworkInfo(0);
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                    return true;
                } else {
                    netInfo = e.getNetworkInfo(1);
                    return netInfo != null && netInfo.isConnectedOrConnecting();
                }
            } else {
                return true;
            }
        } catch (Exception var2) {
            return true;
        }
    }

    public static File getCacheDir() {
        String state = null;

        try {
            state = Environment.getExternalStorageState();
        } catch (Exception var4) {
            Log.e("AndroidUtilities", "" + var4);
        }

        File e;
        if (state == null || state.startsWith("mounted")) {
            try {
                e = applicationContext.getExternalCacheDir();
                if (e != null) {
                    return e;
                }
            } catch (Exception var3) {
                Log.e("AndroidUtilities", "" + var3);
            }
        }

        try {
            e = applicationContext.getCacheDir();
            if (e != null) {
                return e;
            }
        } catch (Exception var2) {
            Log.e("AndroidUtilities", "" + var2);
        }

        return new File("");
    }
}
