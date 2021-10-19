package com.aceli.bilibililuckdraw.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment

fun View.showOrGone(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.showOrHide(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isGone() = visibility == View.GONE
fun View.isVisible() = visibility == View.VISIBLE


// 实现
inline fun <reified T : Activity> Activity.startActivity(initializer: Intent.() -> Unit) {
    startActivity(
        Intent(this, T::class.java).apply(initializer)
    )
}

// 实现
inline fun <reified T : Activity> Fragment.startActivity(initializer: Intent.() -> Unit) {
    startActivity(
        Intent(this.context, T::class.java).apply(initializer)
    )
}

fun Boolean.toInt() = if (this) 1 else 0

fun Boolean.toStringValue() = if (this) "1" else "0"

fun Long.safeToInt(): Int = if (this > Int.MAX_VALUE) Int.MAX_VALUE else this.toInt()

/**
 * 替换文本中的省略号为英文...
 */
fun TextView.replaceEllipsisEnd() {
    doOnLayout {
        val layout2 = layout
        layout2?.let {
            val lines = layout2.lineCount
            //                    Logger.d("删减前${metaView.textView.text}")
            //                    Logger.d("当前行数是:" + layout2.lineCount)
            //                    Logger.d("被省略的字符数量是:" + layout2.getEllipsisCount(lines - 1))//看看最后一行被省略掉了多少
            //                    Logger.d("被省略的字符起始位置是:" + layout2.getEllipsisStart(lines - 1))//看看最后一行被省略的起始位置
            //                    Logger.d("最后一个可见字符的偏移是:" + layout2.getLineVisibleEnd(lines - 1))
            //开始替换系统省略号
            if (lines < 1 || layout2.getEllipsisCount(lines - 1) == 0) return@let//如果被省略的字符数量为0，就不管了
            var showText = text.toString()
            showText = showText.substring(0, layout2.getEllipsisStart(lines - 1)) + "..."
            //                    Logger.d("删减后$showText")
            text = showText
        }
    }
}
