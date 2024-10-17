// AddGameActivity.kt
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

class AddGameActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var ratingEditText: EditText
    private lateinit var downloadsEditText: EditText
    private lateinit var addButton: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)

        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        ratingEditText = findViewById(R.id.ratingEditText)
        downloadsEditText = findViewById(R.id.downloadsEditText)
        addButton = findViewById(R.id.addButton)

        database = FirebaseDatabase.getInstance().getReference("games")

        addButton.setOnClickListener {
            addGame()
        }
    }

    private fun addGame() {
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

        // Create a new Game object with the input values
        val id = database.push().key ?: return // Generate a unique ID for the game
        val game = Game(id, title, description, rating, downloads)

        // Add the game to the database
        database.child(id).setValue(game).addOnCompleteListener {
            Toast.makeText(this, "Game added successfully", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to add game", Toast.LENGTH_SHORT).show()
        }
    }
}
