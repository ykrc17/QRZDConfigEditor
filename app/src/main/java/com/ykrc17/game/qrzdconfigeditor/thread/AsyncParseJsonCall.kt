package com.ykrc17.game.qrzdconfigeditor.thread

import com.ykrc17.game.qrzdconfigeditor.adapter.CGEntity
import com.ykrc17.util.function.Consumer
import org.json.JSONObject

class AsyncParseJsonCall : AsyncCall<JSONObject, List<CGEntity>> {
    constructor(callback: Consumer<List<CGEntity>>) : super(callback)

    override fun doInBackground(params: JSONObject): List<CGEntity> {
        val endingList = arrayListOf<CGEntity>()
        val result = arrayListOf<CGEntity>()
        params.optJSONObject("ending")?.apply {
            keys().forEach { cgKey ->
                val entity = CGEntity(cgKey, optString(cgKey), true)
                endingList.add(entity)
                result.add(entity)
            }
        }
        params.getJSONObject("cg")?.apply {
            keys().forEach { cgKey ->
                val cgName = optString(cgKey)
                result.add(CGEntity(cgKey, cgName, endingList.any { it.name == cgName }))
            }
        }
        return result
    }
}
