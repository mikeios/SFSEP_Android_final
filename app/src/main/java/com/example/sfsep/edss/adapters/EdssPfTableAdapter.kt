package com.example.sfsep.edss.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sfsep.R
import com.example.sfsep.edss.model.EDSSManager
import kotlinx.android.synthetic.main.cell_edss_pf_detail.view.*

class EdssPfTableAdapter(private val pfArray:Array<String>,
                         private val rootActivity: AppCompatActivity,
                         private val pfIndex:Int): RecyclerView.Adapter<EdssPfTableAdapter.EdssPFViewHolder>() {

    inner class EdssPFViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var ui_pfDetailTextView = itemView.detailTextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            pfSelected(adapterPosition)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EdssPFViewHolder {
        // 1 - Charger la vue en XML
        val rootView = LayoutInflater.from(p0.context).inflate(R.layout.cell_edss_pf_detail, p0, false)

        // 2 - Créer un viewholder pour contrôler cette vue
        val holder = EdssPFViewHolder(rootView)

        // 3 - Retourner le viewholder

        return holder


    }

    override fun onBindViewHolder(holder: EdssPFViewHolder, raw: Int) {
        // 1 - Obtenir le détail du PF
        val pfDetail = pfArray[raw]

        // 2 - Envoyer dans le holder
        holder.ui_pfDetailTextView.text = pfDetail



    }

    override fun getItemCount(): Int {
        return pfArray.size
    }

    fun pfSelected(index:Int) {
        when (pfIndex) {
            0 -> EDSSManager.pyramidalPF = index
            1 -> EDSSManager.cerebellarPF = index
            2 -> EDSSManager.sensitivePF = index
            3 -> EDSSManager.brainstemPF = index
            4 -> EDSSManager.sphPF = index
            5 -> EDSSManager.visualPF = index
            6 -> EDSSManager.cognitivePF = index
            7 -> EDSSManager.otherPF = index
            8 -> EDSSManager.ambulationPF = index
        }

        rootActivity.finish()
    }
}