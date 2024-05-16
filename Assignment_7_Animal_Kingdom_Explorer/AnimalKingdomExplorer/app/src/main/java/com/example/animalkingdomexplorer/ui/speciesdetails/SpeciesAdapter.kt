package com.example.animalkingdomexplorer.ui.speciesdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.model.Species

class SpeciesAdapter : ListAdapter<Species, SpeciesAdapter.SpeciesViewHolder>(SpeciesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_species, parent, false)
        return SpeciesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val currentSpecies = getItem(position)
        holder.bind(currentSpecies)
    }

    inner class SpeciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewSpeciesName)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewSpeciesDescription)

        fun bind(species: Species) {
            nameTextView.text = species.name
            descriptionTextView.text = species.description
        }
    }

    class SpeciesDiffCallback : DiffUtil.ItemCallback<Species>() {
        override fun areItemsTheSame(oldItem: Species, newItem: Species): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Species, newItem: Species): Boolean {
            return oldItem == newItem
        }
    }
}
