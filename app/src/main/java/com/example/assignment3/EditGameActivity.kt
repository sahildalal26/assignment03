package com.example.assignment3

import Game
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditGameActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var ratingEditText: EditText
    private lateinit var downloadsEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var database: DatabaseReference

    private lateinit var gameId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_game)

        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        ratingEditText = findViewById(R.id.ratingEditText)
        downloadsEditText = findViewById(R.id.downloadsEditText)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)

        database = Firebase.database.reference

        // Get the game ID passed from the previous activity
        gameId = intent.getStringExtra("GAME_ID") ?: return

        // Load the game details from Firebase
        loadGameDetails()

        updateButton.setOnClickListener {
            updateGame()
        }

        deleteButton.setOnClickListener {
            deleteGame()
        }
    }

    private fun loadGameDetails() {
        database.child("games").child(gameId).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val game = dataSnapshot.getValue(Game::class.java)
                if (game != null) {
                    titleEditText.setText(game.title)
                    descriptionEditText.setText(game.description)
                    ratingEditText.setText(game.rating.toString())
                    downloadsEditText.setText(game.downloads.toString())
                }
            } else {
                Toast.makeText(this, "Game not found", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to load game details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateGame() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val ratingString = ratingEditText.text.toString()
        val downloadsString = downloadsEditText.text.toString()

        // Validate inputs
        if (title.isEmpty() || description.isEmpty() || ratingString.isEmpty() || downloadsString.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert the input values to the correct data types
        val rating = ratingString.toDoubleOrNull() ?: run {
            Toast.makeText(this, "Invalid rating", Toast.LENGTH_SHORT).show()
            return
        }

        val downloads = downloadsString.toIntOrNull() ?: run {
            Toast.makeText(this, "Invalid downloads", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a new Game object with the updated values
        val updatedGame = Game(gameId, title, description, rating, downloads)

        // Update the game in the database
        database.child("games").child(gameId).setValue(updatedGame).addOnCompleteListener {
            Toast.makeText(this, "Game updated successfully", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to update game", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteGame() {
        database.child("games").child(gameId).removeValue().addOnCompleteListener {
            Toast.makeText(this, "Game deleted successfully", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to delete game", Toast.LENGTH_SHORT).show()
        }
    }
}
