package com.example.sfsep.vaccins

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.PDF.PDFManager

class VaccinSummaryActivity : AppCompatActivity() {

    val menuManager = MenuManager(this)
    val pdfManager = PDFManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccin_summary)
        supportActionBar!!.title = getString(R.string.vaccins)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun naifButtonTouched(sender: View) {
        pdfManager.showPDFFile(R.string.file_vaccin_reco_naif)
    }

    fun immunomodulateurButtonTouched(sender: View) {
        pdfManager.showPDFFile(R.string.file_vaccin_reco_immunomodulateur)
    }

    fun immunosuppresseurButtonTouched(sender: View) {
        pdfManager.showPDFFile(R.string.file_vaccin_reco_immunosuppresseur)
    }

}