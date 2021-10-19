package com.aceli.bilibililuckdraw.bean

data class CommentsBean(
    var commentList: ArrayList<CommentBean>? = null
)

data class CommentBean(
    var rpid: Long? = 0L,
    var oid: Long? = 0L,
    //评论时间戳
    var ctime: Long? = 0L,
    var rpid_str: String? = null,
    //点赞数
    var like: Long? = 0L,
    var member: CommentMember? = null,
    var content: CommentContent? = null
)

data class CommentMember(
    var mid: String? = null,
    var uname: String? = null,
    var sex: String? = null,
    var avatar: String? = null,
    var rank: String? = null,
    var level_info: UserLevel? = null,
)

data class UserLevel(
    var current_level: Int? = 0
)

data class CommentContent(
    var message: String? = null,
    var up_action: UpAction? = null
)

data class UpAction(
    var like: Boolean? = false,
    var reply: Boolean? = false
)


