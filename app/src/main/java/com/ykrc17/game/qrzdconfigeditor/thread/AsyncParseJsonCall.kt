package com.ykrc17.game.qrzdconfigeditor.thread

import com.ykrc17.game.qrzdconfigeditor.adapter.CGEntity
import com.ykrc17.util.function.Consumer
import org.json.JSONObject

class AsyncParseJsonCall : AsyncCall<JSONObject, List<CGEntity>> {
    constructor(callback: Consumer<List<CGEntity>>) : super(callback)

    override fun doInBackground(params: JSONObject): List<CGEntity> {
        val result = arrayListOf<CGEntity>()
        params.keys().forEach {
            result.add(CGEntity(it, params.optString(it)))
        }
        return result
    }
}
