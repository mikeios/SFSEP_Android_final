package com.example.sfsep.vaccins

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.vaccins.model.VaccinAssistant
import kotlinx.android.synthetic.main.activity_vaccin_assistant_two.*
import kotlinx.android.synthetic.main.activity_vaccin_summary.*

class VaccinAssistantTwo : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccin_assistant_two)
        supportActionBar!!.title = getString(R.string.vaccin_assistant)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun hideKeyboard(sender: View) {

    }

    fun nextButtonTouched(sender: View) {
        VaccinAssistant.resetVaccins()

        // La DDN est elle valide?
        val ddnIsValid = VaccinAssistant.isValidDate(ddnTextField.text.toString())

        if (!ddnIsValid) {
            // Alerte donnée invalide
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.alert_invalidData_title)
            alertDialog.setMessage(R.string.alert_ddn_message)
            alertDialog.setNeutralButton(R.string.OK, null)
            ddnTextField.text.clear()
            alertDialog.show()
        } else {
            // On renseigne l'assistant de vaccination
            VaccinAssistant.dateDeNaissanceString = ddnTextField.text.toString()
            VaccinAssistant.actualIS = (dmtGroup.checkedRadioButtonId == R.id.ui_vaccin_isButton)
            VaccinAssistant.projetIS = (projetISGroup.checkedRadioButtonId == R.id.ui_vaccin_projetISoui)
            VaccinAssistant.voyageur = (voyageGroup.checkedRadioButtonId == R.id.ui_vaccin_voyage_oui)
            VaccinAssistant.corticoides = (ctcGroup.checkedRadioButtonId == R.id.ui_vaccin_recentCTC_oui)
            VaccinAssistant.highEDSS = (highEDSSGroup.checkedRadioButtonId == R.id.ui_vaccin_highEDSS_oui)
            VaccinAssistant.vzvRecent = (recentVZVGroup.checkedRadioButtonId == R.id.ui_vaccin_recentVZV_oui)
            VaccinAssistant.vvaRecent = (recentVVAGroup.checkedRadioButtonId == R.id.ui_vaccin_recentVVA_oui)
            VaccinAssistant.viRecent = (recentVIGroup.checkedRadioButtonId == R.id.ui_vaccin_recentVI_oui)
            VaccinAssistant.cocooning = (cocooningGroup.checkedRadioButtonId == R.id.ui_vaccin_cocooning_oui)

            VaccinAssistant.generateArrays()
            VaccinAssistant.generateDelais()

            if (VaccinAssistant.expertMode) {
                val intent = Intent(this, VaccinAssistantExpert::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, VaccinAssistantResult::class.java)
                startActivity(intent)
            }

        }
    }
}