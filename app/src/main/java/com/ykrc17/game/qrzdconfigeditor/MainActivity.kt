package com.ykrc17.game.qrzdconfigeditor

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import com.ykrc17.game.qrzdconfigeditor.config.QRZDConfig
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var presenter: MainPresenter
    lateinit var frameRate: CheckBox
    lateinit var debugText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindView()

        presenter = MainPresenter(this)
        requestPermissions(presenter::readConfig)
    }

    private fun bindView() {
        frameRate = findViewById(R.id.cb_frame_rate)
        debugText = findViewById(R.id.tv_debug)
    }

    override fun showConfig(config: QRZDConfig) {
        frameRate.isChecked = config.isHighFrame
        frameRate.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            presenter.writeConfig {
                it.isHighFrame = isChecked
            }
        }

        debugText.text = config.originString
    }

    private fun requestPermissions(success: () -> Unit) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), Random().nextInt(0xFFFF))
            } else {
                success()
            }
        } else {
            success()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            presenter.readConfig()
        } else {
            Toast.makeText(this, "读写权限被拒绝", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
