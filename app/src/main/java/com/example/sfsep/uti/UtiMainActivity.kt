package com.example.sfsep.uti

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.FAQ.FaqActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.PDF.PDFManager
import com.example.sfsep.generalModel.ResourcesManager
import com.example.sfsep.generalModel.WebsiteManager
import com.example.sfsep.uti.assistantReco.UtiAssistantRecoForm

class UtiMainActivity : AppCompatActivity() {

    val menuManager = MenuManager(this)
    val pdfManager = PDFManager(this)
    val webManager = WebsiteManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uti_main)
        supportActionBar!!.title = getString(R.string.uti)
        ResourcesManager.context = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun assistantRecoTouched(sender:View) {
        val intent = Intent(this, UtiAssistantRecoForm::class.java)
        startActivity(intent)
    }

    fun assistantTttTouched(sender: View) {
        val intent = Intent(this, UtiAssistantTreatmentForm::class.java)
        startActivity(intent)
    }

    fun texteIntegralTouched(sender: View) {
        val url = getString(R.string.uti_sfsep_url)
        webManager.goToWebsite(url)
    }

    fun definitionsTouched(sender:View) {
        pdfManager.showPDFFile(R.string.file_uti_definitions)
    }

    fun utiPousseesTouched(sender: View) {
        pdfManager.showPDFFile(R.string.file_uti_poussee)
    }

    fun utiAndTreatmentsTouched(sender: View) {
        pdfManager.showPDFFile(R.string.file_uti_ttt)
    }

    fun utiDepistageTouched(sender: View) {
        pdfManager.showPDFFile(R.string.file_uti_depistage)
    }

    fun utiFAQTouched(sender: View) {
        val intent = Intent(this, FaqActivity::class.java)
        intent.putExtra(getString(R.string.faqIdForIntent), getString(R.string.faq_key_uti))
        startActivity(intent)
    }
}