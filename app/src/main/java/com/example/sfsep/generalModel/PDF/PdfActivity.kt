package com.example.sfsep.generalModel.PDF

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.github.barteksc.pdfviewer.util.FitPolicy
import kotlinx.android.synthetic.main.activity_pdf.*

class PdfActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)


        val file = intent.getStringExtra("file")
        val url = intent.getStringExtra("url")





        if (file != null) {
            supportActionBar!!.hide()
            pdfView.fromAsset(file).load()
        }

        else if (url != null) {
            val uri = Uri.parse(url)
            supportActionBar!!.hide()
            pdfView.fromUri(uri).load()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}