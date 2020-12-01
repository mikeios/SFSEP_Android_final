package com.example.sfsep.FAQ

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sfsep.PopUpWindow
import com.example.sfsep.R
import kotlinx.android.synthetic.main.cell_edss_pf_detail.view.*

class FaqAdapter(val faqArray:Array<String>,
                 val rootActivity: AppCompatActivity): RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    val questions
        get() = faqArray.map { it.split("|").first() }

    val answers
        get() = faqArray.map { it.split("|").last() }

    inner class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var ui_pfDetailTextView = itemView.detailTextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val intent = Intent(rootActivity, PopUpWindow::class.java)
            intent.putExtra("answer", answers[adapterPosition])
            rootActivity.startActivity(intent)


        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FaqViewHolder {
        // 1 - Charger la vue en XML
        val rootView = LayoutInflater.from(p0.context).inflate(R.layout.cell_edss_pf_detail, p0, false)

        // 2 - Créer un viewholder pour contrôler cette vue
        val holder = FaqViewHolder(rootView)

        // 3 - Retourner le viewholder

        return holder


    }

    override fun onBindViewHolder(holder: FaqViewHolder, raw: Int) {
        // 1 - Obtenir le détail du PF
        val question = questions[raw]

        // 2 - Envoyer dans le holder
        holder.ui_pfDetailTextView.text = question



    }

    override fun getItemCount(): Int {
        return faqArray.size
    }

}