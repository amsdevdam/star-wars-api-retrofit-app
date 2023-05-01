package com.example.starwarsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.model.Planet

class PlanetsAdapter : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {
    private var planets = emptyList<Planet>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.planet_name_text_view)
        val terrainTv: TextView = itemView.findViewById(R.id.planet_terrain_text_view)
        val surfaceWaterTv: TextView = itemView.findViewById(R.id.planet_surface_water_text_view)
        val climateTv: TextView = itemView.findViewById(R.id.planet_climate_text_view)
        val gravityTv: TextView = itemView.findViewById(R.id.planet_gravity_text_view)
        val populationTv: TextView = itemView.findViewById(R.id.planet_population_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.planet_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return planets.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planet = planets[position]
        holder.nameTv.text = planet.name
        holder.terrainTv.text = String.format(
            holder.itemView.context.getString(R.string.planet_terrain_text),
            planet.terrain
        )
        holder.gravityTv.text = String.format(
            holder.itemView.context.getString(R.string.planet_gravity_text),
            planet.gravity
        )
        holder.surfaceWaterTv.text = String.format(
            holder.itemView.context.getString(R.string.planet_surface_water_text),
            planet.surface_water
        )
        holder.climateTv.text = String.format(
            holder.itemView.context.getString(R.string.planet_climate_text),
            planet.climate
        )
        holder.populationTv.text = String.format(
            holder.itemView.context.getString(R.string.planet_population_text),
            planet.population
        )
    }

    fun updateDataSet(planets : List<Planet>) {
        this.planets = planets
        notifyDataSetChanged()
    }
}