package com.example.sfsep.vaccins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.diaporamaphotos.enableAll
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.ResourcesManager
import com.example.sfsep.vaccins.model.VaccinAssistant
import com.example.sfsep.vaccins.model.VaccinAssistantExpert
import kotlinx.android.synthetic.main.activity_vaccin_assistant_expert.*
import kotlinx.android.synthetic.main.activity_vaccin_assistant_two.*

class VaccinAssistantExpert : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccin_assistant_expert)

        supportActionBar!!.title = getString(R.string.vaccin_assistant)
        ResourcesManager.context = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun vhbSelectionChanged(sender: View) {
        if (vaccinVHBGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_VHB3) {
            acHbSGroup.check(R.id.ui_vaccin_expert_AcHbS_seropositif)
            acHbCGroup.check(R.id.ui_vaccin_expert_AcHbC_seronegatif)
            agHbSGroup.check(R.id.ui_vaccin_expert_AgHbS_seronegatif)
        } else {
            acHbSGroup.check(R.id.ui_vaccin_expert_AcHbS_seronegatif)
            acHbCGroup.check(R.id.ui_vaccin_expert_AcHbC_seronegatif)
            agHbSGroup.check(R.id.ui_vaccin_expert_AgHbS_seronegatif)
        }
    }

    fun vhaSelectionChanged(sender:View) {
        if (vaccinVHAGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vaccin_VHA_oui) {
            seroVHAGroup.check(R.id.ui_vaccin_expert_vha_seropositif)
            seroVHAGroup.enableAll(false)
        } else {
            seroVHAGroup.check(R.id.ui_vaccin_expert_vha_seronegatif)
            seroVHAGroup.enableAll(true)
        }

    }
    fun vzvSelectionChanged(sender:View) {
        if (vaccinVZVGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vaccin_VZV_oui) {
            seroVZVGroup.check(R.id.ui_vaccin_expert_vzv_seropositif)
            seroVZVGroup.enableAll(false)
        } else {
            seroVZVGroup.check(R.id.ui_vaccin_expert_vzv_seronegatif)
            seroVZVGroup.enableAll(true)
        }
    }
    fun expert_nextButtonTouched(sender:View) {
        // La date de DTP est elle valide?
        val dtpDate = lastDTPTextField.text.toString().replace(" ", "")
        val dtpIsValid = VaccinAssistantExpert.checkDateValidity(dtpDate)

        if (!dtpIsValid && dtpDate != "0") {
            // Alerte donnée invalide
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.alert_invalidData_title)
            alertDialog.setMessage(R.string.alert_dtp_message)
            alertDialog.setNeutralButton(R.string.OK, null)
            ddnTextField.text.clear()
            alertDialog.show()
        } else if (dtpDate != "0"){
            VaccinAssistantExpert.dernierDTP = dtpDate
        }

        VaccinAssistantExpert.seroVZV = (seroVZVGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vzv_seropositif)
        VaccinAssistantExpert.seroVHA = (seroVHAGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vha_seropositif)
        VaccinAssistantExpert.acHBC = (acHbCGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_AcHbC_seropositif)
        VaccinAssistantExpert.acHBS = (acHbSGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_AcHbS_seropositif)
        VaccinAssistantExpert.agHBS = (agHbSGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_AgHbS_seropositif)

        when (vaccinVHBGroup.checkedRadioButtonId) {
            R.id.ui_vaccin_expert_VHB0 -> VaccinAssistantExpert.nbVHB = 0
            R.id.ui_vaccin_expert_VHBless3 -> VaccinAssistantExpert.nbVHB = 1
            else -> VaccinAssistantExpert.nbVHB = 3
        }

        when (rorGroup.checkedRadioButtonId) {
            R.id.ui_vaccin_expert_ROR0 -> VaccinAssistantExpert.nbROR = 0
            R.id.ui_vaccin_expert_ROR1 -> VaccinAssistantExpert.nbROR = 1
            R.id.ui_vaccin_expert_ROR2 -> VaccinAssistantExpert.nbROR = 2
        }

        VaccinAssistantExpert.vzvFait = (vaccinVZVGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vaccin_VZV_oui)
        VaccinAssistantExpert.vhaFait = (vaccinVHAGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vaccin_VHA_oui)
        VaccinAssistantExpert.grippeFait = (vaccinGrippeGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vaccinGrippe_Oui)
        VaccinAssistantExpert.pneumoFait = (vaccinPneumoGroup.checkedRadioButtonId == R.id.ui_vaccin_expert_vaccinPneumo_oui)

        VaccinAssistantExpert.affinateVaccins()
        VaccinAssistant.generateDelais()

        val intent = Intent(this, VaccinAssistantResult::class.java)
        startActivity(intent)
    }
}