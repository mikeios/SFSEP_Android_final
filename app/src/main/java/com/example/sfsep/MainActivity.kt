package com.example.sfsep

import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sfsep.edss.adapters.EdssPfTableAdapter
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.RSS.RSSArticle
import com.example.sfsep.generalModel.RSS.RssTableAdapter
import com.example.sfsep.generalModel.RSS.rssFeedManager
import com.example.sfsep.generalModel.WebsiteManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edss_pf_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private val menuManager = MenuManager(this)
    lateinit var requestQueue: RequestQueue
    val websiteManager = WebsiteManager(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestQueue = Volley.newRequestQueue(this)
        parseRSS()

        // Swipe to refresh
        //** Set the colors of the Pull To Refresh View
        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.colorPrimary))
        itemsswipetorefresh.setColorSchemeColors(Color.WHITE)

        itemsswipetorefresh.setOnRefreshListener {
            parseRSS(true)
            itemsswipetorefresh.isRefreshing = false
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

    fun parseRSS(forceParsing:Boolean = false) {
        if (rssFeedManager.shouldUpdateData || forceParsing) {

            val url = getString(R.string.sfsep_rss)
            val request = StringRequest(Request.Method.GET, url, {

                var articles = it.split("<item>").drop(1)
                rssFeedManager.parsedArticles = arrayOf()


                for (article in articles) {

                    val rssArticle = RSSArticle(article.substringAfter("<title>").substringBefore("</title>"),
                        article.substringAfter("<link>").substringBefore("</link>"))
                    if (article.contains("img")) {

                        val tempImageUrl = (article.substringAfter("<img").substringBefore("alt"))
                        val imageUrl = tempImageUrl.substringAfter("src=").dropLast(2).drop(1)
                        rssArticle.imageUrl = imageUrl
                    }
                    rssFeedManager.parsedArticles += rssArticle

                }

                updateTable()
                rssFeedManager.lastParsingDate = LocalDate.now()

            }, {
                println(it.localizedMessage)
            })



            requestQueue.add(request)
        } else {
            updateTable()
        }



    }

    fun featuredArticleTouched(sender:View) {
        if (!rssFeedManager.parsedArticles.isEmpty()) {
            val url = rssFeedManager.parsedArticles.first().link
            websiteManager.goToWebsite(url)
        }

    }


    fun updateTable() {
        // Gestion de l'article numéro 1
        val featuredArticle = rssFeedManager.parsedArticles.first()
        if (featuredArticle.imageUrl != null) {
            Picasso.get().load(featuredArticle.imageUrl).into(featuredArticleImage)
        }
        featuredArticleTitleField.text = featuredArticle.title



        //Chargement du tableau
        val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        rssTable.addItemDecoration(itemDecor)

        rssTable.adapter = RssTableAdapter(rssFeedManager.parsedArticles.drop(1).toTypedArray(), this)
        rssTable.layoutManager = LinearLayoutManager(this)
        rssTable.setHasFixedSize(false)
    }
}