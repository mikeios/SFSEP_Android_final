package com.example.sfsep.generalModel

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.R

class DisclaimerManager(val rootActivity: AppCompatActivity, val preferenceKey:String) {

    fun showDisclaimerIfNeeded() {
            val preferenceReader = rootActivity.getSharedPreferences("default", Context.MODE_PRIVATE)
            println(preferenceReader.getBoolean(preferenceKey, false))
            if (!preferenceReader.getBoolean(preferenceKey, false)) {

                val preferenceWriter = rootActivity.getSharedPreferences("default", Context.MODE_PRIVATE).edit()
                preferenceWriter.putBoolean(preferenceKey, true)
                preferenceWriter.apply()

                val alert = AlertDialog.Builder(rootActivity)
                alert.setTitle(R.string.disclaimer_title)
                alert.setMessage(R.string.disclaimer_message)
                alert.setNeutralButton(R.string.OK, null)
                alert.show()
            }

        }


}