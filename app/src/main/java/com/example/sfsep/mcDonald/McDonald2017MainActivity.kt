package com.example.sfsep.mcDonald

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.WebsiteManager

class McDonald2017MainActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)
    val websiteManager = WebsiteManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mc_donald2017)

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

    fun seeCriteriaTouched(button: View) {
        val intent = Intent(this, McDonald2017DetailActivity::class.java)
        startActivity(intent)

    }

    fun seeSlideShow(button: View) {
        val slideShowURL = getString(R.string.mcdonald_url)
        websiteManager.goToWebsite(slideShowURL)
    }


}