package com.ykrc17.game.qrzdconfigeditor.adapter

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import com.ykrc17.game.qrzdconfigeditor.layout.CgListItemBinding
import kotlin.math.roundToInt

class CGViewModel : ViewModel<CGEntity> {
    private val binding: CgListItemBinding

    constructor(itemView: View, listener: OnClickItemListener<CGEntity>) : super(itemView, listener) {
        binding = CgListItemBinding(itemView)
    }

    override fun onBind(item: CGEntity) {
        binding.apply {
            tv_id.text = item.id
            tv_name.text = item.name

            tv_id.paint.isFakeBoldText = item.isEnding
            tv_name.paint.isFakeBoldText = item.isEnding
        }

        (itemView.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = if (item.isLast) {
            (Resources.getSystem().displayMetrics.density * 20).roundToInt()
        } else 0
    }
}
