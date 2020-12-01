package com.example.sfsep

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.PDF.PDFManager
import com.example.sfsep.generalModel.WebsiteManager

class AboutActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)
    val websiteManager = WebsiteManager(this)
    val pdfManager = PDFManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar!!.title = getString(R.string.about)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun websiteButtonTouched(button: View) {
        val url = getString(R.string.sfsep_url)
        websiteManager.goToWebsite(url)
    }

    fun contactButtonTouched(button: View) {
        val url = getString(R.string.contact_url)
        websiteManager.goToWebsite(url)
    }

    fun cguButtonTouched(button: View) {
        pdfManager.showPDFFile(R.string.cguFile)
    }
}