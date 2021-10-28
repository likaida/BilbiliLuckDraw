package com.aceli.bilibililuckdraw

import android.app.Application
import android.view.Gravity
import com.aceli.bilibililuckdraw.database.AceRepository
import com.aceli.bilibililuckdraw.database.AceRoomDatabase
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber


class LuckDrawApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process //
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AceRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { AceRepository(database.wordDao()) }

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
        initPython()
    }

    // 初始化python
    private fun initPython() {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
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