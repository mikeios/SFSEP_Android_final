package com.example.sfsep.workInProgress

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

object WIPManager {
    fun showWIPScreen(rootActivity: AppCompatActivity, year:String) {
        val intent = Intent(rootActivity, WipActivity::class.java)
        intent.putExtra("year", year)
        rootActivity.startActivity(intent)
    }
}