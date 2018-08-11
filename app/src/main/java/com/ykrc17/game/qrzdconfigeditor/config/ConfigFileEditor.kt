package com.ykrc17.game.qrzdconfigeditor.config

class ConfigFileEditor(filePath: String) : SDCardFileEditor(filePath) {
    fun readConfig(consumer: (QRZDConfig) -> Unit) {
        readFile { string: String ->
            val config = QRZDConfig(string)
            consumer(config)
        }
    }

    fun writeConfig(config: QRZDConfig) {
        writeFile(config.json.toString())
    }
}
