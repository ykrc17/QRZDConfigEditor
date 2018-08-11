package com.ykrc17.game.qrzdconfigeditor.config

import org.json.JSONObject
import kotlin.math.absoluteValue

class QRZDConfig(val originString: String) {
    val json: JSONObject = JSONObject(originString)
    var frameRate: Int
        get() = json.optInt("frame_rate")
        set(value) {
            json.put("frame_rate", value)
        }
    var isHighFrame: Boolean
        get() = frameRate == 60
        set(value) {
            frameRate = if (value) 60 else 30
        }
}