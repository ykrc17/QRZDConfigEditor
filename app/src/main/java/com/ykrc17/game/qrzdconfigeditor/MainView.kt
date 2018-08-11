package com.ykrc17.game.qrzdconfigeditor

import com.ykrc17.game.qrzdconfigeditor.config.QRZDConfig

interface MainView {
    fun showConfig(config: QRZDConfig)
}