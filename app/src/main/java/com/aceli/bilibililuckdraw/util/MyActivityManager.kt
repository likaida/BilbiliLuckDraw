package com.aceli.bilibililuckdraw.util

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * 获取当前正在运行的activity
 */
object MyActivityManager {

    private var sCurrentActivityWeakRef: WeakReference<Activity?>? = null

    var currentActivity: Activity?
        get() {
            var currentActivity: Activity? = null
            if (sCurrentActivityWeakRef != null && sCurrentActivityWeakRef!!.get() != null) {
                currentActivity = sCurrentActivityWeakRef!!.get()
            }
            return currentActivity
        }
        set(activity) {
            sCurrentActivityWeakRef = WeakReference(activity)
        }
}

