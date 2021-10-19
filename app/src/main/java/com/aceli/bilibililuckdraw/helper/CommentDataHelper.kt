package com.aceli.bilibililuckdraw.helper

import com.aceli.bilibililuckdraw.bean.CommentBean
import com.aceli.bilibililuckdraw.bean.CommentsBean


object CommentDataHelper {
    var commentsData: CommentsBean? = null
    var repeatData: ArrayList<CommentBean>? = null
    var nameMap: HashMap<String, String> = HashMap()
    fun setData(dataList: ArrayList<CommentBean>) {
        if (commentsData == null) {
            commentsData = CommentsBean()
        }
        if (commentsData?.commentList == null) {
            commentsData?.commentList = ArrayList()
        }
        commentsData?.commentList?.clear()
        nameMap.clear()
        dataList.forEach {
            if (nameMap.containsKey(it.member?.uname)) {
                if (repeatData == null) {
                    repeatData = ArrayList()
                }
                repeatData?.add(it)
            } else {
                commentsData?.commentList?.add(it)
                it.member?.uname?.let { name ->
                    nameMap.put(name, "")
                }
            }
        }
    }

}