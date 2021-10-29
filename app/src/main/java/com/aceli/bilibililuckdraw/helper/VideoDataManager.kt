package com.aceli.bilibililuckdraw.helper

import com.aceli.bilibililuckdraw.LuckDrawApplication
import com.aceli.bilibililuckdraw.bean.JsonBean
import com.aceli.bilibililuckdraw.database.AceRoomDatabase
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.lang.Exception

object VideoDataManager {
    fun addVideo(videoInfo: VideoInfoEntity) {
        LuckDrawApplication.mApp
        val videoDatabase = AceRoomDatabase.getDataBase()?.videoInfo()
        if (videoDatabase?.getVideoById(videoInfo.bvid) != null) {
            videoDatabase.updateVideoInfo(videoInfo)
        } else {
            videoDatabase?.insertVideoInfo(videoInfo)
        }
    }

    fun getAllVideo(): List<VideoInfoEntity>? {
        return AceRoomDatabase.getDataBase()?.videoInfo()?.getAllVideoInfo()
    }

    fun addVideoById(vid: String, callback: OnAddVideoCallback? = null) {
        val py: Python = Python.getInstance()
        py.getModule("GetVideoInfo").callAttr("init", vid)
        val pyObjectVideoInfo: PyObject = py.getModule("GetVideoInfo").callAttr("getJson")
        val info: JsonBean = pyObjectVideoInfo.toJava(
            JsonBean::class.java
        )
        val aid = info.jsonData
        var infoBean: VideoInfoEntity? = null
        try {
            infoBean = GsonHelper.instance.gson.fromJson(
                info.jsonData,
                object : TypeToken<VideoInfoEntity>() {}.type
            ) as VideoInfoEntity
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (infoBean != null) {
            addVideo(infoBean)
            callback?.onAddVideoSuccess(infoBean)
            Toasty.success(LuckDrawApplication.mApp, "Add Video ${infoBean.title} Success").show()
        } else {
            callback?.onAddVideoFail()
            Toasty.error(LuckDrawApplication.mApp, "Add Video $vid error").show()
        }
        Timber.d("python_likaida:aid->$aid")
    }

    interface OnAddVideoCallback {
        fun onAddVideoSuccess(videoInfo: VideoInfoEntity)
        fun onAddVideoFail()
    }
}