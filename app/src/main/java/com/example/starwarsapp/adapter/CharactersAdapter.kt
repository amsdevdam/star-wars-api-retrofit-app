package com.example.starwarsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.model.Character

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    private var characters = emptyList<Character>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.character_name_text_view)
        val speciesTv: TextView = itemView.findViewById(R.id.character_species_text_view)
        val birthYearTv: TextView = itemView.findViewById(R.id.character_birth_year_text_view)
        val genderTv: TextView = itemView.findViewById(R.id.character_gender_text_view)
        val homeWorldTv: TextView = itemView.findViewById(R.id.character_home_world_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.characters_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.nameTv.text = character.name
        var speciesText = ""
        character.species.forEach {
            speciesText += it.plus(", ")
        }
        speciesText = speciesText.dropLast(2)
        holder.speciesTv.text = String.format(holder.itemView.context.getString(R.string.character_species_text), speciesText)
        holder.birthYearTv.text = String.format(holder.itemView.context.getString(R.string.character_birth_year_text), character.birth_year)
        holder.genderTv.text = String.format(holder.itemView.context.getString(R.string.character_gender_text), character.gender)
        holder.homeWorldTv.text = String.format(holder.itemView.context.getString(R.string.character_home_world_text), character.homeworld)
    }

    fun updateDataSet(characters : List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }
}