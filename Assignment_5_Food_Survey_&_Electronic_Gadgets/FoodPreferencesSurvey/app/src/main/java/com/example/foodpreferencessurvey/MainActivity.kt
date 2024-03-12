package com.example.foodpreferencessurvey


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foodPreferencesButton: Button = findViewById(R.id.foodPreferencesButton)
        foodPreferencesButton.setOnClickListener {
            startSurveyActivity("Food Preferences")
        }

        val dietaryHabitsButton: Button = findViewById(R.id.dietaryHabitsButton)
        dietaryHabitsButton.setOnClickListener {
            startSurveyActivity("Dietary Habits")
        }
    }

    private fun startSurveyActivity(surveyType: String) {
        val intent = Intent(this, SurveyActivity::class.java)
        intent.putExtra("surveyType", surveyType)
        startActivity(intent)
    }
}
