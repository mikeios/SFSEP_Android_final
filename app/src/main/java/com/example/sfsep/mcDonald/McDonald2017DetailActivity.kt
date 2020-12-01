package com.example.sfsep.mcDonald

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager

class McDonald2017DetailActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mc_donald2017_detail)
        supportActionBar!!.title = getString(R.string.McDonald)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}