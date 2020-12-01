package com.example.sfsep.generalModel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

class WebsiteManager (private val rootActivity: AppCompatActivity) {
    fun goToWebsite(url:String) {

        val urlToShow: Uri = Uri.parse(url)

        val intent = Intent(Intent.ACTION_VIEW, urlToShow)

        if (intent.resolveActivity(rootActivity.packageManager) != null) {
            rootActivity.startActivity(intent)
        }
    }
}