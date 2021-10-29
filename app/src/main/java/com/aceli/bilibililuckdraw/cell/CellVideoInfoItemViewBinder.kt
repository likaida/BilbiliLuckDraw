package com.aceli.bilibililuckdraw.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.widget.multitype.ItemViewBinder
import com.aceli.bilibililuckdraw.widget.multitype.OnItemMultiClickListener
import com.facebook.drawee.view.SimpleDraweeView

class CellVideoInfoItemViewBinder(private var onItemMultiClickListener: OnItemMultiClickListener) :
    ItemViewBinder<VideoInfoEntity, CellVideoInfoItemViewBinder.ViewHolder>() {

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
//            holder.mPlayTimesIcon.setImageDrawable(
//                tintDrawable(
//                    holder.mPlayTimesIcon.drawable, ColorStateList.valueOf(
//                        Color.rgb(255, 255, 255)
//                    )
//                )
//            )
        }
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mVideoCover: SimpleDraweeView = itemView.findViewById(R.id.mVideoCover)
        var mPlayTimesIcon: ImageView = itemView.findViewById(R.id.mPlayTimesIcon)
        var mVideoName: TextView = itemView.findViewById(R.id.mVideoName)
        var mPlayTimes: TextView = itemView.findViewById(R.id.mPlayTimes)
        var mLikeNum: TextView = itemView.findViewById(R.id.mLikeNum)
        var mCoinNum: TextView = itemView.findViewById(R.id.mCoinNum)
        var mFavoriteNum: TextView = itemView.findViewById(R.id.mFavoriteNum)

    }
}