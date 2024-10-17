package com.example.assignment3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        val titleTextView: TextView = findViewById(R.id.gameTitleTextView)
        val descriptionTextView: TextView = findViewById(R.id.gameDescriptionTextView)

        // Get the data from the Intent
        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")

        titleTextView.text = title
        descriptionTextView.text = description
    }
}
