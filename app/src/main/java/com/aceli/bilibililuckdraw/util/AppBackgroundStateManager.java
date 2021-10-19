package com.aceli.bilibililuckdraw.util;

import java.util.ArrayList;
import java.util.List;

/**
 * app前后端切换管理类
 */

public class AppBackgroundStateManager {

    public static int activityCount = 0;
    public static boolean isOnBackground = false;

    private static AppBackgroundStateManager appBackgroundStateManager = new AppBackgroundStateManager();

    public static AppBackgroundStateManager getInstance() {
        return appBackgroundStateManager;
    }

    public List<OnAppBackgroundStateChangeListener> onAppBackgroundStateChangeListeners = new ArrayList<>();


    public void addOnAppBackgroundStateChangeListener(OnAppBackgroundStateChangeListener onAppBackgroudStateChangeListener) {
        onAppBackgroundStateChangeListeners.add(onAppBackgroudStateChangeListener);
    }

    public void removeOnAppBackgroundStateChangeListener(OnAppBackgroundStateChangeListener onAppBackgroudStateChangeListener) {
        onAppBackgroundStateChangeListeners.remove(onAppBackgroudStateChangeListener);
    }

    public interface OnAppBackgroundStateChangeListener {
        void onAppForeground();
        void onAppBackground();
    }
}
