package com.ykrc17.game.qrzdconfigeditor

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.ykrc17.game.qrzdconfigeditor.config.QRZDConfig
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var presenter: MainPresenter
    lateinit var frameRate: CheckBox
    lateinit var ending: EditText

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
        ending = findViewById(R.id.et_ending)
        debugText = findViewById(R.id.tv_debug)
    }

    var endingListener = object : TextWatcher {
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
        frameRate.setChecked(config.isHighFrame)
        frameRate.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            presenter.writeConfig { it.isHighFrame = isChecked }
        }
        ending.removeTextChangedListener(endingListener)
        ending.setText(config.ending.toString())
        ending.addTextChangedListener(endingListener)

        if (BuildConfig.DEBUG) {
            debugText.text = config.originString
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
