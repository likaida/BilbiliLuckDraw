package com.aceli.bilibililuckdraw.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.const.ClickConst
import com.aceli.bilibililuckdraw.widget.multitype.ItemViewBinder
import com.aceli.bilibililuckdraw.widget.multitype.OnItemMultiClickListener

class CellBarrageItemViewBinder(var listener: OnItemMultiClickListener?) :
    ItemViewBinder<String, CellBarrageItemViewBinder.ViewHolder>() {

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp: ViewGroup.LayoutParams = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            val p: StaggeredGridLayoutManager.LayoutParams = lp
            p.isFullSpan = true
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.cell_barrage, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: String) {
        item.let { data ->
            holder.mContent.text = data
            holder.itemView.setOnLongClickListener {
                listener?.onBaseItemMultiClick(
                    ClickConst.CLICK_ACTION_BARRAGE_NUM,
                    holder.adapterPosition,
                    item
                )
                false
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mContent: TextView = itemView.findViewById(R.id.mContent)
    }
}