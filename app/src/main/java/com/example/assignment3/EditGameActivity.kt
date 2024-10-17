package com.example.assignment3

import Game
import GameViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditGameActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText
    private var gameId: Int = 0 // Assume you receive this from the intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_game)

        titleInput = findViewById(R.id.titleInput)
        descriptionInput = findViewById(R.id.descriptionInput)

        // Get the data from the Intent
        titleInput.setText(intent.getStringExtra("TITLE"))
        descriptionInput.setText(intent.getStringExtra("DESCRIPTION"))

        val updateButton: Button = findViewById(R.id.updateButton)
        updateButton.setOnClickListener {
            updateGame()
        }
    }

    private fun updateGame() {
        val title = titleInput.text.toString()
        val description = descriptionInput.text.toString()

        // Update the game in the database
        val updatedGame = Game(gameId, title, description)
        val viewModel = GameViewModel()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.updateGame(updatedGame)
            finish() // Close the activity after updating
        }
    }
}
