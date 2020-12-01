package com.example.sfsep.vaccins.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sfsep.R
import kotlinx.android.synthetic.main.cell_standard_title_and_subtitle.view.*


class VaccinResultAdapter(private val adapterType:String): RecyclerView.Adapter<VaccinResultAdapter.VaccinViewHolder>() {
    var vaccinArray = listOf<Vaccin>()
    init {
        when (adapterType) {
            "reco" -> vaccinArray = VaccinAssistant.vaccinsRecommandés
            "interdit" -> vaccinArray = VaccinAssistant.vaccinsInterdits
        }
    }

    inner class VaccinViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ui_title = itemView.cell_title
        val ui_comment = itemView.cell_comment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccinViewHolder {
        // 1 - Charger la vue en XML
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.cell_standard_title_and_subtitle, parent, false)

        // 2 - Créer un viewholder pour contrôler cette vue
        val holder = VaccinViewHolder(rootView)

        // 3 - Retourner le viewholder
        return holder
    }

    override fun onBindViewHolder(holder: VaccinViewHolder, position: Int) {
        val vaccin = vaccinArray[position]
        val name = vaccin.nom
        val comment = vaccin.commentaire

        holder.ui_title.text = name
        holder.ui_comment.text = comment
    }

    override fun getItemCount(): Int {
        return vaccinArray.size
    }
}