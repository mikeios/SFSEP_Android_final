package com.example.sfsep.vaccins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.ResourcesManager
import com.example.sfsep.vaccins.model.VaccinAssistant
import com.example.sfsep.vaccins.model.VaccinResultAdapter
import kotlinx.android.synthetic.main.activity_edss_pf_detail.*
import kotlinx.android.synthetic.main.activity_vaccin_assistant_result.*

class VaccinAssistantResult : AppCompatActivity() {

    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccin_assistant_result)
        supportActionBar!!.title = getString(R.string.Resultats)
        ResourcesManager.context = this

        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun backPressed(sender:View) {
        val intent = Intent(this, VaccinMainActivity::class.java)
        startActivity(intent)
    }

    fun updateUI() {
        // On enlève les points importants si il on est pas sur un switch d'IS
        val switchIS = (VaccinAssistant.projetIS && VaccinAssistant.actualIS)
        if (!switchIS) {
            resultTable.removeView(importantPoints)
        }
        // Vaccins recommandés
        if (VaccinAssistant.vaccinsRecommandés.isEmpty()) {
            resultTable.removeView(recommendedVaccines)
        } else {
            val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            recommendedRecyclerView.addItemDecoration(itemDecor)
            recommendedRecyclerView.adapter = VaccinResultAdapter("reco")
            recommendedRecyclerView.layoutManager = LinearLayoutManager(this)
            recommendedRecyclerView.setHasFixedSize(false)
        }
        // Vaccins interdits
        if (VaccinAssistant.vaccinsInterdits.isEmpty()) {
            resultTable.removeView(forbiddenVaccines)
        } else {
            val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            forbiddenRecyclerView.addItemDecoration(itemDecor)
            forbiddenRecyclerView.adapter = VaccinResultAdapter("interdit")
            forbiddenRecyclerView.layoutManager = LinearLayoutManager(this)
            forbiddenRecyclerView.setHasFixedSize(false)
        }
        // Infos
        quandVaccinerText.text = VaccinAssistant.quandVacciner
        quandTraiterText.text = VaccinAssistant.delaiVaccinTraitement
        coadText.text = VaccinAssistant.coAdministration
    }
}