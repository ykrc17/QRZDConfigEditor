package com.ykrc17.game.qrzdconfigeditor.config

import android.content.Context

class ConfigFileReader(filePath: String) : SDCardFileReader(filePath) {
    fun readConfig(consumer: (QRZDConfig) -> Unit) {
        readFile() { string: String ->
            val config = QRZDConfig("", string)
            consumer(config)
        }
    }
}
