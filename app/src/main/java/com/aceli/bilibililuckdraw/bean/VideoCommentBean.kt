package com.aceli.bilibililuckdraw.bean

data class VideoCommentBean(
    var rpid: Long? = null,
    var oid: Int? = null,
    var type: Int? = null,
    var mid: Int? = null,
    var root: Int? = null,
    var parent: Int? = null,
    var dialog: Int? = null,
    var count: Int? = null,
    var rcount: Int? = null,
    var state: Int? = null,
    var fansgrade: Int? = null,
    var attr: Int? = null,
    var ctime: Long? = null,
    var rpid_str: String? = null,
    var root_str: String? = null,
    var parent_str: String? = null,
    var like: Int? = null,
    var action: Int? = null,
    var member: MemberDTO? = null,
    var content: ContentDTO? = null,
    var replies: List<*>? = null,
    var assist: Int? = null,
    var folder: FolderDTO? = null,
    var up_action: UpActionDTO? = null,
    var show_follow: Boolean? = null,
    var invisible: Boolean? = null,
    var reply_control: ReplyControlDTO? = null
)

class MemberDTO {
    var mid: String? = null
    var uname: String? = null
    var sex: String? = null
    var sign: String? = null
    var avatar: String? = null
    var rank: String? = null
    var display_rank: String? = null
    var level_Info: LevelInfoDTO? = null
    var pendant: PendantDTO? = null
    var nameplate: NameplateDTO? = null
    var official_verify: OfficialVerifyDTO? = null
    var vip: VipDTO? = null
    var fans_detail: Any? = null
    var following: Int? = null
    var is_followed: Int? = null
    var user_sailing: UserSailingDTO? = null
    var is_contractor: Boolean? = null
    var contract_desc: String? = null
}

class ContentDTO {
    var message: String? = null
    var plat: Int? = null
    var devices: String? = null
    var max_line: Int? = null //members[] jump_url{}
}

class FolderDTO {
    var has_folded: Boolean? = null
    var is_folded: Boolean? = null
    var rule: String? = null
}

class UpActionDTO {
    var like: Boolean? = null
    var reply: Boolean? = null
}

class ReplyControlDTO {
    var time_desc: String? = null
}

class LevelInfoDTO {
    var current_level: Int? = null
    var current_min: Int? = null
    var current_exp: Int? = null
    var next_exp: Int? = null
}

class NameplateDTO {
    var nid: Int? = null
    var name: String? = null
    var image: String? = null
    var image_small: String? = null
    var level: String? = null
    var condition: String? = null
}

class OfficialVerifyDTO {
    var type: Int? = null
    var desc: String? = null
}

class VipDTO {
    var vip_type: Int? = null
    var vip_due_date: Long? = null
    var due_remark: String? = null
    var access_status: Int? = null
    var vip_status: Int? = null
    var vipstatus_warn: String? = null
    var theme_type: Int? = null
    var label: LabelDTO? = null
    var avatar_subscript: Int? = null
    var avatar_subscript_url: String? = null
    var nickname_color: String? = null
}

class LabelDTO {
    var path: String? = null
    var text: String? = null
    var label_theme: String? = null
    var text_color: String? = null
    var bg_style: Int? = null
    var bg_color: String? = null
    var border_color: String? = null
}

class UserSailingDTO {
    var pendant: PendantDTO? = null
    var cardbg: CardbgDTO? = null
    var cardbg_with_focus: Any? = null
}

class PendantDTO {
    var id: Int? = null
    var name: String? = null
    var image: String? = null
    var jump_url: String? = null
    var type: String? = null
    var image_enhance: String? = null
    var image_enhance_frame: String? = null
}

class CardbgDTO {
    var id: Int? = null
    var name: String? = null
    var image: String? = null
    var jump_url: String? = null
    var fan: FanDTO? = null
    var type: String? = null
}

class FanDTO {
    var isFan: Int? = null
    var number: Int? = null
    var color: String? = null
    var name: String? = null
    var numDesc: String? = null
}
