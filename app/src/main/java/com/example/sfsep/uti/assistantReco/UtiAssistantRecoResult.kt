package com.example.sfsep.uti.assistantReco

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.sfsep.R
import com.example.sfsep.generalModel.ResourcesManager
import com.example.sfsep.uti.UtiMainActivity
import com.example.sfsep.uti.model.UtiRecoManager
import kotlinx.android.synthetic.main.activity_uti_assistant_reco_result.*

class UtiAssistantRecoResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uti_assistant_reco_result)
        supportActionBar!!.title = getString(R.string.vaccin_assistant)
        ResourcesManager.context = this
        updateResults()
    }


    fun updateResults() {
        // Sujet à risque ?
        val atRisk = UtiRecoManager.atRiskSituation()
        if (atRisk) {
            atRiskSubjectField.text = getString(R.string.oui)
        } else {
            atRiskSubjectField.text = getString(R.string.non)
        }
        updateTextColor(atRiskSubjectField, R.string.non, null)

        // Traitement majore le risque
        treatmentRaisesRiskField.text = UtiRecoManager.treatmentRaisesRisk().value
        updateTextColor(treatmentRaisesRiskField, R.string.non, R.string.possiblement)
        if (UtiRecoManager.treatmentRaisesRisk() != UtiRecoManager.dmtRaisesRisk.possibly) {
            resultLayout.removeView(ocrewarning)
        }

        // IU récurrentes
        recurrentUtiField.text = UtiRecoManager.reco_recurrentUtiText
        updateTextColor(recurrentUtiField, R.string.non, null)

        // Screening
        screeningField.text = UtiRecoManager.reco_screenColonizationText
        updateTextColor(screeningField, R.string.non_screening, null)

        //Screening before BUD
        budScreeningField.text = UtiRecoManager.reco_screenBeforeBudText
        updateTextColor(budScreeningField, R.string.non, null)

        // Preventive treatment
        preventiveTreatmentField.text = UtiRecoManager.reco_preventiveTreatmentText
        updateTextColor(preventiveTreatmentField, R.string.non, null)

        //Additionnal
        if (UtiRecoManager.additionnalReco() != null) {
            additionalField.text = UtiRecoManager.additionnalReco()
        } else {
            resultLayout.removeView(noteLayout)
        }
    }

    fun okTouched(sender:View) {
        val intent = Intent(this, UtiMainActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("ResourceAsColor")
    fun updateTextColor(view: TextView, greenAnswer:Int, orangeAnswer:Int?) {
        if (orangeAnswer != null && view.text == getString(orangeAnswer)) {
            view.setTextColor(getColor(R.color.orange))
        }
        else if (view.text == getString(greenAnswer)) {
            view.setTextColor(getColor(R.color.green))
        } else {
            view.setTextColor(getColor(R.color.red))
        }
    }
}