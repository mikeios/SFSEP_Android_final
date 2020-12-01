package com.example.sfsep.generalModel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.sfsep.*
import com.example.sfsep.uti.UtiMainActivity
import com.example.sfsep.vaccins.VaccinMainActivity
import com.example.sfsep.workInProgress.WIPManager

class MenuManager (private val rootActivity: AppCompatActivity) {
    fun manageMenuItemSelected(id:Int) {
        when (id) {
            R.id.home -> backToMain()
            R.id.tools -> displayTools()
            R.id.switchs, R.id.preIS -> WIPManager.showWIPScreen(rootActivity, "2021")
            R.id.grossesse -> WIPManager.showWIPScreen(rootActivity, "2022")
            R.id.feedback -> sendEmail()
            R.id.about -> displayAbout()
            R.id.vaccins -> displayVaccins()
            R.id.uti -> displayUTI()

        }
    }

    fun backToMain(){
        val intent = Intent(rootActivity, MainActivity::class.java)
        startActivity(rootActivity, intent, null)
    }

    fun displayTools() {
        val intent = Intent(rootActivity, Tools::class.java)
        startActivity(rootActivity, intent, null)
    }

    fun displayAbout() {
        val intent = Intent(rootActivity, AboutActivity::class.java)
        startActivity(rootActivity, intent, null)
    }

    fun displayUTI(){
        val intent = Intent(rootActivity, UtiMainActivity::class.java)
        startActivity(rootActivity, intent, null)
    }

    fun displayVaccins() {
        val intent = Intent(rootActivity, VaccinMainActivity::class.java)
        startActivity(rootActivity, intent, null)
    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.setType("message/rfc822");
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("mikael@mikeiosapps.com"))
        rootActivity.startActivity(intent)
    }
}