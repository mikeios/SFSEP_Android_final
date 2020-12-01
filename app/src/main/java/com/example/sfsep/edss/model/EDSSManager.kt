package com.example.sfsep.edss.model

import android.content.Context
import com.example.sfsep.R

object EDSSManager {

    // L'EDSS final
    var edss = ""


    /* Score Ambulatoire
    0 = Fully Ambulatory
    1 = 300-500m sans aide (EDSS 4.5)
    2 = 200-300m sans aide (EDSS 5)
    3 = 100-200m sans aide (EDSS 5.5)
    4 = aide unilatérale (EDSS 6)
    5 = aide bilatérale (EDSS 6.5)
    6 = FR autonome transferts (EDSS 7)
    7 = FR autonome déplacement (EDSS 7.5)
    8 = FR aidé pour déplacement (EDSS 8)
    9 = Lit avec usage partiel des bras (EDSS 8.5)
    10 = Lit grabataire, peut manger et communiquer (EDSS 9)
    11 = Impotent (EDSS 9.5) */

    var ambulationPF:Int=0

    // Paramètres fonctionnels
    var pyramidalPF:Int = 0
    var cerebellarPF:Int = 0
    var sensitivePF:Int = 0
    var brainstemPF:Int = 0
    var sphPF:Int = 0
    var visualPF:Int = 0
    var cognitivePF:Int = 0
    var otherPF:Int = 0

    var cerebellarX = false
    var pyramidalAlertShown = false



    // Arrays de PF
    val pfArray:Array<Int>
        get() = arrayOf(pyramidalPF, cerebellarPF, sensitivePF, brainstemPF, sphPF, visualPF, cognitivePF, otherPF, ambulationPF)


    val nombreZero:Int
        get() = pfArray.filter { it == 0 }.size
    val nombreUn
        get() = pfArray.filter { it == 1 }.size
    val nombreDeux
        get() = pfArray.filter { it == 2 }.size
    val nombreTrois
        get() = pfArray.filter { it == 3 }.size
    val nombreQuatre
        get() = pfArray.filter { it == 4 }.size
    val nombreCinqSix
        get() = pfArray.filter { (it == 5) || (it == 6) }.size

    // Correspondance d'image
    fun getDigitFileName(forValue:Int, isAmbulationPF:Boolean):Int {
        if (isAmbulationPF) {
            when (forValue) {
                0, 1, 2, 3 -> return R.drawable.edss_ambu
                4, 5 -> return R.drawable.edss_canne
                6, 7, 8 -> return R.drawable.edss_fauteuil
                9, 10, 11 -> return R.drawable.edss_lit
            }
        } else {
            when (forValue) {
                0 -> return R.drawable.edss_digit0
                1 -> return R.drawable.edss_digit1
                2 -> return R.drawable.edss_digit2
                3 -> return R.drawable.edss_digit3
                4 -> return R.drawable.edss_digit4
                5 -> return R.drawable.edss_digit5
                6 -> return R.drawable.edss_digit6
            }
        }




        return 0
    }

    fun getPFStrings(context: Context):Array<String> {
        return context.resources.getStringArray(R.array.EdssPFList)
    }

    fun calculEDSS() {
        if (cerebellarX) {
            cerebellarPF = 0
        }
        when (ambulationPF) {
            11 -> edss = "9.5"
            10 -> edss = "9.0"
            9 -> edss = "8.5"
            8 -> edss = "8.0"
            7 -> edss = "7.5"
            6 -> edss = "7.0"
            5 -> edss = "6.5"
            4 -> edss = "6.0"
            3 -> edss = "5.5"
            2 -> edss = "5.0"
            1 -> edss = "4.5"
            0 -> calculPF()
        }
    }

    private fun calculPF() {
        if (nombreCinqSix != 0 || nombreQuatre >= 2 || nombreTrois >= 6) {
            edss = "5.0"
        } else if (nombreQuatre == 1 && nombreTrois > 3) {
            edss = "5.0"
        } else if (nombreQuatre == 1 && nombreTrois <= 2 && nombreTrois != 0) {
            edss = "4.5"
        } else if (nombreQuatre == 1 && nombreTrois == 0 && nombreDeux != 0) {
            edss = "4.5"
        } else if (nombreQuatre == 1 && nombreTrois == 0 && nombreDeux == 0) {
            edss = "4.0"
        }
        else if (nombreTrois == 5) {
            edss = "4.5"
        } else if (nombreTrois >= 2) {
            if (nombreTrois == 2 && nombreDeux == 0) {
                edss = "3.5"}
            else {
                edss = "4.0"}
        }
        else if (nombreTrois == 1) {
            if (nombreDeux == 0) {
                edss = "3.0"}
            else if (nombreDeux == 1 || nombreDeux == 2) {
                edss = "3.5"}
            else {
                edss = "4.0"}

        } else if (nombreDeux == 5) {
            edss = "3.5"}
        else if (nombreDeux == 3 || nombreDeux == 4) {
            edss = "3.0"}
        else if (nombreDeux == 2) {
            edss = "2.5"}
        else if (nombreDeux == 1) {
            edss = "2.0"}
        else if (nombreUn > 1) {
            edss = "1.5"}
        else if (nombreUn == 1) {
            edss = "1.0"}
        else {
            edss = "0"}
    }

    fun resetEDSS() {
        ambulationPF = 0
        pyramidalPF = 0
        cerebellarPF = 0
        sensitivePF = 0
        brainstemPF = 0
        sphPF = 0
        visualPF = 0
        cognitivePF = 0
        otherPF = 0

        cerebellarX = false
        pyramidalAlertShown = false

        calculEDSS()
    }


}