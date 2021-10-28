package com.aceli.bilibililuckdraw.bean

data class VideoInfoBean(
     var bvid: String? = null,
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
     var descV2: List<DescV2DTO>? = null,
     var state: Int? = null,
     var duration: Int? = null,
     var missionId: Int? = null,
     var rights: RightsDTO? = null,
     var owner: OwnerDTO? = null,
     var stat: StatDTO? = null,
     var dynamic: String? = null,
     var cid: Int? = null,
     var dimension: DimensionDTO? = null,
     var noCache: Boolean? = null,
     var pages: List<PagesDTO>? = null,
     var subtitle: SubtitleDTO? = null,
     var userGarb: UserGarbDTO? = null,
)

data class RightsDTO(
     var bp: Int? = null,
     var elec: Int? = null,
     var download: Int? = null,
     var movie: Int? = null,
     var pay: Int? = null,
     var hd5: Int? = null,
     var noReprint: Int? = null,
     var autoplay: Int? = null,
     var ugcPay: Int? = null,
     var isCooperation: Int? = null,
     var ugcPayPreview: Int? = null,
     var noBackground: Int? = null,
     var cleanMode: Int? = null,
     var isSteinGate: Int? = null,
     var is360: Int? = null,
)

data class OwnerDTO(
     var mid: Int? = null,
     var name: String? = null,
     var face: String? = null
)

data class StatDTO(
     var aid: Int? = null,
     var view: Int? = null,
     var danmaku: Int? = null,
     var reply: Int? = null,
     var favorite: Int? = null,
     var coin: Int? = null,
     var share: Int? = null,
     var nowRank: Int? = null,
     var hisRank: Int? = null,
     var like: Int? = null,
     var dislike: Int? = null,
     var evaruation: String? = null,
     var argueMsg: String? = null
)

data class SubtitleDTO(
     var allowSubmit: Boolean? = null,
     var list: List<*>? = null
)

data class UserGarbDTO(
     var urlImageAniCut: String? = null
)

data class DescV2DTO(
     var rawText: String? = null,
     var type: Int? = null,
     var bizId: Int? = null
)

data class PagesDTO(
     var cid: Int? = null,
     var page: Int? = null,
     var from: String? = null,
     var part: String? = null,
     var duration: Int? = null,
     var vid: String? = null,
     var weblink: String? = null,
     var dimension: DimensionDTO? = null,
     var firstFrame: String? = null
)

data class DimensionDTO(
     var width: Int? = null,
     var height: Int? = null,
     var rotate: Int? = null
)
