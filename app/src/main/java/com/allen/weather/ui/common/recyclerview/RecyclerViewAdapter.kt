package com.allen.weather.ui.common.recyclerview

import androidx.recyclerview.widget.DiffUtil
import me.drakeet.multitype.MultiTypeAdapter
import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor() : MultiTypeAdapter() {

    @Suppress("UNCHECKED_CAST")
    fun setAdapterItems(items: List<BaseListItem>) {

        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(
                DiffUtilCallback(this.items.toList() as List<BaseListItem>, items)
            )
        diffResult.dispatchUpdatesTo(this)

        super.setItems(items)
    }

    fun setViewPagerAdapterItems(items: List<BaseListItem>) {
        super.setItems(items)
        notifyDataSetChanged()
    }
}

class DiffUtilCallback(
    private val currentData: List<BaseListItem>,
    private val newData: List<BaseListItem>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        currentData[oldItemPosition].id == newData[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
       currentData[oldItemPosition] == newData[newItemPosition]

    override fun getOldListSize(): Int = currentData.size

    override fun getNewListSize(): Int = newData.size
}
