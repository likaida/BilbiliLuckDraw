package com.aceli.bilibililuckdraw

import android.app.Application
import android.view.Gravity
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.facebook.drawee.backends.pipeline.Fresco

class LuckDrawApplication : Application() {
    companion object {
        lateinit var mApp: com.aceli.bilibililuckdraw.LuckDrawApplication

        fun getApplicationObject(): com.aceli.bilibililuckdraw.LuckDrawApplication {
            return mApp
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        AppContext.isDebug =
            com.aceli.bilibililuckdraw.BuildConfig.DEBUG
        AppContext.init(this)
        init()
    }

    private fun init() {
        initToasty()
        Fresco.initialize(this)
    }

    private fun initToasty() {
        com.aceli.bilibililuckdraw.widget.toasty.Toasty.Config.getInstance()
            .setTextSize(14)
            .allowQueue(false) // 是否排队显示
            .setGravity(Gravity.CENTER)
            .supportDarkTheme(false)
            .apply()
    }

}