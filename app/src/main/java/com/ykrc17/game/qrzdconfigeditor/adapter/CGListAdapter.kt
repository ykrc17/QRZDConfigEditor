package com.ykrc17.game.qrzdconfigeditor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ykrc17.game.qrzdconfigeditor.R

class CGListAdapter : ListAdapter<CGViewModel, CGEntity> {
    private val listener: OnClickItemListener<CGEntity>

    constructor(context: Context, listener: OnClickItemListener<CGEntity>) : super(context) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CGViewModel {
        val view = LayoutInflater.from(context).inflate(R.layout.cg_list_item, parent, false)
        return CGViewModel(view, listener)
    }

    override fun onBindViewHolder(holder: CGViewModel, item: CGEntity, position: Int) {
        item.isLast = position == itemCount - 1
        holder.bind(item)
    }

    fun setSelectedItem(id: String?): Int {
        if (id == null) {
            return -1
        }
        list.forEachIndexed { index, entity ->
            if (entity.id == id) {
                entity.name += "√"
                notifyItemChanged(index)
                return index
            }
        }
        return -1
    }

}
