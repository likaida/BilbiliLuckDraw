package com.aceli.bilibililuckdraw.database.dao

import androidx.room.*
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoInfoDao {

    @Query("SELECT * FROM video_info_table WHERE bvid LIKE :vid ORDER BY ctime DESC")
    fun getVideoById(vid: String): VideoInfoEntity

    @Insert
    fun insertVideoInfo(word: VideoInfoEntity)

    @Update
    fun updateVideoInfo(vararg book: VideoInfoEntity)

    @Query("SELECT * FROM video_info_table")
    fun getAllVideoInfo(): List<VideoInfoEntity>

    @Query("DELETE FROM video_info_table WHERE bvid LIKE :vid")
    fun deleteVideoById(vid: String)

    @Query("DELETE FROM video_info_table")
    fun deleteAll()
}