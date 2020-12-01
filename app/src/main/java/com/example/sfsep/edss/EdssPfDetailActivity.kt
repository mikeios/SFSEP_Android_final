package com.example.sfsep.edss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sfsep.R
import com.example.sfsep.edss.adapters.EdssPfTableAdapter
import kotlinx.android.synthetic.main.activity_edss_pf_detail.*

class EdssPfDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edss_pf_detail)
        val title = intent.getStringExtra("title")
        if (title != null) {
            supportActionBar!!.title = title
        }

        val pfIndex = intent.getIntExtra("pfIndex", 0)


        val pfArray = intent.getStringArrayExtra("pfArray")
        if (pfArray != null) {
            val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            pfDetailTable.addItemDecoration(itemDecor)

            pfDetailTable.adapter = EdssPfTableAdapter(pfArray, this, pfIndex)
            pfDetailTable.layoutManager = LinearLayoutManager(this)
            pfDetailTable.setHasFixedSize(false)
        }


    }

}