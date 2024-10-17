// MainActivity.kt
package com.example.assignment3

import GameAdapter
import GameViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GameAdapter
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with an empty list and a click listener
        adapter = GameAdapter(emptyList()) { game ->
            val intent = Intent(this, DetailGameActivity::class.java)
            intent.putExtra("gameId", game.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Observe the game list
        gameViewModel.games.observe(this) { games ->
            adapter = GameAdapter(games) { game ->
                val intent = Intent(this, DetailGameActivity::class.java)
                intent.putExtra("gameId", game.id)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }

        // Add button click listener
        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            startActivity(Intent(this, AddGameActivity::class.java))
        }

        // Edit button click listener
        findViewById<Button>(R.id.buttonEdit).setOnClickListener {
            // Here you can implement your logic to pass the selected game's ID for editing
            // For example, you might want to show a dialog to select which game to edit
            // This is a placeholder for demonstration purposes
            startActivity(Intent(this, EditGameActivity::class.java))
        }

        // Delete button click listener (currently redirects to DetailGameActivity)
        findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            // Here you might want to show a dialog to select which game to delete
            // This is a placeholder for demonstration purposes
            startActivity(Intent(this, DetailGameActivity::class.java))
        }
    }
}
