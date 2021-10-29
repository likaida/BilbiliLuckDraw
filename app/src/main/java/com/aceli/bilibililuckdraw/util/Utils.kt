package com.aceli.bilibililuckdraw.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.DrawableCompat
import com.aceli.bilibililuckdraw.LuckDrawApplication
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    fun getStatusBarHeight(context: Context): Int {
        var statusBarHeight = 0
        //获取status_bar_height资源的ID
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    /**
     * 得到json文件中的内容
     * @param context
     * @param fileName
     * @return
     */
    fun getJson(context: Context, fileName: String?): String {
        val stringBuilder = StringBuilder()
        //获得assets资源管理器
        val assetManager = context.assets
        //使用IO流读取json文件内容
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileName!!), "utf-8"
                )
            )
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            bufferedReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    private val YEAR_FORMAT: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val TIME_FORMAT: DateFormat = SimpleDateFormat("HH:mm")
    val DAY_FORMAT: DateFormat = SimpleDateFormat("MM-dd")

    private const val SECOND: Long = 1000
    private const val MINUTE: Long = SECOND * 60
    private const val HOUR: Long = MINUTE * 60
    private const val DAY: Long = HOUR * 24

    fun convertTimeStampForCreateTime(time: Long): String? {
        try {
            val date0 = Date(time)
            val dateCurrent = Date() //取时间
            val currentTime = dateCurrent.time
            val calendar: Calendar = GregorianCalendar()
            calendar.time = dateCurrent
            calendar[Calendar.HOUR_OF_DAY] = 0
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MINUTE] = 0
            calendar[Calendar.MILLISECOND] = 0
            val thisDay0 = calendar.time
            calendar.time = dateCurrent
            calendar.add(Calendar.HOUR_OF_DAY, -1)
            val oneHourAgo = calendar.time
            return if (currentTime < time) {
                YEAR_FORMAT.format(date0)
            } else if (date0.after(thisDay0) && date0.after(oneHourAgo)) {
                if ((currentTime - time) / MINUTE == 0L) {
                    "刚刚"
                } else {
                    ((currentTime - time) / MINUTE).toString() + "分钟前"
                }
            } else if (date0.after(thisDay0) && date0.before(oneHourAgo)) {
                "今日" + TIME_FORMAT.format(date0)
            } else {
                calendar.time = dateCurrent
                calendar.add(Calendar.DAY_OF_YEAR, -1)
                calendar[Calendar.HOUR_OF_DAY] = 0
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MINUTE] = 0
                calendar[Calendar.MILLISECOND] = 0
                val yesterday = calendar.time
                calendar.time = dateCurrent
                calendar.add(Calendar.DAY_OF_YEAR, -6) //WEEK_OF_YEAR -1是不符合常规说法的，会出现7天前，而7天前算一个周之前
                calendar[Calendar.HOUR_OF_DAY] = 0
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MINUTE] = 0
                calendar[Calendar.MILLISECOND] = 0
                val oneWeekAgo = calendar.time
                if (date0.after(yesterday)) { //昨天
                    "昨日" + TIME_FORMAT.format(dateCurrent)
                } else if (date0.before(yesterday) && date0.after(oneWeekAgo)) { //昨天之前，一周以内
                    ((currentTime - time) / DAY).toString() + "天前"
                } else { //一周之前
                    YEAR_FORMAT.format(date0)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun getColor(id: Int): Int {
        return LuckDrawApplication.mApp.resources.getColor(id)
    }

    private fun tintDrawable(drawable: Drawable, colors: ColorStateList): Drawable {
        val wrappedDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTintList(wrappedDrawable, colors)
        return wrappedDrawable
    }
}