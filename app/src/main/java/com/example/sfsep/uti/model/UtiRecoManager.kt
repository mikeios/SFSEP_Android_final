package com.example.sfsep.uti.model

import android.content.res.Resources
import com.example.sfsep.R
import com.example.sfsep.generalModel.ResourcesManager

object UtiRecoManager {

    enum class AgeTier {
        less65, between65And75, over75;

        var value = 0
        get() = when(this) {
            less65 -> 0
            between65And75 -> 1
            over75 -> 2
        }
    }

    enum class dmtRaisesRisk {
        yes, no, possibly;

        var value = ""
        get() = when(this) {
            yes -> ResourcesManager.getResourceString(R.string.oui)
            no -> ResourcesManager.getResourceString(R.string.non)
            possibly -> ResourcesManager.getResourceString(R.string.possiblement)
        }
    }

    val treatments = ResourcesManager.getResourceArray(R.array.dmt)
    var ageTier = AgeTier.less65
    var isMale = true
    var frailty = false
    var tractusAbnormality = false
    var reflux = false
    var highPression = false
    var corticosteroids = false
    var currentTreatment = treatments.first()
    var hypogamma = false
    var neutropenia = false
    var badRenalFunction = false
    var recurrentUTI = false
    var bolus = false
    var plasmapheresis = false
    var fampridine = false
    val immunosuppressiveTreatment:Boolean
        get() = (currentTreatment != "IFN-β")
            && (currentTreatment != "Glatiramer Acetate")
            && (currentTreatment != treatments.first())

    var reco_recurrentUtiText:String = ""
        get() = if (recurrentUTI) {
            ResourcesManager.getResourceString(R.string.oui)
        } else {
            ResourcesManager.getResourceString(R.string.non)
        }

    var reco_screenColonizationText:String = ""
        get() = if (colonizationShouldBeScreened()) {
            ResourcesManager.getResourceString(R.string.uti_screening)
        } else {
            ResourcesManager.getResourceString(R.string.non_screening)
        }

    var reco_screenBeforeBudText:String = ""
        get() = if (colonizationShouldBeScreenedBeforeBUD()) {
            ResourcesManager.getResourceString(R.string.uti_screening_bud)
        } else {
            ResourcesManager.getResourceString(R.string.non)
        }

    var reco_preventiveTreatmentText:String = ""
        get() = if (recurrentUTI) {
            ResourcesManager.getResourceString(R.string.uti_prevention)
        } else {
            ResourcesManager.getResourceString(R.string.non)
        }



    fun atRiskSituation() : Boolean {

        return (ageTier == AgeTier.over75
                || (ageTier == AgeTier.between65And75 && frailty)
                || isMale
                || tractusAbnormality
                || immunosuppressiveTreatment
                || hypogamma
                || neutropenia
                || badRenalFunction
                )
    }

    fun treatmentRaisesRisk() : dmtRaisesRisk {
        if (currentTreatment == "Mitoxantrone"
                || currentTreatment == "Alemtuzumab"
                || currentTreatment == "Cyclophosphamide"
                || currentTreatment == "Rituximab") {
            return dmtRaisesRisk.yes
        } else if (currentTreatment == "Ocrelizumab" && hypogamma) {
            return dmtRaisesRisk.possibly
        } else {
            return dmtRaisesRisk.no
        }
    }

    fun colonizationShouldBeScreened() : Boolean {
        return (immunosuppressiveTreatment && hypogamma)
    }

    fun colonizationShouldBeScreenedBeforeBUD() : Boolean {
        return highPression
                || recurrentUTI
                || reflux
    }

    fun additionnalReco() : String? {
        var additionalString = ""

        if (bolus) {
            additionalString = additionalString + ResourcesManager.getResourceString(R.string.uti_bolusReco) + "\n\n"
        }

        if (plasmapheresis) {
            additionalString = additionalString + ResourcesManager.getResourceString(R.string.uti_plasmaReco) + "\n\n"
        }
        if (fampridine) {
            additionalString = additionalString + ResourcesManager.getResourceString(R.string.uti_fampridineReco) + "\n\n"
        }

        if (additionalString == "") {
            return  null
        } else {
            return additionalString
        }
    }

}