package com.aceli.bilibililuckdraw.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.bean.VideoCommentBean
import com.aceli.bilibililuckdraw.util.Utils
import com.aceli.bilibililuckdraw.widget.multitype.ItemViewBinder
import com.facebook.drawee.view.SimpleDraweeView

class CellCommentFromNetItemViewBinder :
    ItemViewBinder<VideoCommentBean, CellCommentFromNetItemViewBinder.ViewHolder>() {

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp: ViewGroup.LayoutParams = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            val p: StaggeredGridLayoutManager.LayoutParams = lp
            p.isFullSpan = true
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.cell_comment, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: VideoCommentBean) {
        item.let { data ->
            data.member?.uname?.let {
                holder.mUserName.text = it
            }
            data.member?.sex?.let {

            }
            data.content?.message?.let {
                holder.mContent.text = it
            }
            data.ctime?.let {
                holder.mCreateTime.text = Utils.convertTimeStampForCreateTime(it * 1000)
            }
            holder.mUserIcon.setImageURI(data.member?.avatar)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mUserName: TextView = itemView.findViewById(R.id.mUserName)
        var mContent: TextView = itemView.findViewById(R.id.mContent)
        var mCreateTime: TextView = itemView.findViewById(R.id.mCreateTime)
        var mUserIcon: SimpleDraweeView = itemView.findViewById(R.id.mUserIcon)
    }
}