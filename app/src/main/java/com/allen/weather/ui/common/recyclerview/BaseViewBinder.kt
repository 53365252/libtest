package com.allen.weather.ui.common.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import me.drakeet.multitype.ItemViewBinder

abstract class BaseViewBinder<I : BaseListItem, H : BaseViewHolder<I>> : ItemViewBinder<I, H>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): H = createViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: H, item: I) = holder.bind(item)

    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): H
}
