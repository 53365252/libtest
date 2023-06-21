package com.allen.weather.ui.common.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<in I : BaseListItem>(
    itemView: View,
    private val onItemClickListener: ((view: View, item: I) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private lateinit var item: I

    init {
        onItemClickListener?.let {
            itemView.setOnClickListener(this)
        }
    }

    open fun bind(item: I) {
        this.item = item
    }

    override fun onClick(view: View) {
        onItemClickListener?.invoke(view, item)
    }
}

/**
 * for some simple header or footer
 */
class BaseViewHolderImpl<T : BaseListItem>(
    itemView: View,
    onItemClick: ((view: View, item: T) -> Unit)? = null
) : BaseViewHolder<T>(itemView, onItemClick)