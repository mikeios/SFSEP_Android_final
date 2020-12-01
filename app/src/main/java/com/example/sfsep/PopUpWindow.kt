package com.example.sfsep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_pop_up_window.*

class PopUpWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_window)
        supportActionBar!!.hide()
        val answer = intent.getStringExtra("answer")
        if (answer != null ) {
            faqAnswer.text = answer
        }

    }

    fun okPressed(button: View) {
        this.finish()
    }
}