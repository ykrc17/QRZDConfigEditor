package com.ykrc17.game.qrzdconfigeditor

import com.ykrc17.game.qrzdconfigeditor.config.ConfigFileEditor
import com.ykrc17.game.qrzdconfigeditor.config.QRZDConfig

class MainPresenter(private val view: MainView) {
    private val editor = ConfigFileEditor("/Android/data/com.netease.qrzd/files/netease/qrzd/Documents/config_info")
    private lateinit var config: QRZDConfig

    fun readConfig() {
        editor.readConfig {
            config = it
            view.showConfig(it)
        }
    }

    fun writeConfig(updateConfig: (QRZDConfig) -> Unit) {
        updateConfig(config)
        editor.writeConfig(config)
    }
}