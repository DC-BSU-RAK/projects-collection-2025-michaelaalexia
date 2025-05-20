package com.example.takeyourcross

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class JournalLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_journal_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // variables : buttons and textboxes
        val returnButton : Button = findViewById(R.id.returnButton)
        val saveButton : Button = findViewById(R.id.saveButton)
        val showEntryButton : Button = findViewById(R.id.showEntryButton)
        val dateEntry : EditText = findViewById(R.id.dateEntry)
        val passageEntry : EditText = findViewById(R.id.passageEntry)
        val takeawayEntry : EditText = findViewById(R.id.takeawayEntry)

        // to return to home screen
        returnButton.setOnClickListener{
            val goToHome = Intent(this, Home::class.java)
            startActivity(goToHome)
        }

        // to show entry
        showEntryButton.setOnClickListener{
            val showEntry = Intent(this, SavedEntries::class.java)
            startActivity(showEntry)
        }

        // save button functions w/ setOnClickListener
        saveButton.setOnClickListener{
            val date = dateEntry.text.toString()
            val pass = passageEntry.text.toString()
            val takeaway = takeawayEntry.text.toString()

            val sharedPreference = getSharedPreferences("JournalEntries", MODE_PRIVATE)
            with(sharedPreference.edit()){
                putString("date", date)
                putString("passage", pass)
                putString("takeaway", takeaway)
                apply()
            }
            // toast message to indicate that user entry has been saved
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
        }

    }
}