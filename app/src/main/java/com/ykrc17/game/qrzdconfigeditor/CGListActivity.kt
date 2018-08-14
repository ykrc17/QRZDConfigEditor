package com.ykrc17.game.qrzdconfigeditor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CGListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "CG列表"
        setContentView(R.layout.activity_cg_list)
        findViewById<TextView>(R.id.tv_cg_list).text = assets.open("endings.md").bufferedReader().readText()
    }
}
