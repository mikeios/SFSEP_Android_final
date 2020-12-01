package com.example.sfsep.vaccins.model

import android.content.res.Resources
import com.example.sfsep.R
import com.example.sfsep.generalModel.ResourcesManager.getResourceString
import java.lang.Exception
import java.time.LocalDate
import java.util.*
import kotlin.properties.Delegates

object VaccinAssistantExpert {
    //DTP
    var dtpVaccinationDelai:Int = 10
        get() = if (VaccinAssistant.actualIS || VaccinAssistant.projetIS) {
            10
        } else {
            20
    }
    var dernierDTP = ""
    //Autres vaccins
    var nbROR = 0
    var nbVHB = 0

    var vzvFait : Boolean by Delegates.observable(false) { prep, old, new ->
        if (new) {
            seroVZV = new
        }
    }
    var vhaFait : Boolean by Delegates.observable(false) { prep, old, new ->
        if (new) {
            seroVHA = new
        }
    }

    var grippeFait = false
    var pneumoFait = false

    //Sérologies
    var seroVZV = false
    var seroVHA = true
    var acHBS = false
    var acHBC = false
    var agHBS = false

    fun calculateAge(ddnString: String, atDate: LocalDate) : Int {
        val ddn = LocalDate.parse(ddnString, VaccinAssistant.formatter)

        val ddnCalendar = Calendar.getInstance()
        val dateCalendar = Calendar.getInstance()

        ddnCalendar.set(ddn.year, ddn.monthValue, ddn.dayOfMonth)
        dateCalendar.set(atDate.year, atDate.monthValue, atDate.dayOfMonth)

        var age = dateCalendar[Calendar.YEAR] - ddnCalendar[Calendar.YEAR]

        if (dateCalendar[Calendar.DAY_OF_YEAR] < ddnCalendar[Calendar.DAY_OF_YEAR]) {
            age--
        }

        return age
    }



    fun checkDateValidity(dateString: String): Boolean {
        if (isValidDate(dateString)) {
            val ddn = LocalDate.parse("01/" + dateString, VaccinAssistant.formatter)
            return !ddn.isAfter(LocalDate.now())
        } else {
            return false
        }

    }

    private fun isValidDate(dateString:String) : Boolean {


        try {
            LocalDate.parse(dateString, VaccinAssistant.formatter)
        } catch (pe: Exception){
            TODO("Message d'erreur à afficher")
            return false;
        }
        return true;
    }

    fun affinateVaccins() {
        //DTP
        val ageActuel = calculateAge(VaccinAssistant.dateDeNaissanceString, LocalDate.now())

        if (checkDateValidity(dernierDTP)) {
            val dernierDTPdate = LocalDate.parse(dernierDTP, VaccinAssistant.formatter)
            val ageVaccination = calculateAge(VaccinAssistant.dateDeNaissanceString, dernierDTPdate)

            when (ageActuel) {
                in 65..9999 ->
                    if (ageActuel - ageVaccination > 10) {
                        DTP.commentaire = getResourceString(R.string.commentDTPquatre)

                    } else {
                        val delai = 10 - (ageActuel - ageVaccination)
                        DTP.commentaire = String.format(Resources.getSystem().getString(R.string.commentDTPcinq), delai)
                    }
                in 26..64 ->
                    affinateDTPin2565()
                else -> return
            }
        }

        // ROR, VHA, VZV, grippe, pneumo
        if (nbROR > 1) {
            val ROR = getResourceString(R.string.ROR)
            removeVaccinFromArray(ROR)
        }

        if (vhaFait || seroVHA) {
            val VHA = getResourceString(R.string.VHA)
            removeVaccinFromArray(VHA)
        }

        if (vzvFait || seroVZV) {
            removeVaccinFromArray("VZV")
        }

        if (grippeFait) {
            val grippe = getResourceString(R.string.grippe)
            removeVaccinFromArray(grippe)
        }

        if (pneumoFait) {
            val pneumocoque = getResourceString(R.string.pneumocoque)
            removeVaccinFromArray(pneumocoque)
        }

        VHB.commentaire = statutVHB()
    }

    fun affinateDTPin2565() {
        val ageActuel = calculateAge(VaccinAssistant.dateDeNaissanceString, LocalDate.now())

        if (checkDateValidity(dernierDTP)) {
            val dernierDTPdate = LocalDate.parse(dernierDTP, VaccinAssistant.formatter)
            val ageVaccination = calculateAge(VaccinAssistant.dateDeNaissanceString, dernierDTPdate)

            if ((ageActuel - ageVaccination) > dtpVaccinationDelai) {
                DTP.commentaire = getResourceString(R.string.commentDTPsix)
            } else {
                val delai = dtpVaccinationDelai - (ageActuel - ageVaccination)
                DTP.commentaire =
                    String.format(Resources.getSystem().getString(R.string.commentDTPsept), delai)
            }

            if (VaccinAssistant.projetIS || VaccinAssistant.actualIS) {
                DTP.commentaire += getResourceString(R.string.commentDTPhuit)
            } else {
                val ageArray = arrayOf(25, 45, 65)
                val tempArray = arrayListOf<Int>()

                for (valeur in ageArray) {
                    if (ageActuel < valeur) {
                        tempArray += valeur
                    }
                }

                if (tempArray.size != 0) {
                    DTP.commentaire += getResourceString(R.string.commentDTPneuf)
                    for (age in tempArray) {
                        DTP.commentaire += String.format(Resources.getSystem().getString(R.string.commentDTPdix), age)
                    }
                    DTP.commentaire += getResourceString(R.string.commentDTPonze)
                } else {
                    DTP.commentaire += getResourceString(R.string.commentDTPdouze)
                }
            }
        }
    }

    fun statutVHB() : String {
        if (agHBS) {
            return getResourceString(R.string.commentVHBdeux)
        } else {
            if (acHBC) {
                return getResourceString(R.string.commentVHBtrois)
            } else {
                // Ni AgHBS, ni Ac anti HbC
                // Le patient est-il vacciné?
                if (nbVHB > 2 || acHBS) {
                    //Immunisé
                    val VHB = getResourceString(R.string.VHB)
                    removeVaccinFromArray(VHB)
                    return ""
                } else {
                    // Sérologie négative
                    return getResourceString(R.string.commentVHBquatre)
                }
            }
        }
    }


    fun removeVaccinFromArray(name:String) {
        VaccinAssistant.vaccinsRecommandés.dropWhile { vaccin -> vaccin.nom == name }
    }

    fun resetExpert(){
        dernierDTP = ""
        nbROR = 0
        nbVHB = 0
        vzvFait = false
        vhaFait = false
        grippeFait = false
        pneumoFait = false


        seroVHA = true
        seroVZV = false
        acHBS = false
        acHBC = false
        agHBS = false
    }



}