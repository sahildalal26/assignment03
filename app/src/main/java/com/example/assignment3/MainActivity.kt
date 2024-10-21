package com.example.assignment3

import GameAdapter
import GameViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GameAdapter
    private val gameViewModel: GameViewModel by viewModels()
    private val database = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with empty list, and provide two click listeners: one for viewing and one for editing
        adapter = GameAdapter(emptyList(), { game ->
            // On click for viewing game details
            val intent = Intent(this, DetailGameActivity::class.java).apply {
                putExtra("GAME_TITLE", game.title)
                putExtra("GAME_DESCRIPTION", game.description)
            }
            startActivity(intent)
        }, { game ->
            // On click for editing game
            val intent = Intent(this, EditGameActivity::class.java).apply {
                putExtra("GAME_ID", game.id)
                putExtra("GAME_TITLE", game.title)
                putExtra("GAME_DESCRIPTION", game.description)
                putExtra("GAME_RATING", game.rating)
                putExtra("GAME_DOWNLOADS", game.downloads)
            }
            startActivity(intent)
        },{ game ->
            database.child("games").child(game.id).removeValue().addOnCompleteListener {
                Toast.makeText(this, "Game deleted successfully", Toast.LENGTH_SHORT).show()
                finish() // Close the activity
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to delete game", Toast.LENGTH_SHORT).show()
            }
        }
            )
        recyclerView.adapter = adapter

        gameViewModel.games.observe(this) { games ->
            // Update the adapter with the new list of games
            adapter = GameAdapter(games, { game ->
                val intent = Intent(this, DetailGameActivity::class.java).apply {
                    putExtra("GAME_TITLE", game.title)
                    putExtra("GAME_DESCRIPTION", game.description)
                }
                startActivity(intent)
            }, { game ->
                val intent = Intent(this, EditGameActivity::class.java).apply {
                    putExtra("GAME_ID", game.id)
                    putExtra("GAME_TITLE", game.title)
                    putExtra("GAME_DESCRIPTION", game.description)
                    putExtra("GAME_RATING", game.rating)
                    putExtra("GAME_DOWNLOADS", game.downloads)
                }
                startActivity(intent)
            }, { game ->
                database.child("games").child(game.id).removeValue().addOnCompleteListener {
                    Toast.makeText(this, "Game deleted successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to delete game", Toast.LENGTH_SHORT).show()
                }
                }
                )
            recyclerView.adapter = adapter
        }



        // Add button click listener to navigate to AddGameActivity
        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            startActivity(Intent(this, AddGameActivity::class.java))
        }
    }
}
