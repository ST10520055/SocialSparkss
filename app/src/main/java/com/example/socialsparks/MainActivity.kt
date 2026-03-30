package com.example.socialsparks // Make sure this matches your project name

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Initialize the Views
        val etTimeOfDay = findViewById<EditText>(R.id.edtTimeOfDay)
        val btnSuggest = findViewById<Button>(R.id.btnSuggest)
        val tvSuggestion = findViewById<TextView>(R.id.tvSuggestion)
        val btnReset = findViewById<Button>(R.id.btnReset)

        // 2. Set up the Suggestion Logic
        btnSuggest.setOnClickListener {
            val userInput = etTimeOfDay.text.toString().trim()

            // Handle Empty Input Error
            if (userInput.isEmpty()) {
                etTimeOfDay.error = "Please enter a time of day to get a spark!"
                tvSuggestion.text = "I'm ready when you are! Try typing 'Morning'."
                return@setOnClickListener
            }

            // Determine the Spark based on input
            val sparkMessage = when {
                userInput.equals("Morning", ignoreCase = true) ->
                    "☀️ Send a 'Good morning' text to a family member."

                userInput.equals("Mid-morning", ignoreCase = true) ->
                    "☕ Reach out to a colleague with a quick 'Thank you'."

                userInput.equals("Afternoon", ignoreCase = true) ->
                    "😎 Share a funny meme or interesting link with a friend."

                userInput.contains("Snack", ignoreCase = true) ->
                    "🥨 Afternoon Snack Time: Send a quick 'thinking of you' message."

                userInput.equals("Dinner", ignoreCase = true) ->
                    "🍽️ Call a friend or relative for a 5-minute catch-up."

                userInput.contains("Night", ignoreCase = true) || userInput.contains("Evening", ignoreCase = true) ->
                    "🌙 Leave a thoughtful comment on a friend's post."

                // Constructive Feedback for unrecognized input
                else -> "Hmm, I don't recognize '$userInput'. Keep going. Try 'Morning', 'Afternoon', or 'Dinner'!"
            }

            tvSuggestion.text = sparkMessage
        }

        // 3. Reset Functionality
        btnReset.setOnClickListener {
            etTimeOfDay.text.clear()
            tvSuggestion.text = "Your spark will appear here"
            etTimeOfDay.error = null // Clear the error message
        }
    }
}
