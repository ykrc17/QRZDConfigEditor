package com.ykrc17.game.qrzdconfigeditor

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ykrc17.game.qrzdconfigeditor.adapter.CGEntity
import com.ykrc17.game.qrzdconfigeditor.adapter.CGListAdapter
import com.ykrc17.game.qrzdconfigeditor.adapter.OnClickItemListener
import com.ykrc17.game.qrzdconfigeditor.thread.AsyncParseJsonCall
import com.ykrc17.game.qrzdconfigeditor.thread.AsyncReadJsonCall
import com.ykrc17.game.qrzdconfigeditor.thread.MainThreadConsumer

class CGListActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CGListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "CG列表"

        recyclerView = RecyclerView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CGListAdapter(this, object : OnClickItemListener<CGEntity> {
            override fun onClick(item: CGEntity) {
                val bundle = Bundle()
                bundle.putString("id", item.id)
                finish(bundle)
            }
        })
        recyclerView.adapter = adapter
        setContentView(recyclerView)

        readJson()
    }

    private fun readJson() {
        AsyncReadJsonCall(AsyncParseJsonCall(object : MainThreadConsumer<List<CGEntity>> {
            override fun accept(t: List<CGEntity>) {
                showCGList(t)
            }
        })).execute(assets.open("endings.json"))
    }

    private fun showCGList(entity: List<CGEntity>) {
        adapter.setAll(entity)
        recyclerView.scrollToPosition(adapter.setSelectedItem(intent.getStringExtra("id")))
    }
}
