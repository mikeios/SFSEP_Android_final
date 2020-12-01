package com.example.sfsep.vaccins.model

import com.example.sfsep.R
import com.example.sfsep.generalModel.ResourcesManager.getResourceString
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.properties.Delegates

object VaccinAssistant {
    // Les arrays avec les vaccins en définitif
    var vaccinsRecommandés = listOf<Vaccin>()
    var vaccinsInterdits = listOf<Vaccin>()

    // Gestion des dates
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")


    var dateDeNaissanceString : String by Delegates.observable("") {
            prep, old, new ->
        ddnIsValid = isValidDate(new)
        if (dateDeNaissance != null) {
            val date1980String = "01/01/1980"
            val date1980 = LocalDate.parse(date1980String, formatter)
            before1980 = dateDeNaissance!!.isBefore(date1980)
        }
    }

    var ddnIsValid = false
    var dateDeNaissance: LocalDate? = null
    var before1980 = false




     fun isValidDate(dateString:String) : Boolean {
        try {
            dateDeNaissance = LocalDate.parse("01/" + dateString, formatter)
        } catch (pe: Exception){
            return false;
        }
        return true;
    }

    // ExpertMode
    var expertMode = false

    // Variables pour déterminer les vaccins
    var cocooning = false
    var actualIS = false
    var projetIS = false
    var highEDSS = false
    var corticoides = false

    var vzvRecent : Boolean by Delegates.observable(false)  {
            prep, old, new ->
        VaccinAssistantExpert.seroVZV = new
        VaccinAssistantExpert.vzvFait = vzvRecent
        VZV.recommandé = !new
    }
    var vvaRecent = false
    var viRecent = false
    var voyageur = false

    var quandVacciner = getResourceString(R.string.quandVacciner)
    var delaiVaccinTraitement = getResourceString(R.string.delaiVaccinTraitement)
    var coAdministration = getResourceString(R.string.coAdministration)



    fun resetVaccins(){
        ROR = Vaccin(
            getResourceString(R.string.ROR),
            getResourceString(R.string.commentROR),
            true,
            false
        )
        VHB = Vaccin(
            getResourceString(R.string.VHB),
            getResourceString(R.string.commentVHB),
            false,
            false)
        VZV = Vaccin(
            "VZV",
            getResourceString(R.string.commentVZV),
            true,
            !VaccinAssistantExpert.seroVZV
        )
        VHA = Vaccin(
            getResourceString(R.string.VHA),
            getResourceString(R.string.commentVHA),
            false,
            false)
        DTP = Vaccin(
            "DTP",
            getResourceString(R.string.commentDTP),
            false,
            false)

// Les vaccins particuliers
        grippe = Vaccin(
            getResourceString(R.string.grippe),
            getResourceString(R.string.commentGrippe),
            false,
            false)
        pneumocoque = Vaccin(
            getResourceString(R.string.pneumocoque),
            getResourceString(R.string.commentPneumocoque),
            false,
            false)
        autresVVA = Vaccin(
            getResourceString(R.string.autresVVA),
            getResourceString(R.string.commentVVA),
            true,
            false)
        // Les matrices
        vaccinArray = listOf<Vaccin>(ROR, VHB, VZV, VHA, DTP, grippe, pneumocoque, autresVVA)
        vaccinsRecommandés = listOf<Vaccin>()
        vaccinsInterdits = listOf<Vaccin>()

    }

    fun personnalizeVaccins(){
        if (before1980) {
            ROR.commentaire = getResourceString(R.string.commentRORdeux)
        }

        if (actualIS) {

            for (vaccination in vaccinArray) {
                if (vaccination.vivant) {
                    vaccination.contreIndiqué = true
                }
            }
            ROR.commentaire = getResourceString(R.string.commentRORtrois)
            VZV.commentaire = getResourceString(R.string.commentVZVdeux)
            grippe.commentaire = getResourceString(R.string.commentGrippeDeux)
            DTP.commentaire = getResourceString(R.string.commentDTPdeux)
            pneumocoque.recommandé = true
        }

        if (projetIS) {
            grippe.commentaire = getResourceString(R.string.commentGrippeDeux)
            pneumocoque.recommandé = true
            VZV.recommandé = !VaccinAssistantExpert.seroVZV
            VZV.commentaire = getResourceString(R.string.commentVZVtrois)
        }

        if (highEDSS) {
            grippe.commentaire = getResourceString(R.string.commentGrippeDeux)
        }



        if (cocooning) {
            DTP.commentaire = DTP.commentaire + getResourceString(R.string.commentDTPtrois)
        }

        if ((actualIS || projetIS) && voyageur) {
            VHA.recommandé = true
        }
    }

    fun generateArrays(){

        personnalizeVaccins()

        if (actualIS && projetIS) {
            for (vaccin in vaccinArray) {
                if (vaccin.recommandé) {
                    vaccinsRecommandés += vaccin
                }
                if (vaccin.contreIndiqué && !vaccin.recommandé) {
                    vaccinsInterdits += vaccin
                }
            }
        } else {

            for (vaccin in vaccinArray) {
                if (vaccin.recommandé && !(vaccin.contreIndiqué)) {
                    vaccinsRecommandés += vaccin
                }
                if (vaccin.contreIndiqué) {
                    vaccinsInterdits += vaccin
                }
            }
        }
    }

    fun generateDelais() {
        val testArray = vaccinsRecommandés.filter() { it.vivant }
        val testArray2 = vaccinsRecommandés.filter() { !it.vivant }

        val doitFaireVVA = !testArray.isEmpty()
        val doitFairePlusieursVVA = testArray.size > 1
        val doitFaireVI = !testArray2.isEmpty()
        val doitFaireVZV = vaccinsRecommandés.contains(VZV)

        // Quand vacciner?
        quandVacciner = ""
        if (actualIS && doitFaireVVA) {
            quandVacciner += getResourceString(R.string.quandVaccinerDeux)
        }
        if (corticoides) {

            if (doitFaireVVA) {
                quandVacciner += getResourceString(R.string.quandVaccinerTrois)
            }

            if (doitFaireVI) {
                quandVacciner += getResourceString(R.string.quandVaccinerQuatre)
            }
        }

        if ((!actualIS && !corticoides) || (actualIS && !doitFaireVVA && !corticoides)) {
            quandVacciner = getResourceString(R.string.quandVacciner)
        }

        // Quand traiter?
        delaiVaccinTraitement = ""
        if (projetIS) {
            if (doitFaireVZV || vzvRecent) {
                delaiVaccinTraitement = getResourceString(R.string.delaiVTdeux)
            }
            if (doitFaireVVA || vvaRecent) {
                delaiVaccinTraitement += getResourceString(R.string.delaiVTtrois)
            }

            if (doitFaireVI || viRecent) {
                delaiVaccinTraitement += getResourceString(R.string.delaiVTquatre)
            }

            if (!doitFaireVZV && !doitFaireVI && doitFaireVVA) {
                delaiVaccinTraitement = getResourceString(R.string.delaiVaccinTraitement)
            }
        } else if (actualIS) {
            delaiVaccinTraitement = getResourceString(R.string.delaiVTcinq)
        } else {
            delaiVaccinTraitement = getResourceString(R.string.delaiVTsix)
        }


        //Coadministration
        coAdministration = ""
        if (doitFaireVZV && pneumocoque.recommandé) {
            coAdministration = getResourceString(R.string.coAdDeux)
            if (doitFaireVVA || doitFaireVI) {
                coAdministration += getResourceString(R.string.coAdTrois)
            }
        }
        if (doitFairePlusieursVVA) {
            coAdministration += getResourceString(R.string.coAdQuatre)
        }
        if (!doitFairePlusieursVVA && doitFaireVI) {
            coAdministration += getResourceString(R.string.coAdCinq)
        }
        if (!doitFaireVZV && !doitFaireVVA && !doitFaireVI) {
            coAdministration = getResourceString(R.string.coAdSix)
        }
        if (coAdministration == "") {
            coAdministration = getResourceString(R.string.coAdministration)
        }
    }

}