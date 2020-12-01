package com.example.sfsep.uti.assistantReco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.diaporamaphotos.enableAll
import com.example.sfsep.R
import com.example.sfsep.generalModel.DisclaimerManager
import com.example.sfsep.uti.model.UtiRecoManager
import kotlinx.android.synthetic.main.activity_uti_assistant_reco_form.*

class UtiAssistantRecoForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uti_assistant_reco_form)
        supportActionBar!!.title = getString(R.string.vaccin_assistant)
        dmtLabel.text = UtiRecoManager.currentTreatment

        val disclaimerManager = DisclaimerManager(this, "uti_recos")
        disclaimerManager.showDisclaimerIfNeeded()
    }

    override fun onRestart() {
        super.onRestart()
        dmtLabel.text = UtiRecoManager.currentTreatment
    }
    fun dmtTouched(sender: View) {
        val intent = Intent(this, UtiAssistantRecoDmtSelector::class.java)
        startActivity(intent)

    }


    fun ageChanged(sender: View) {
        if (ageGroup.checkedRadioButtonId == R.id.ageButton65_75) {
            frailtyGroup.enableAll(true)
        } else {
            frailtyGroup.check(R.id.frailtyButtonNo)
            frailtyGroup.enableAll(false)
        }
    }

    fun nextButtonTouched(sender: View) {
        when (ageGroup.checkedRadioButtonId) {
            R.id.ageButtonLess65 -> UtiRecoManager.ageTier = UtiRecoManager.AgeTier.less65
            R.id.ageButton65_75 -> UtiRecoManager.ageTier = UtiRecoManager.AgeTier.between65And75
            R.id.ageButtonHigher75 -> UtiRecoManager.ageTier = UtiRecoManager.AgeTier.over75
        }

        UtiRecoManager.isMale = !(genreGroup.checkedRadioButtonId == R.id.femaleButton)
        UtiRecoManager.frailty = frailtyGroup.checkedRadioButtonId == R.id.frailtyButtonYes
        UtiRecoManager.tractusAbnormality = anomalieTractusGroup.checkedRadioButtonId == R.id.tractusButtonYes
        UtiRecoManager.reflux = refluxGroup.checkedRadioButtonId == R.id.refluxButtonYes
        UtiRecoManager.highPression = highPressionGroup.checkedRadioButtonId == R.id.highPressionButtonYes
        UtiRecoManager.corticosteroids = ctcGroup.checkedRadioButtonId == R.id.ctcButtonYes
        UtiRecoManager.bolus = bolusGroup.checkedRadioButtonId == R.id.bolusButtonYes
        UtiRecoManager.fampridine = fampridineGroup.checkedRadioButtonId == R.id.fampridineButtonYes
        UtiRecoManager.plasmapheresis = plexGroup.checkedRadioButtonId == R.id.plexButtonYes
        UtiRecoManager.hypogamma = hypogammaGroup.checkedRadioButtonId == R.id.hypogammaButtonYes
        UtiRecoManager.neutropenia = neutropenieGroup.checkedRadioButtonId == R.id.neutropenieButtonYes
        UtiRecoManager.badRenalFunction = badRenalFunctionGroup.checkedRadioButtonId == R.id.badRenalFunctionButtonYes
        UtiRecoManager.recurrentUTI = recidivantGroup.checkedRadioButtonId == R.id.recidivantButtonYes

        val intent = Intent(this, UtiAssistantRecoResult::class.java)
        startActivity(intent)



    }
}