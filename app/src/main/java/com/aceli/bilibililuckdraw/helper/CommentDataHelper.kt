package com.aceli.bilibililuckdraw.helper

import com.aceli.bilibililuckdraw.bean.VideoCommentBean


object CommentDataHelper {
    var commentsData: ArrayList<VideoCommentBean>? = null
    var repeatData: ArrayList<VideoCommentBean>? = null
    var nameMap: HashMap<String, String> = HashMap()
    fun setData(dataList: ArrayList<VideoCommentBean>) {
        if (commentsData == null) {
            commentsData = ArrayList()
        }
        repeatData?.clear()
        commentsData?.clear()
        nameMap.clear()
        dataList.forEach {
            if (nameMap.containsKey(it.member?.uname) && nameMap[it.member?.uname] != it.oid.toString()) {
                if (repeatData == null) {
                    repeatData = ArrayList()
                }
                repeatData?.add(it)
            } else {
                commentsData?.add(it)
                if (it.member?.is_followed == 1) {
                    commentsData?.add(it)
                }
                it.member?.uname?.let { name ->
                    nameMap.put(name, it.oid.toString())
                }
            }
        }
    }
}