package com.ykrc17.game.qrzdconfigeditor.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView

abstract class ListAdapter<VH : ViewModel<E>, E> : RecyclerView.Adapter<VH> {
    protected val list = arrayListOf<E>()
    protected val context: Context

    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getItemCount() = list.size

    fun setAll(elements: List<E>) {
        list.clear()
        list.addAll(elements)
        notifyDataSetChanged()
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, list[position], position)
    }

    open fun onBindViewHolder(holder: VH, item: E, position: Int) {
        holder.bind(item)
    }
}
