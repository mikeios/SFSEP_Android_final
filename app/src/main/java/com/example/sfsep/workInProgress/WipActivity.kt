package com.example.sfsep.workInProgress

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import kotlinx.android.synthetic.main.activity_wip.*

class WipActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wip)
        val year = intent.getStringExtra("year")
        supportActionBar!!.title = getString(R.string.wip)
        expectedLabel.text = getString(R.string.expected) + " $year"

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