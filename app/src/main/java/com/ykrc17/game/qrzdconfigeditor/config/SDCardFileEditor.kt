package com.ykrc17.game.qrzdconfigeditor.config

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File

abstract class SDCardFileEditor(private val filePath: String) {
    val file = File(Environment.getExternalStorageDirectory(), filePath)

    protected fun readFile(consumer: (String) -> Unit) {
        if (file.exists()) {
            val text = file.readText()
            consumer(text)
        } else {
            Log.e(javaClass.simpleName, "file not found")
        }
    }

    protected fun writeFile(text: String) {
        file.writeText(text)
    }
}
