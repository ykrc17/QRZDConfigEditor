package com.ykrc17.game.qrzdconfigeditor.thread

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.ykrc17.util.function.Consumer

abstract class AsyncCall<Param, Result> : Consumer<Param> {
    private val handler = Handler(Looper.getMainLooper())
    private val callback: Consumer<Result>

    constructor(callback: Consumer<Result>) {
        this.callback = callback
    }

    override fun accept(param: Param) {
        execute(param)
    }

    fun execute(params: Param) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute {
            val result = doInBackground(params)
            if (callback is MainThreadConsumer) {
                handler.post { callback.accept(result) }
            } else {
                callback.accept(result)
            }
        }
    }

    abstract fun doInBackground(params: Param): Result
}
