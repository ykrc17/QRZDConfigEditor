package com.ykrc17.game.qrzdconfigeditor.thread

import com.ykrc17.util.function.Consumer
import org.json.JSONObject
import java.io.InputStream

class AsyncReadJsonCall : AsyncCall<InputStream, JSONObject> {
    constructor(callback: Consumer<JSONObject>) : super(callback)


    override fun doInBackground(params: InputStream): JSONObject {
        return JSONObject(params.bufferedReader().readText())
    }
}
