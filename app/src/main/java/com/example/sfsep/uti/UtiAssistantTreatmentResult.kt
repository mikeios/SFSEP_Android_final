package com.example.sfsep.uti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sfsep.R
import com.example.sfsep.generalModel.ResourcesManager
import com.example.sfsep.uti.model.UtiTreatmentHelper
import kotlinx.android.synthetic.main.activity_uti_assistant_treatment_result.*

class UtiAssistantTreatmentResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uti_assistant_treatment_result)
        supportActionBar!!.title = getString(R.string.vaccin_assistant)
        ResourcesManager.context = this
        updateUI()
    }

    fun updateUI() {
        val probabilistTreatment = UtiTreatmentHelper.getProbabilistTreatment()
        if (probabilistTreatment != null && !probabilistTreatment.isEmpty()) {
            probabilistField.text = convertArrayToString(probabilistTreatment)
        } else {
            probabilistField.text = getString(R.string.aucun)
        }

        val adaptedTreatment = UtiTreatmentHelper.getRelayTreatment()
        adaptedField.text = convertArrayToString(adaptedTreatment)

        val duration = UtiTreatmentHelper.getTreatmentDuration()
        durationField.text = convertArrayToString(duration)
    }


    fun convertArrayToString(fromArray: Array<String>):String {
        var concatenateString = ""
        for (string in fromArray) {
            concatenateString += string + "\n\n"
        }
        concatenateString = concatenateString.removeSuffix("\n\n")
        return concatenateString
    }

    fun nextButtonTouched(sender: View) {
        val intent = Intent(this, UtiMainActivity::class.java)
        startActivity(intent)
    }
}