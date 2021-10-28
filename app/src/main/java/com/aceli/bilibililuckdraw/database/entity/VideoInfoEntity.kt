package com.aceli.bilibililuckdraw.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aceli.bilibililuckdraw.bean.*
import com.aceli.bilibililuckdraw.database.convert.VideoInfoConvert

/**
 * @author likaida
 * @Description Video Info Table
 * @date 2021-10-28
 */
@Entity(tableName = "video_info_table")
@TypeConverters(VideoInfoConvert::class)
data class VideoInfoEntity(
    @PrimaryKey
    var bvid: String,
    var aid: Int? = null,
    var videos: Int? = null,
    var tid: Int? = null,
    var tname: String? = null,
    var copyright: Int? = null,
    var pic: String? = null,
    var title: String? = null,
    var pubdate: Int? = null,
    var ctime: Int? = null,
    var desc: String? = null,
    var state: Int? = null,
    var duration: Int? = null,
    var missionId: Int? = null,
    var dynamic: String? = null,
    var cid: Int? = null,
    var noCache: Boolean? = null,
    var descV2: List<DescV2DTO>? = null,
    var rights: RightsDTO? = null,
    var owner: OwnerDTO? = null,
    var stat: StatDTO? = null,
    var dimension: DimensionDTO? = null,
    var pages: List<PagesDTO>? = null,
    var subtitle: SubtitleDTO? = null,
    var userGarb: UserGarbDTO? = null,
)
