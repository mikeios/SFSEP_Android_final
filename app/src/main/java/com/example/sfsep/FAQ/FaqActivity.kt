package com.example.sfsep.FAQ

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import kotlinx.android.synthetic.main.activity_faq.*

class FaqActivity : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        supportActionBar!!.title = getString(R.string.faq)
        val faqID = intent.getStringExtra(getString(R.string.faqIdForIntent))


        when (faqID) {
            getString(R.string.faq_key_vaccination) -> updateTable(R.array.Vaccinations_FAQ)
            getString(R.string.faq_key_uti) -> updateTable(R.array.uti_faq)
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

    fun updateTable(arrayID:Int) {
        val faqArray = resources.getStringArray(arrayID)

        val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        faqRecyclerView.addItemDecoration(itemDecor)

        faqRecyclerView.adapter = FaqAdapter(faqArray, this)
        faqRecyclerView.layoutManager = LinearLayoutManager(this)
        faqRecyclerView.setHasFixedSize(false)
    }
}