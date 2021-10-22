package com.aceli.bilibililuckdraw.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.database.entity.WordEntity
import com.aceli.bilibililuckdraw.widget.multitype.ItemViewBinder

class CellWordItemViewBinder :
    ItemViewBinder<WordEntity, CellWordItemViewBinder.ViewHolder>() {

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp: ViewGroup.LayoutParams = holder.itemView.layoutParams;
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            val p: StaggeredGridLayoutManager.LayoutParams = lp
            p.isFullSpan = true
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.cell_word, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: WordEntity) {
        item.let { data ->
            holder.bind(data.word)

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mWord: TextView = itemView.findViewById(R.id.mWord)

        fun bind(text: String?) {
            mWord.text = text
        }
    }
}