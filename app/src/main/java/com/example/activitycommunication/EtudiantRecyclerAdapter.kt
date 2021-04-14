package com.example.activitycommunication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EtudiantRecyclerAdapter(
    private val etudiants: List<Etudiant>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<EtudiantRecyclerAdapter.EtudiantViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtudiantViewHolder {
        return EtudiantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_etudiant, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return etudiants.size;
    }

    override fun onBindViewHolder(holder: EtudiantViewHolder, position: Int) {
            holder.bind(etudiants.get(position))
        }


    inner class EtudiantViewHolder constructor(itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val etudiant_name = itemView.findViewById<TextView>(R.id.text_view_full_name)

        fun bind(etudiant: Etudiant){
            etudiant_name.text = etudiant.nom + " " + etudiant.prenom
        }

        init {
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }
}