package com.example.dynamictable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var editTextResource: EditText
    private lateinit var editTextLocation: EditText
    private lateinit var editTextType: EditText

    private lateinit var addButton: Button
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextResource = findViewById(R.id.editTextResource)
        editTextLocation = findViewById(R.id.editTextLocation)
        editTextType = findViewById(R.id.editTextType)

        addButton = findViewById(R.id.addButton)
        tableLayout = findViewById(R.id.tableLayout)

        val headerRow = TableRow(this)
        headerRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)

        val resourceHeader = createHeaderCell("Resource Name")
        val locationHeader = createHeaderCell("Location")
        val typeHeader = createHeaderCell("Type")


        headerRow.addView(resourceHeader)
        headerRow.addView(locationHeader)
        headerRow.addView(typeHeader)



        tableLayout.addView(headerRow)

    addRowToTable("River gangs", "varsneila india", "river")

        addButton.setOnClickListener {
            val resource = editTextResource.text.toString()
            val location = editTextLocation.text.toString()
            val type = editTextType.text.toString()

            addRowToTable(resource, location, type)
            editTextResource.text.clear()
            editTextLocation.text.clear()
            editTextType.text.clear()

        }
    }


    private fun addRowToTable(resource: String, location: String, type: String) {
        val dataRow = TableRow(this)
        dataRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        dataRow.setBackgroundResource(R.drawable.divider)

        val resourceText = createDataCell(resource)
        val locationText = createDataCell(location)
        val typeText = createDataCell(type)

        dataRow.addView(resourceText)
        dataRow.addView(locationText)
        dataRow.addView(typeText)


        tableLayout.addView(dataRow)
    }

    private fun createHeaderCell(text: String): TextView {
        val textView = TextView(this)
        textView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        textView.text = text
        textView.setBackgroundResource(R.drawable.cell_background)
        textView.setPadding(5, 5, 5, 5)
        textView.setTextColor(resources.getColor(R.color.black))
        textView.gravity = Gravity.CENTER
        return textView
    }

    private fun createDataCell(text: String): TextView {
        val textView = TextView(this)
        textView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        textView.text = text
        textView.setBackgroundResource(R.drawable.cell_background)
        textView.setPadding(5, 5, 5, 5)
        textView.setTextColor(resources.getColor(R.color.black))
        textView.gravity = Gravity.CENTER
        return textView
    }


}