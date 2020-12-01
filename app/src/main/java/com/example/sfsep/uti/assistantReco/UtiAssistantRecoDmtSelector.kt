package com.example.sfsep.uti.assistantReco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sfsep.R
import com.example.sfsep.edss.adapters.EdssPfTableAdapter
import kotlinx.android.synthetic.main.activity_edss_pf_detail.*
import kotlinx.android.synthetic.main.activity_uti_assistant_reco_dmt_selector.*

class UtiAssistantRecoDmtSelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uti_assistant_reco_dmt_selector)
        supportActionBar!!.title = getString(R.string.uti_dmt)
        val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        dmtRecyclerView.addItemDecoration(itemDecor)

        dmtRecyclerView.adapter = dmtAdapter(this)
        dmtRecyclerView.layoutManager = LinearLayoutManager(this)
        dmtRecyclerView.setHasFixedSize(false)
    }
}