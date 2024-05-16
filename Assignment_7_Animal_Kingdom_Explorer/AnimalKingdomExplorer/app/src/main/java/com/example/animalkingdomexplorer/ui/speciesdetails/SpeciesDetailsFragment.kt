package com.example.animalkingdomexplorer.ui.speciesdetails

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
import com.example.animalkingdomexplorer.data.model.Species
import com.google.android.material.floatingactionbutton.FloatingActionButton
class SpeciesDetailsFragment : Fragment() {


    private val newSpeciesActivityRequestCode = 1
    private val speciesViewModel: SpeciesViewModel by viewModels {
        SpeciesViewModelFactory((requireActivity().application as AnimalKingdomApplication).speciesRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_species_details, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = SpeciesAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        speciesViewModel.allSpecies.observe(viewLifecycleOwner) { species ->
            species?.let { adapter.submitList(it) }
        }

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddSpecies)
        fab.setOnClickListener {
            showAddSpeciesDialog()
        }

        return view
    }

    private fun showAddSpeciesDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_species, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextSpeciesName)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextSpeciesDescription)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Species")
            .setPositiveButton("Add") { dialog, _ ->
                val name = editTextName.text.toString()
                val description = editTextDescription.text.toString()

                if (name.isNotEmpty() && description.isNotEmpty()) {
                    val species = Species(0, name, description)
                    speciesViewModel.insert(species)
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
