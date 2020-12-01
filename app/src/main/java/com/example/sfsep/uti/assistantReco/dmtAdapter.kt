package com.example.sfsep.uti.assistantReco

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sfsep.R
import com.example.sfsep.edss.adapters.EdssPfTableAdapter
import com.example.sfsep.uti.model.UtiRecoManager
import kotlinx.android.synthetic.main.cell_edss_pf_detail.view.*

class dmtAdapter(private val rootActivity:AppCompatActivity): RecyclerView.Adapter<dmtAdapter.EdssPFViewHolder>() {

    val dmtArray = rootActivity.resources.getStringArray(R.array.dmt)

    inner class EdssPFViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val ui_text = itemView.detailTextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            selectedTreatment(adapterPosition)
        }

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): dmtAdapter.EdssPFViewHolder {
        // 1 - Charger la vue en XML
        val rootView = LayoutInflater.from(p0.context).inflate(R.layout.cell_edss_pf_detail, p0, false)

        // 2 - Créer un viewholder pour contrôler cette vue
        val holder = EdssPFViewHolder(rootView)

        // 3 - Retourner le viewholder

        return holder


    }

    override fun onBindViewHolder(holder: dmtAdapter.EdssPFViewHolder, raw: Int) {
        // 1 - Obtenir le détail du PF
        val dmt = dmtArray[raw]

        // 2 - Envoyer dans le holder
        holder.ui_text.text = dmt



    }

    override fun getItemCount(): Int {
        return dmtArray.size
    }



    fun selectedTreatment(position:Int) {
        UtiRecoManager.currentTreatment = dmtArray[position]
        rootActivity.finish()
    }

}