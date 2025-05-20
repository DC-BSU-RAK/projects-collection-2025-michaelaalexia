package com.example.complicolors

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GeneratorSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generator_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //VARIABLES
        // color buttons
        val redButton : ImageButton = findViewById(R.id.redButton)
        val yellowButton : ImageButton = findViewById(R.id.yellowButton)
        val blueButton : ImageButton = findViewById(R.id.blueButton)
        val greenButton : ImageButton = findViewById(R.id.greenButton)
        val purpleButton : ImageButton = findViewById(R.id.purpleButton)
        val orangeButton : ImageButton = findViewById(R.id.orangeButton)
        val pinkButton : ImageButton = findViewById(R.id.pinkButton)
        val brownButton : ImageButton = findViewById(R.id.brownButton)
        var selectedColorName : String? = null // default setting of color before user makes selection

        // main buttons and text
        val generateButton : Button = findViewById(R.id.generateButton)
        val backButton : Button = findViewById(R.id.backButton)
        val selected : TextView = findViewById(R.id.selectedColor) // selected color text

        // spinner
        val pairingSpinner : Spinner = findViewById(R.id.pairingSpinner)
        val pairing = arrayOf("Complementary", "Analogous", "Both")

        // BUTTONS AND SPINNER FUNCTIONS
        // assigning array to spinner
        pairingSpinner.adapter = ArrayAdapter(this@GeneratorSettings, android.R.layout.simple_spinner_dropdown_item, pairing)


        // to return to home page
        backButton.setOnClickListener{
            val home = Intent(this, Home::class.java)
            startActivity(home)
        }

        // to go to results page
        generateButton.setOnClickListener{
            if (selectedColorName != null) {
                val selectedPair = pairingSpinner.selectedItem.toString() // spinner settings
                val resultsAct = Intent(this, GeneratorResults::class.java)
                resultsAct.putExtra("selectedColor", selectedColorName) // user selection will be generated in the next activity
                resultsAct.putExtra("selectedPair", selectedPair) // selected pairing will be sent to the next activity and results will be applied
                startActivity(resultsAct)
            } else {
                Toast.makeText(this, "Please select a color first!", Toast.LENGTH_SHORT).show() // toast message will show if color not selected
            }
        }

        // show selected w/ setOnClickListener
        redButton.setOnClickListener{
            selected.text = "Red"
            selectedColorName = "Red"
        }

        yellowButton.setOnClickListener{
            selected.text = "Yellow"
            selectedColorName = "Yellow"
        }

        blueButton.setOnClickListener{
            selected.text = "Blue"
            selectedColorName = "Blue"
        }

        greenButton.setOnClickListener{
            selected.text = "Green"
            selectedColorName = "Green"
        }

        orangeButton.setOnClickListener{
            selected.text = "Orange"
            selectedColorName = "Orange"
        }

        purpleButton.setOnClickListener{
            selected.text = "Purple"
            selectedColorName = "Purple"
        }

        pinkButton.setOnClickListener{
            selected.text = "Pink"
            selectedColorName = "Pink"
        }

        brownButton.setOnClickListener{
            selected.text = "Brown"
            selectedColorName = "Brown"
        }
    }
}