package com.ykrc17.game.qrzdconfigeditor.thread

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.ykrc17.util.function.Consumer

abstract class AsyncCall<Params, Result> : Consumer<Params> {
    private val handler = Handler(Looper.getMainLooper())
    private val callback: Consumer<Result>

    constructor(callback: Consumer<Result>) {
        this.callback = callback
    }

    override fun accept(params: Params) {
        execute(params)
    }

    fun execute(params: Params) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute {
            val result = doInBackground(params)
            if (callback is MainThreadConsumer) {
                handler.post { callback.accept(result) }
            } else {
                callback.accept(result)
            }
        }
    }

    abstract fun doInBackground(params: Params): Result
}
