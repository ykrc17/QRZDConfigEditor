package com.ykrc17.game.qrzdconfigeditor

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.ykrc17.game.qrzdconfigeditor.config.ConfigFileReader
import java.util.*

class MainActivity : AppCompatActivity() {

    val requestCode = Random().nextInt(0xFFFF)
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text_view)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), requestCode)
        } else {
            readConfig()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            readConfig()
        }
    }

    fun readConfig() {
        ConfigFileReader("/Android/data/com.netease.qrzd/files/netease/qrzd/Documents/config_info").readConfig() {
            textView.text = it.originString
        }
    }
}
