package com.aceli.bilibililuckdraw.bean

data class RightsDTO(
     var bp: Int? = null,
     var elec: Int? = null,
     var download: Int? = null,
     var movie: Int? = null,
     var pay: Int? = null,
     var hd5: Int? = null,
     var noReprint: Int? = null,
     var autoplay: Int? = null,
     var ugc_pay: Int? = null,
     var is_cooperation: Int? = null,
     var ugc_pay_preview: Int? = null,
     var no_background: Int? = null,
     var clean_mode: Int? = null,
     var isstein_gate: Int? = null,
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
     var now_rank: Int? = null,
     var his_rank: Int? = null,
     var like: Int? = null,
     var dislike: Int? = null,
     var evaruation: String? = null,
     var argue_msg: String? = null
)

data class SubtitleDTO(
     var allow_submit: Boolean,
     var list: List<*>? = null
)

data class UserGarbDTO(
     var url_image_ani_cut: String? = null
)

data class DescV2DTO(
     var raw_text: String? = null,
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
     var first_frame: String? = null
)

data class DimensionDTO(
     var width: Int? = null,
     var height: Int? = null,
     var rotate: Int? = null
)
