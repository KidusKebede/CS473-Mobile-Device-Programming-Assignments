package com.example.animalkingdomexplorer.ui.animaldetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal

class AnimalAdapter : ListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(AnimalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val currentAnimal = getItem(position)
        holder.bind(currentAnimal)
    }

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val habitatTextView: TextView = itemView.findViewById(R.id.textViewHabitat)
        private val dietTextView: TextView = itemView.findViewById(R.id.textViewDiet)

        fun bind(animal: Animal) {
            nameTextView.text = animal.name
            habitatTextView.text = animal.habitat
            dietTextView.text = animal.diet
        }
    }

    class AnimalDiffCallback : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }
}
