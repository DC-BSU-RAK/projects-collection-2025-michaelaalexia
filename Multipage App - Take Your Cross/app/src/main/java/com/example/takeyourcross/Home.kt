package com.example.takeyourcross

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // variables for home page buttons (three different image buttons)
        val journalButton : ImageButton = findViewById(R.id.journalButton)
        val aboutButton : ImageButton = findViewById(R.id.aboutButton)
        val saveEntButton : ImageButton = findViewById(R.id.saveEntButton)

        // starts up the pop-up for the About Button
        aboutButton.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val aboutPU = inflater.inflate(R.layout.activity_about, null) // to access pop-up XML

            // pop up dimensions
            val w = 966
            val h = 1998

            val aboutWindow = PopupWindow(aboutPU, w, h, true)
            aboutWindow.showAtLocation(aboutPU, Gravity.NO_GRAVITY, 50, 150) // where the pop=up shows

            // closes pop-up once user clicks "close:
            val closeButton : Button = aboutPU.findViewById(R.id.closeButton)
            closeButton.setOnClickListener{
                aboutWindow.dismiss()
            }
        }

        // access journal entry page
        journalButton.setOnClickListener{
            val goToEntry = Intent(this, JournalLayout::class.java)
            startActivity(goToEntry)
        }

        // access saved entries page
        saveEntButton.setOnClickListener{
            val goToSavedEnt = Intent(this, SavedEntries::class.java)
            startActivity(goToSavedEnt)
        }
    }
}