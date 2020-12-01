package com.example.sfsep.vaccins

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.sfsep.R
import com.example.sfsep.generalModel.DisclaimerManager
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.ResourcesManager
//
class VaccinAssistantOne : AppCompatActivity() {
    val menuManager = MenuManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccin_assistant_one)
        supportActionBar!!.title = getString(R.string.vaccin_assistant)
        ResourcesManager.context = this

        val disclaimerManager = DisclaimerManager(this, "vaccins")
        disclaimerManager.showDisclaimerIfNeeded()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun nextButtonTouched(sender: View) {
        val id = sender.id
        com.example.sfsep.vaccins.model.VaccinAssistant.expertMode = (id == R.id.vaccinAssistantYesButton)
        val intent = Intent(this, VaccinAssistantTwo::class.java)
        startActivity(intent)
    }
}