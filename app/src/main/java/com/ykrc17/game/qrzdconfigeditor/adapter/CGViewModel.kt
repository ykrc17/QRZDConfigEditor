package com.ykrc17.game.qrzdconfigeditor.adapter

import android.view.View
import com.ykrc17.game.qrzdconfigeditor.layout.CgListItemBinding

class CGViewModel : ViewModel<CGEntity> {
    private val binding: CgListItemBinding

    constructor(itemView: View, listener: OnClickItemListener<CGEntity>) : super(itemView, listener) {
        binding = CgListItemBinding(itemView)
    }

    override fun onBind(item: CGEntity) {
        binding.apply {
            tv_id.text = item.id
            tv_name.text = item.name
        }
    }
}
