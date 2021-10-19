package com.aceli.bilibililuckdraw

import android.app.Application
import androidx.annotation.Keep

@Keep
object AppContext {
    var mApplication: Application? = null
    var isDebug = false

    fun init(application: Application) {
        com.aceli.bilibililuckdraw.AppContext.mApplication = application
    }

}