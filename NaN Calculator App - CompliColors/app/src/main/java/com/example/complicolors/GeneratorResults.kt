package com.example.complicolors

import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GeneratorResults : AppCompatActivity() {
    // function to work out the color pairings
    // the color generator will work based on the pairings listed here
    private fun getPairs(color: String, pairing: String): List<String> {
        return when (pairing.lowercase()) {
            "complementary" -> listOf(when (color.lowercase()) {
                "red" -> "green"
                "blue" -> "orange"
                "yellow" -> "purple"
                "green" -> "red"
                "orange" -> "blue"
                "purple" -> "yellow"
                "pink" -> "green"
                "brown" -> "blue"
                else -> "gray"
            })
            "analogous", "both" -> when (color.lowercase()) {
                "red" -> listOf("pink", "orange")
                "blue" -> listOf("purple", "green")
                "yellow" -> listOf("orange", "green")
                "green" -> listOf("yellow", "blue")
                "orange" -> listOf("red", "yellow")
                "purple" -> listOf("blue", "pink")
                "pink" -> listOf("red", "purple")
                "brown" -> listOf("orange", "red")
                else -> listOf("gray", "gray")
            }
            else -> listOf("gray")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generator_results)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // return button
        val returnToGenerator : Button = findViewById(R.id.returnButton)
        returnToGenerator.setOnClickListener{
            val backToGen = Intent(this, GeneratorSettings::class.java)
            startActivity(backToGen)
        }

        // selected color image
        val selectedColorName = intent.getStringExtra("selectedColor") ?: "Red"
        val selectedImageView: ImageView = findViewById(R.id.selectedImageView)

        val drawableId = when (selectedColorName.lowercase()) {
            "red" -> R.drawable.red
            "blue" -> R.drawable.blue
            "yellow" -> R.drawable.yellow
            "green" -> R.drawable.green
            "orange" -> R.drawable.orange
            "purple" -> R.drawable.purple
            "pink" -> R.drawable.pink
            "brown" -> R.drawable.brown
            else -> R.drawable.gray
        }

        selectedImageView.setImageResource(drawableId) // selected color will show in this image view

        // complimentary pairings
        val selectedPair = intent.getStringExtra("selectedPair") ?: "Complimentary"
        val complimentImageView : ImageView = findViewById(R.id.complimentImageView)

        val complimentaryColor = getPairs(selectedColorName, "complementary")

        // analogous pairings
        val anaImageView1 : ImageView = findViewById(R.id.anaImageView1)
        val anaImageView2 : ImageView = findViewById(R.id.anaImageView2)

        val analogousColor = getPairs(selectedColorName, "analogous")

        val compliIMG = when ( complimentaryColor.firstOrNull()?.lowercase() ?: "gray"){
            "red" -> R.drawable.red
            "blue" -> R.drawable.blue
            "yellow" -> R.drawable.yellow
            "green" -> R.drawable.green
            "orange" -> R.drawable.orange
            "purple" -> R.drawable.purple
            "pink" -> R.drawable.pink
            "brown" -> R.drawable.brown
            else -> R.drawable.gray
        } // access images

        if (selectedPair.equals("Complementary", ignoreCase = true) || selectedPair.equals("Both", ignoreCase = true)) {
            complimentImageView.setImageResource(compliIMG)  // referenced image colors will be put in complimentary image view
        } else {
            complimentImageView.setImageDrawable(null)
        }

        // analogous image pairings
        if (selectedPair.equals("Analogous", ignoreCase = true) || selectedPair.equals("Both", ignoreCase = true)){
            val anaColor1 = analogousColor.getOrNull(0)?.lowercase() ?: "gray"
            val anaColor2 = analogousColor.getOrNull(1)?.lowercase() ?: "gray"

            val anaIMG1 = when (anaColor1) {
                "red" -> R.drawable.red
                "blue" -> R.drawable.blue
                "yellow" -> R.drawable.yellow
                "green" -> R.drawable.green
                "orange" -> R.drawable.orange
                "purple" -> R.drawable.purple
                "pink" -> R.drawable.pink
                "brown" -> R.drawable.brown
                else -> R.drawable.gray
            }

            val anaIMG2 = when (anaColor2) {
                "red" -> R.drawable.red
                "blue" -> R.drawable.blue
                "yellow" -> R.drawable.yellow
                "green" -> R.drawable.green
                "orange" -> R.drawable.orange
                "purple" -> R.drawable.purple
                "pink" -> R.drawable.pink
                "brown" -> R.drawable.brown
                else -> R.drawable.gray
            }
            anaImageView1.setImageResource(anaIMG1)
            anaImageView2.setImageResource(anaIMG2) // referenced images will be put into these analogous image views
        }
    }
}