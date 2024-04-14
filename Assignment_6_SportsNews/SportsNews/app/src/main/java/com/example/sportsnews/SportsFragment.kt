package com.example.sportsnews

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SportsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sportAdapter: SportAdapter
    private lateinit var fabAdd: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sports    , container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        fabAdd = view.findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            showAddSportDialog()
        }

        val sports = mutableListOf(
            Sport("Measure", "Car racing", "sports are ones where the goal is to min/max a certain value. The most common measure sports are races. You win a measured sport by running the fastest, lifting the most, throwing the farthest, etc.\n The objective is to get the record, and there is no “best” performance or ceiling other than the current world record."),
            Sport("Precision", "cornhole", "Play sports are hard to define on their own and not in contrast to the other four sports categories. Most Play sports have more complex rules than Measure, Precision, Spectacle, or Combat sports. Play sports are often, but not always team sports — both singles and doubles tennis are Play sports."),
            Sport("Spectacle", "cheerleading", "Play sports are hard to define on their own and not in contrast to the other four sports categories. Most Play sports have more complex rules than Measure, Precision, Spectacle, or Combat sports. Play sports are often, but not always team sports — both singles and doubles tennis are Play sports."),
            Sport("Combat", "Boxing", "Play sports are hard to define on their own and not in contrast to the other four sports categories. Most Play sports have more complex rules than Measure, Precision, Spectacle, or Combat sports. Play sports are often, but not always team sports — both singles and doubles tennis are Play sports."),
            Sport("Play", "football", "Play sports are hard to define on their own and not in contrast to the other four sports categories. Most Play sports have more complex rules than Measure, Precision, Spectacle, or Combat sports. Play sports are often, but not always team sports — both singles and doubles tennis are Play sports.")
        )

        sportAdapter = SportAdapter(sports)
        recyclerView.adapter = sportAdapter

        return view
    }

    private fun showAddSportDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_sport, null)

        val editTextType = dialogView.findViewById<EditText>(R.id.editTextType)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextInstruction = dialogView.findViewById<EditText>(R.id.editTextInstruction)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Sport")
            .setPositiveButton("Add") { dialog, _ ->
                val type = editTextType.text.toString().trim()
                val name = editTextName.text.toString().trim()
                val instruction = editTextInstruction.text.toString().trim()

                if (type.isNotEmpty() && name.isNotEmpty() && instruction.isNotEmpty()) {
                    val newSport = Sport(type, name, instruction)
                    addSport(newSport)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addSport(newSport: Sport) {
        sportAdapter.addItem(newSport)
    }
}