package com.example.foodpreferencessurvey

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodpreferencessurvey.R

class SurveyActivity : AppCompatActivity() {

    private lateinit var surveyLayout: LinearLayout
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        surveyLayout = findViewById(R.id.surveyLayout)
        submitButton = findViewById(R.id.submitButton)

        val surveyType = intent.getStringExtra("surveyType")
        if (surveyType != null) {
            if (surveyType == "Food Preferences") {
                populateFoodPreferencesSurvey()
            } else if (surveyType == "Dietary Habits") {
                populateDietaryHabitsSurvey()
            }
        }

        submitButton.setOnClickListener {
            if (validateSurvey()) {
                submitSurvey()
            }
        }
    }

    private fun populateFoodPreferencesSurvey() {
        addQuestionView("What is your favorite cuisine?", arrayOf("Chinese", "French", "Italian", "Indian", "Japanese", "Thai", "Turkish"))
        addQuestionView("How often do you eat out?", arrayOf("Never", "Rarely", "Sometimes", "Frequently"))
        addQuestionView("Do you prefer spicy food?", arrayOf("Yes", "No"))
        addQuestionView("Do you prefer vegetarian food?", arrayOf("Yes", "No"))
        addQuestionView("Do you like seafood?", arrayOf("Yes", "No"))
    }

    private fun populateDietaryHabitsSurvey() {
        addQuestionView("Are you vegetarian?", arrayOf("Yes", "No"))
        addQuestionView("Do you prefer organic food?", arrayOf("Yes", "No"))
        addQuestionView("Do you consume dairy products?", arrayOf("Yes", "No"))
        addQuestionView("Do you eat fast food?", arrayOf("Yes", "No"))
        addQuestionView("Do you have any food allergies?", arrayOf("Yes", "No"))
    }

    private fun addQuestionView(question: String, options: Array<String>) {
        val questionTextView = TextView(this)
        questionTextView.text = question
        surveyLayout.addView(questionTextView)

        val radioGroup = RadioGroup(this)
        for (option in options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }
        surveyLayout.addView(radioGroup)
    }

    private fun validateSurvey(): Boolean {
        for (i in 0 until surveyLayout.childCount) {
            val view = surveyLayout.getChildAt(i)
            if (view is RadioGroup) {
                if (view.checkedRadioButtonId == -1) {
                    return false
                }
            }
        }
        return true
    }

    private fun submitSurvey() {
        val answersTextView = TextView(this)
        answersTextView.text = "Survey Results:\n\n"

        for (i in 0 until surveyLayout.childCount) {
            val view = surveyLayout.getChildAt(i)
            if (view is RadioGroup) {
                val selectedRadioButtonId = view.checkedRadioButtonId
                if (selectedRadioButtonId != -1) {
                    val radioButton = findViewById<RadioButton>(selectedRadioButtonId)
                    val question = (surveyLayout.getChildAt(i - 1) as TextView).text.toString()
                    val answer = radioButton.text.toString()
                    answersTextView.append("$question: $answer\n")
                }
            }
        }

        surveyLayout.addView(answersTextView)
    }
}
