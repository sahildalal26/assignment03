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

class AddGameActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)

        titleInput = findViewById(R.id.titleInput)
        descriptionInput = findViewById(R.id.descriptionInput)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveGame()
        }
    }

    private fun saveGame() {
        val title = titleInput.text.toString()
        val description = descriptionInput.text.toString()

        // You can implement a way to generate unique IDs for games
        val newGame = Game(0, title, description)

        // Save the new game to the database
        val viewModel = GameViewModel()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.insertGame(newGame)
            finish() // Close the activity after saving
        }
    }
}
