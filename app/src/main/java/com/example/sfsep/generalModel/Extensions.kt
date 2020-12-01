package com.example.diaporamaphotos

import android.R.attr.checked
import android.content.res.Resources
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.sfsep.R
import com.example.sfsep.generalModel.ResourcesManager


fun Int.daysString():String {
    when (this) {
        1 -> return "1 " + ResourcesManager.getResourceString(R.string.jour)
        else -> return "$this " + ResourcesManager.getResourceString(R.string.jours)
    }
}

fun RadioGroup.enableAll(enabled: Boolean) {
    for (i in 0 until this.getChildCount()) {
        (this.getChildAt(i) as RadioButton).isEnabled = enabled
    }
}