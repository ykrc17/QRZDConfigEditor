package com.ykrc17.game.qrzdconfigeditor.config

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File

abstract class SDCardFileReader(private val filePath: String) {
    protected fun readFile(consumer: (String) -> Unit) {
        val file = File(Environment.getExternalStorageDirectory(), filePath)
        if (file.exists()) {
            val text = file.readText()
            consumer(text)
        } else {
            Log.e(javaClass.simpleName, "file not found")
        }
    }
}
