package com.example.sfsep.generalModel.PDF

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class PDFManager (val rootActivity: AppCompatActivity) {

    fun showPDFFile(fileId:Int) {
        val fileName:String = rootActivity.getString(fileId)

        val intent = Intent(rootActivity, PdfActivity::class.java)
        intent.putExtra("file", fileName)
        rootActivity.startActivity(intent)


    }

    fun showPdfUrl(urlId:Int) {
        val url:String = rootActivity.getString(urlId)

        val intent = Intent(rootActivity, PdfActivity::class.java)
        intent.putExtra("url", url)
        rootActivity.startActivity(intent)


    }
}