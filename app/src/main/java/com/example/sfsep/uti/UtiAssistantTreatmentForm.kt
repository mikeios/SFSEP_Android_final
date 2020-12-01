package com.example.sfsep.uti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.diaporamaphotos.enableAll
import com.example.sfsep.R
import com.example.sfsep.generalModel.DisclaimerManager
import com.example.sfsep.generalModel.PDF.PDFManager
import com.example.sfsep.uti.model.UtiTreatmentHelper
import kotlinx.android.synthetic.main.activity_uti_assistant_treatment_form.*

class UtiAssistantTreatmentForm : AppCompatActivity() {

    val pdfManager = PDFManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uti_assistant_treatment_form)

        supportActionBar!!.title = getString(R.string.vaccin_assistant)

        val disclaimerManager = DisclaimerManager(this, "uti_treatment")
        disclaimerManager.showDisclaimerIfNeeded()



    }

    fun reviewDefinitionsTouched(sender: View) {
        pdfManager.showPDFFile(R.string.file_uti_definitions_short)
    }



    fun genreChanged(sender: View) {
        if (genreGroup.checkedRadioButtonId == R.id.maleButton) {
            pregnancyGroup.check(R.id.pregnancyButtonNo)
            pregnancyGroup.enableAll(false)

            infectionGroup.clearCheck()
            infectionGroup.enableAll(false)

            chocGroup.enableAll(true)
            graviteGroup.enableAll(true)
            chocGroup.check(R.id.chocButtonNo)
            graviteGroup.check(R.id.graviteButtonNo)
        } else {
            pregnancyGroup.enableAll(true)
            pregnancyGroup.check(R.id.pregnancyButtonNo)

            infectionGroup.check(R.id.cystite)
            infectionGroup.enableAll(true)
            colo.isEnabled = false

            chocGroup.enableAll(false)
            graviteGroup.enableAll(false)
            chocGroup.check(R.id.chocButtonNo)
            graviteGroup.check(R.id.graviteButtonNo)

        }
    }

    fun pregnancyChanged(sender: View) {
        colo.isEnabled = pregnancyGroup.checkedRadioButtonId == R.id.pregnancyButtonYes
    }

    fun infectionTypeChanged(sender: View) {
        when (infectionGroup.checkedRadioButtonId) {
            R.id.cystite -> configureForCystitis()
            R.id.pyelo -> configureForPyelo()
            R.id.colo -> removeBoth()
        }
    }

    fun chocChanged(sender: View) {
        if (chocGroup.checkedRadioButtonId == R.id.chocButtonYes) {
            graviteGroup.check(R.id.graviteButtonYes)
        }
    }

    fun graviteChanged(sender: View) {
        if (graviteGroup.checkedRadioButtonId == R.id.graviteButtonNo) {
            chocGroup.check(R.id.chocButtonNo)
        }
    }

    fun pauciSpChanged(sender: View) {
        if (pauciSpGroup.checkedRadioButtonId == R.id.pauciSpButtonYes) {
            canBeDelayedGroup.check(R.id.canBeDelayedButtonYes)
        } else {
            canBeDelayedGroup.check(R.id.canBeDelayedButtonNo)
        }
    }

    fun canBeDelayedChanged(sender: View) {
        if (canBeDelayedGroup.checkedRadioButtonId == R.id.canBeDelayedButtonYes) {
            pauciSpGroup.check(R.id.pauciSpButtonYes)
        } else {
            pauciSpGroup.check(R.id.pauciSpButtonNo)
        }
    }

    fun setChocAndGravity(enabled:Boolean) {
        chocGroup.enableAll(enabled)
        graviteGroup.enableAll(enabled)
        chocGroup.check(R.id.chocButtonNo)
        graviteGroup.check(R.id.graviteButtonNo)
    }

    fun setPauciAndDelayed(enabled:Boolean) {
        pauciSpGroup.enableAll(enabled)
        canBeDelayedGroup.enableAll(enabled)
        pauciSpGroup.check(R.id.pauciSpButtonNo)
        canBeDelayedGroup.check(R.id.canBeDelayedButtonNo)
    }

    fun configureForCystitis() {
        setChocAndGravity(false)
        setPauciAndDelayed(true)
    }

    fun configureForPyelo() {
        setChocAndGravity(true)
        setPauciAndDelayed(false)
    }

    fun removeBoth() {
        setChocAndGravity(false)
        setPauciAndDelayed(false)
    }

    fun nextButtonTouched(sender: View) {
        //Mise à jour du modèle
        // Genre et infection type
        if (genreGroup.checkedRadioButtonId == R.id.femaleButton) {
            UtiTreatmentHelper.genre = UtiTreatmentHelper.Genre.female
            when (infectionGroup.checkedRadioButtonId) {
                R.id.cystite -> UtiTreatmentHelper.infection = UtiTreatmentHelper.InfectionType.cystite
                R.id.pyelo -> UtiTreatmentHelper.infection = UtiTreatmentHelper.InfectionType.pna
                R.id.colo -> UtiTreatmentHelper.infection = UtiTreatmentHelper.InfectionType.colonisation
            }
        } else {
            UtiTreatmentHelper.genre = UtiTreatmentHelper.Genre.male
            UtiTreatmentHelper.infection = UtiTreatmentHelper.InfectionType.masculine
        }

        // Le reste
        UtiTreatmentHelper.pregnancy = (pregnancyGroup.checkedRadioButtonId == R.id.pregnancyButtonYes)
        UtiTreatmentHelper.chocSeptique = (chocGroup.checkedRadioButtonId == R.id.chocButtonYes)
        UtiTreatmentHelper.signesDeGravite = (graviteGroup.checkedRadioButtonId == R.id.graviteButtonYes)
        UtiTreatmentHelper.pauciSymptomatic = (pauciSpGroup.checkedRadioButtonId == R.id.pauciSpButtonYes)
        UtiTreatmentHelper.treatmentCanBeDelayed = (canBeDelayedGroup.checkedRadioButtonId == R.id.canBeDelayedButtonYes)


        // Affichage des résultats
        val intent = Intent(this, UtiAssistantTreatmentResult::class.java)
        startActivity(intent)
    }


}