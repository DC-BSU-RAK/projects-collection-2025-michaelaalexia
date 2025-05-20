package com.example.complicolors

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
        // variables for image buttons
        val generatorButton : ImageButton = findViewById(R.id.generatorButton)
        val instructionButton : ImageButton = findViewById(R.id.instructionButton)

        // this takes the user to the generator activity
        generatorButton.setOnClickListener{
            val goToGenerator = Intent(this, GeneratorSettings::class.java)
            startActivity(goToGenerator)
        }

        // this starts the instructions pop-up
        instructionButton.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val instrPopUp = inflater.inflate(R.layout.activity_instruction, null) // access pop-up XML

            // pop-up width and height
            val w = 966
            val h = 1998

            val instrWindow = PopupWindow(instrPopUp, w, h, true)
            instrWindow.showAtLocation(instrPopUp, Gravity.NO_GRAVITY, 50, 150)

            // closes pop-up once user clicks the "close" button
            val closeButton : Button = instrPopUp.findViewById(R.id.closeButton)
            closeButton.setOnClickListener{
                instrWindow.dismiss()
            }
        }
    }
}