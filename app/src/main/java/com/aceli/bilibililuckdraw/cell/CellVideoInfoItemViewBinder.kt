package com.aceli.bilibililuckdraw.cell

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.const.ClickConst
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.util.Utils
import com.aceli.bilibililuckdraw.widget.multitype.ItemViewBinder
import com.aceli.bilibililuckdraw.widget.multitype.OnItemMultiClickListener
import com.facebook.drawee.view.SimpleDraweeView

class CellVideoInfoItemViewBinder(private var onItemMultiClickListener: OnItemMultiClickListener? = null) :
    ItemViewBinder<VideoInfoEntity, CellVideoInfoItemViewBinder.ViewHolder>() {
    private var colorsWhite = ColorStateList.valueOf(Color.rgb(255, 255, 255))
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.cell_video_info, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: VideoInfoEntity) {
        item.let { data ->
            holder.mVideoCover.setImageURI(data.pic)
            holder.mVideoName.text = data.title
            holder.mPlayTimes.text = data.stat?.view.toString()
            holder.mLikeNum.text = data.stat?.like.toString()
            holder.mCoinNum.text = data.stat?.coin.toString()
            holder.mFavoriteNum.text = data.stat?.favorite.toString()
            holder.mPlayTimesIcon.setImageDrawable(
                Utils.tintDrawable(
                    holder.mPlayTimesIcon.drawable, colorsWhite
                )
            )
            holder.mLikeIcon.setImageDrawable(
                Utils.tintDrawable(
                    holder.mLikeIcon.drawable, colorsWhite
                )
            )
            holder.mCoinIcon.setImageDrawable(
                Utils.tintDrawable(
                    holder.mCoinIcon.drawable, colorsWhite
                )
            )
            holder.mFavoriteIcon.setImageDrawable(
                Utils.tintDrawable(
                    holder.mFavoriteIcon.drawable, colorsWhite
                )
            )
            val updateRes = "Update:" + Utils.convertTimeStampForCreateTime(data.updateTime ?: 0)
            holder.mUpdateTime.text = updateRes
            val replyRes = "Reply:" + data.stat?.reply?.toString()
            holder.mReplyNum.text = replyRes
            holder.mVideoDelete.setOnClickListener {
                onItemMultiClickListener?.onBaseItemMultiClick(
                    ClickConst.CLICK_ACTION_VIDEO_DELETE,
                    holder.adapterPosition,
                    data.bvid
                )
            }
            holder.mVideoRefresh.setOnClickListener {
                onItemMultiClickListener?.onBaseItemMultiClick(
                    ClickConst.CLICK_ACTION_VIDEO_REFRESH,
                    holder.adapterPosition,
                    data.bvid
                )
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mVideoCover: SimpleDraweeView = itemView.findViewById(R.id.mVideoCover)
        var mPlayTimesIcon: ImageView = itemView.findViewById(R.id.mPlayTimesIcon)
        var mLikeIcon: ImageView = itemView.findViewById(R.id.mLikeIcon)
        var mCoinIcon: ImageView = itemView.findViewById(R.id.mCoinIcon)
        var mFavoriteIcon: ImageView = itemView.findViewById(R.id.mFavoriteIcon)
        var mVideoName: TextView = itemView.findViewById(R.id.mVideoName)
        var mPlayTimes: TextView = itemView.findViewById(R.id.mPlayTimes)
        var mLikeNum: TextView = itemView.findViewById(R.id.mLikeNum)
        var mCoinNum: TextView = itemView.findViewById(R.id.mCoinNum)
        var mFavoriteNum: TextView = itemView.findViewById(R.id.mFavoriteNum)
        var mUpdateTime: TextView = itemView.findViewById(R.id.mUpdateTime)
        var mReplyNum: TextView = itemView.findViewById(R.id.mReplyNum)
        var mVideoDelete: ImageView = itemView.findViewById(R.id.mVideoDelete)
        var mVideoRefresh: ImageView = itemView.findViewById(R.id.mVideoRefresh)
    }
}