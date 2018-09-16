package com.ykrc17.game.qrzdconfigeditor.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ViewModel<E> : RecyclerView.ViewHolder {
    private val onClickItemListener: OnClickItemListener<E>

    constructor(itemView: View, onClickItemListener: OnClickItemListener<E>) : super(itemView) {
        this.onClickItemListener = onClickItemListener
    }

    fun bind(item: E) {
        onBind(item)
        itemView.setOnClickListener { onClickItemListener.onClick(item) }
    }

    protected abstract fun onBind(item: E)
}
