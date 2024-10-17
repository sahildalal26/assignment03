package com.example.assignment3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailGameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_game)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        val title = intent.getStringExtra("GAME_TITLE")
        val description = intent.getStringExtra("GAME_DESCRIPTION")

        titleTextView.text = title
        descriptionTextView.text = description
    }
}
