package com.example.assignment3

import Game
import GameAdapter
import GameViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var gameAdapter: GameAdapter
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        gameAdapter = GameAdapter(emptyList())
        recyclerView.adapter = gameAdapter

        // Load games from the database
        CoroutineScope(Dispatchers.IO).launch {
            val games = viewModel.getAllGames()
            runOnUiThread {
                gameAdapter = GameAdapter(games)
                recyclerView.adapter = gameAdapter
            }
        }

        // Sample data
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.insertGame(Game(1, "The Witcher 3", "An open-world RPG set in a fantasy universe."))
            viewModel.insertGame(Game(2, "Stardew Valley", "A farming simulation game."))
            viewModel.insertGame(Game(3, "Dark Souls", "An action RPG known for its difficulty."))
        }
    }
}
