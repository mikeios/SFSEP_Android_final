package com.example.sfsep.edss

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sfsep.R
import com.example.sfsep.edss.adapters.EdssMainTableAdapter
import com.example.sfsep.edss.model.EDSSManager
import com.example.sfsep.generalModel.MenuManager
import kotlinx.android.synthetic.main.activity_edss.*

class EdssMainTableActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edss)
        supportActionBar!!.title = getString(R.string.edss)

        val itemDecor = DividerItemDecoration(this, VERTICAL)
        edssTable.addItemDecoration(itemDecor)

        edssTable.adapter = EdssMainTableAdapter(this)
        edssTable.layoutManager = LinearLayoutManager(this)

        checkPyramidalScore()
        updateEDSSLabel()

    }

    override fun onRestart() {
        edssTable.adapter!!.notifyDataSetChanged()
        checkPyramidalScore()
        updateEDSSLabel()
        super.onRestart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun resetButtonTouched(button: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.Effacer)
        builder.setMessage(R.string.EffacerMessage)


        builder.setPositiveButton(R.string.oui) { dialog, which ->
            EDSSManager.resetEDSS()
            edssTable.adapter!!.notifyDataSetChanged()
            updateEDSSLabel()
        }

        builder.setNegativeButton(R.string.non) { dialog, which ->

        }


        builder.show()
    }

    fun checkPyramidalScore() {
        if (EDSSManager.pyramidalPF >= 3 && !EDSSManager.pyramidalAlertShown) {
            showPyramidalAlert()
        }
    }

    fun updateEDSSLabel() {
        EDSSManager.calculEDSS()
        edssResult.text = EDSSManager.edss
    }

    fun showPyramidalAlert() {
        EDSSManager.pyramidalAlertShown = true
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.EDSSAlertTitle)
        builder.setMessage(R.string.EDSSAlertMessage)


        builder.setPositiveButton(R.string.oui) { dialog, which ->
            EDSSManager.cerebellarX = true
            edssTable.adapter!!.notifyDataSetChanged()
            updateEDSSLabel()
        }

        builder.setNegativeButton(R.string.non) { dialog, which ->
            EDSSManager.cerebellarX = false
            edssTable.adapter!!.notifyDataSetChanged()
            updateEDSSLabel()
        }


        builder.show()
    }

}