package com.example.sfsep.vaccins.model

import android.app.Application
import android.content.res.Resources
import android.content.res.loader.ResourcesLoader
import android.content.res.loader.ResourcesProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.sfsep.MainActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.ResourcesManager.getResourceString

// Classe Vaccins
class Vaccin(
    var nom:String,
    var commentaire:String?,
    var vivant: Boolean = false,
    var recommandé:Boolean = true,
    var contreIndiqué:Boolean = false) {

}



// Les vaccins de base

var ROR = Vaccin(
    getResourceString(R.string.ROR),
    getResourceString(R.string.commentROR),
    true,
    false
)
var VHB = Vaccin(
    getResourceString(R.string.VHB),
    getResourceString(R.string.commentVHB),
    false,
    false)
var VZV = Vaccin(
    "VZV",
    getResourceString(R.string.commentVZV),
    true,
    !VaccinAssistantExpert.seroVZV
)
var VHA = Vaccin(
    getResourceString(R.string.VHA),
    getResourceString(R.string.commentVHA),
    false,
    false)
var DTP = Vaccin(
    "DTP",
    getResourceString(R.string.commentDTP),
    false,
    false)

// Les vaccins particuliers
var grippe = Vaccin(
    getResourceString(R.string.grippe),
    getResourceString(R.string.commentGrippe),
    false,
    false)
var pneumocoque = Vaccin(
    getResourceString(R.string.pneumocoque),
    getResourceString(R.string.commentPneumocoque),
    false,
    false)
var autresVVA = Vaccin(
    getResourceString(R.string.autresVVA),
    getResourceString(R.string.commentVVA),
    true,
    false)

// Les matrices
var vaccinArray = listOf<Vaccin>(ROR, VHB, VZV, VHA, DTP, grippe, pneumocoque, autresVVA)
