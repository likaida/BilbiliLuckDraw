package com.aceli.bilibililuckdraw

import android.app.Application
import android.view.Gravity
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

class LuckDrawApplication : Application() {
    companion object {
        lateinit var mApp: LuckDrawApplication

        fun getApplicationObject(): LuckDrawApplication {
            return mApp
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        AppContext.isDebug = BuildConfig.DEBUG
        AppContext.init(this)
        init()
    }

    private fun init() {
        initToasty()
        Fresco.initialize(this)
        Timber.plant(Timber.DebugTree())
    }

    private fun initToasty() {
        Toasty.Config.getInstance()
            .setTextSize(14)
            .allowQueue(false) // 是否排队显示
            .setGravity(Gravity.CENTER)
            .supportDarkTheme(false)
            .apply()
    }

}