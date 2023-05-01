package com.example.starwarsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.model.Species

class SpeciesAdapter : RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {
    private var species = emptyList<Species>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.species_name_text_view)
        val designationTv: TextView = itemView.findViewById(R.id.species_designation_text_view)
        val languageTv: TextView = itemView.findViewById(R.id.species_language_text_view)
        val averageLifeSpanTv: TextView =
            itemView.findViewById(R.id.species_average_lifespan_text_view)
        val homeWorldTv: TextView = itemView.findViewById(R.id.species_homeworld_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.species_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return species.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val species = species[position]
        holder.nameTv.text = species.name
        holder.designationTv.text = String.format(holder.itemView.context.getString(R.string.species_designation_text), species.designation)
        holder.homeWorldTv.text = String.format(holder.itemView.context.getString(R.string.species_home_world_text), species.homeworld)
        holder.averageLifeSpanTv.text = String.format(holder.itemView.context.getString(R.string.species_average_lifespan), species.average_lifespan)
        holder.languageTv.text = String.format(holder.itemView.context.getString(R.string.species_language_text), species.language)
    }

    fun updateDataSet(species: List<Species>) {
        this.species = species
        notifyDataSetChanged()
    }
}