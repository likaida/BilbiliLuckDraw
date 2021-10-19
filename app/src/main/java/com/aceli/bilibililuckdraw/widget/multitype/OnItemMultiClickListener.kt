package com.aceli.bilibililuckdraw.widget.multitype

/**
 * @author likaida
 * @Description 通用Item行为
 * @date 2020-8-21
 */
interface OnItemMultiClickListener {
    /**
     * 通用点击事件
     * @param actionType 点击类型
     * @param pos        pos
     * @param ext        ext
     */
    fun onBaseItemMultiClick(actionType: Int, pos: Int = 0, ext: Any? = null)
}