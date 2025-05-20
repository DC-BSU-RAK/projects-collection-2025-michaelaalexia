package com.example.takeyourcross

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class SavedEntries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saved_entries)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // VARIABLES : TEXT VIEWS
        val dateResult : TextView = findViewById(R.id.dateResult)
        val passageResult : TextView = findViewById(R.id.passageResult)
        val takeawayResult : TextView = findViewById(R.id.takeawayResult)

        // BUTTONS
        val backButton : Button = findViewById(R.id.backButton)
        val homeButton : Button = findViewById(R.id.homeButton)

        // button functions
        backButton.setOnClickListener{
            val backToJournalLayout = Intent(this, JournalLayout::class.java)
            startActivity(backToJournalLayout)
        }

        homeButton.setOnClickListener{
            val goHome = Intent(this, Home::class.java)
            startActivity(goHome)
        }

        // fetching data from the previous activity
        val sharedPreferences = getSharedPreferences("JournalEntries", MODE_PRIVATE)

        val date = sharedPreferences.getString("date", "________________")
        val pass = sharedPreferences.getString("passage", "________________")
        val takeaway = sharedPreferences.getString("takeaway", "________________")

        dateResult.text = date
        passageResult.text = pass
        takeawayResult.text = takeaway
    }
}