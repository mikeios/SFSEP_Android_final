package com.example.sfsep.edss.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sfsep.R
import com.example.sfsep.edss.EdssPfDetailActivity
import com.example.sfsep.edss.model.EDSSManager
import kotlinx.android.synthetic.main.cell_edss.view.*

class EdssMainTableAdapter(private val context: Context): RecyclerView.Adapter<EdssMainTableAdapter.EdssViewHolder>() {

    inner class EdssViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var ui_pfTitle = itemView.ui_pfTitle
        var ui_pfImage = itemView.ui_pfImage

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            getChoosenPF(adapterPosition)
        }


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EdssViewHolder {
        // 1 - Charger la vue en XML
        val rootView = LayoutInflater.from(p0.context).inflate(R.layout.cell_edss, p0, false)

        // 2 - Créer un viewholder pour contrôler cette vue
        val holder = EdssViewHolder(rootView)

        // 3 - Retourner le viewholder

        return holder


    }

    override fun onBindViewHolder(holder: EdssViewHolder, raw: Int) {
        // 1 - Obtenir le nom du PF et l'image à afficher
        val pfName = EDSSManager.getPFStrings(context)[raw]
        val isAmbulatoryPF = (raw == 8)


        var pfImageId = EDSSManager.getDigitFileName(EDSSManager.pfArray[raw], isAmbulatoryPF)

        if (raw == 1 && EDSSManager.cerebellarX) {
            pfImageId = R.drawable.x
            EDSSManager.cerebellarPF = 0
        }

        // 2 - Envoyer dans le holder
        holder.ui_pfTitle.text = pfName
        holder.ui_pfImage.setImageResource(pfImageId)



    }

    override fun getItemCount(): Int {
        return EDSSManager.getPFStrings(context).size
    }

    fun getChoosenPF(index:Int) {
        val pf = EDSSManager.getPFStrings(context)[index]
        val pfArray = getPfArray(index)
        val intent = Intent(context, EdssPfDetailActivity::class.java)
        intent.putExtra("title", pf)
        intent.putExtra("pfArray", pfArray)
        intent.putExtra("pfIndex", index)
        context.startActivity(intent)
    }

    fun getPfArray(index:Int):Array<String> {
        when (index) {
            0 -> return context.resources.getStringArray(R.array.edss_pyramidal)
            1 -> return context.resources.getStringArray(R.array.edss_cerebelleux)
            2 -> return context.resources.getStringArray(R.array.edss_sensitif)
            3 -> return context.resources.getStringArray(R.array.edss_brainstem)
            4 -> return context.resources.getStringArray(R.array.edss_sphincters)
            5 -> return context.resources.getStringArray(R.array.edss_vision)
            6 -> return context.resources.getStringArray(R.array.edss_cognition)
            7 -> return context.resources.getStringArray(R.array.edss_other)
            8 -> return context.resources.getStringArray(R.array.edss_ambulation)
        }
        return context.resources.getStringArray(R.array.edss_ambulation)
    }

}