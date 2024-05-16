package com.example.animalkingdomexplorer.ui.animaldetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorer.AnimalKingdomApplication
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AnimalDetailsFragment : Fragment() {

    private val newAnimalActivityRequestCode = 1
    private val animalViewModel: AnimalViewModel by viewModels {
        AnimalViewModelFactory((requireActivity().application as AnimalKingdomApplication).animalRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal_details, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = AnimalAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        animalViewModel.allAnimals.observe(viewLifecycleOwner) { animals ->
            animals?.let { adapter.submitList(it) }
        }

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddAnimal)
        fab.setOnClickListener {
            showAddAnimalDialog()
        }

        return view
    }

    private fun showAddAnimalDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_animal, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextHabitat = dialogView.findViewById<EditText>(R.id.editTextHabitat)
        val editTextDiet = dialogView.findViewById<EditText>(R.id.editTextDiet)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Animal")
            .setPositiveButton("Add") { dialog, _ ->
                val name = editTextName.text.toString()
                val habitat = editTextHabitat.text.toString()
                val diet = editTextDiet.text.toString()

                if (name.isNotEmpty() && habitat.isNotEmpty() && diet.isNotEmpty()) {
                    val animal = Animal(0, name, habitat, diet)
                    animalViewModel.insert(animal)
                } else {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}

