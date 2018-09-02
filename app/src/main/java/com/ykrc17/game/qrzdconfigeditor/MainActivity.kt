package com.ykrc17.game.qrzdconfigeditor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.ykrc17.game.qrzdconfigeditor.config.QRZDConfig
import com.ykrc17.game.qrzdconfigeditor.layout.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var presenter: MainPresenter
    lateinit var bindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindings = ActivityMainBinding(window.decorView)

        presenter = MainPresenter(this)
        requestPermissions(presenter::readConfig)
        findViewById<View>(R.id.btn_cg_list).setOnClickListener {
            startActivity(Intent(this, CGListActivity::class.java))
        }
    }

    private var endingListener = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!s.isNullOrEmpty()) {
                presenter.writeConfig { it.ending = s.toString().toInt() }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
    }

    override fun showConfig(config: QRZDConfig) {
        bindings.apply {
            cb_frame_rate.setChecked(config.isHighFrame)
            cb_frame_rate.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
                presenter.writeConfig { it.isHighFrame = isChecked }
            }
            et_ending.removeTextChangedListener(endingListener)
            et_ending.setText(config.ending.toString())
            et_ending.addTextChangedListener(endingListener)

            if (BuildConfig.DEBUG) {
                tv_debug.text = config.originString
            }
        }
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
            Toast.makeText(this, "你竟敢拒绝艾露比大人", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
