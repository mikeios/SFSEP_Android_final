package com.example.sfsep.vaccins

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.FAQ.FaqActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.WebsiteManager

class VaccinMainActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)
    val websiteManager = WebsiteManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccin_main)
        supportActionBar!!.title = getString(R.string.vaccins)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun assistantButtonTouched(sender: View) {
        val intent = Intent(this, VaccinAssistantOne::class.java)
        startActivity(intent)
    }

    fun texteIntegralButtonTouched(sender: View) {
        val recosURL = getString(R.string.vaccins_sfsep_url)
        websiteManager.goToWebsite(recosURL)
    }
    fun summaryButtonTouched(sender: View) {
        val intent = Intent(this, VaccinSummaryActivity::class.java)
        startActivity(intent)
    }
    fun faqButtonTouched(sender: View) {
        val intent = Intent(this, FaqActivity::class.java)
        intent.putExtra(getString(R.string.faqIdForIntent), getString(R.string.faq_key_vaccination))
        startActivity(intent)
    }
    fun calendrierButtonTouched(sender: View) {
        val calendarURL = getString(R.string.calendrier_vaccinal_url)
        websiteManager.goToWebsite(calendarURL)

    }
}