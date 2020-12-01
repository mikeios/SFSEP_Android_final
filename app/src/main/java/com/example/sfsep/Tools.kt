package com.example.sfsep

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.edss.EdssMainTableActivity
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.WebsiteManager
import com.example.sfsep.mcDonald.McDonald2017MainActivity
import kotlinx.android.synthetic.main.activity_tools.*

class Tools : AppCompatActivity(), AdapterView.OnItemClickListener {
    val menuManager = MenuManager(this)
    val websiteManager = WebsiteManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)
        supportActionBar!!.title = getString(R.string.outils)
        toolsTable.onItemClickListener = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p2) {
            0 -> showEDSSActivity()
            1 -> showMcDonaldActivity()
            2 -> goToWebsite()
        }
    }

    private fun goToWebsite() {
        val sfsepURLString = getString(R.string.sfsep_url)
        websiteManager.goToWebsite(sfsepURLString)
    }

    private fun showMcDonaldActivity() {
        val intent = Intent(this, McDonald2017MainActivity::class.java)
        startActivity(intent)
    }


    fun showEDSSActivity() {
        val intent = Intent(this, EdssMainTableActivity::class.java)
        startActivity(intent)
    }


}