package com.aceli.bilibililuckdraw.helper

import com.aceli.bilibililuckdraw.LuckDrawApplication
import com.aceli.bilibililuckdraw.database.AceRoomDatabase
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity

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

}