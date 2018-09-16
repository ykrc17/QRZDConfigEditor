package com.ykrc17.game.qrzdconfigeditor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray

abstract class BaseActivity : AppCompatActivity() {
    private val callbacks: SparseArray<(Bundle?) -> Unit> = SparseArray()

    fun startActivityForResult(activityClass: Class<out Activity>, args: Bundle?, callback: (Bundle?) -> Unit) {
        val intent = Intent(this, activityClass)
        args?.also {
            intent.putExtras(it)
        }
        // Can only use lower 16 bits for requestCode
        val requestCode = activityClass.name.hashCode() and 0xFFFF
        callbacks.put(requestCode, callback)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            callbacks.get(requestCode)?.also {
                it(data?.extras)
                return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    protected fun finish(extras: Bundle? = null) {
        val intent = Intent()
        intent.putExtras(extras)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
