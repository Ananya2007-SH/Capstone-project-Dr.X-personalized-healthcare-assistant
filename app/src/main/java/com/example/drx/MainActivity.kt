package com.example.drx

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sideEffectsImageView: ImageView = findViewById(R.id.imageView2)
        val sideEffectsTextView: TextView = findViewById(R.id.textView2)
        sideEffectsImageView?.setOnClickListener {
            navigateToSideEffectsActivity()
        }
        sideEffectsTextView?.setOnClickListener {
            navigateToSideEffectsActivity()
        }

        val compareDrugsImageView: ImageView = findViewById(R.id.imageView3)
        val compareDrugsTextView: TextView = findViewById(R.id.compare_drugs_text_view)
        compareDrugsImageView?.setOnClickListener {
            navigateToCompareDrugsActivity()
        }
        compareDrugsTextView?.setOnClickListener {
            navigateToCompareDrugsActivity()
        }

        val drugDosageImageView: ImageView = findViewById(R.id.imageView4)
        val drugDosageTextView: TextView = findViewById(R.id.drug_dosage_text_view)
        drugDosageImageView?.setOnClickListener {
            navigateTodrugDosageActivity()
        }
        drugDosageTextView?.setOnClickListener {
            navigateTodrugDosageActivity()
        }
    }

    private fun navigateToSideEffectsActivity() {
        val intent = Intent(this, SideEffectsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToCompareDrugsActivity() {
        val intent = Intent(this, CompareDrugsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateTodrugDosageActivity() {
        val intent = Intent(this, DrugDosageActivity::class.java)
        startActivity(intent)
    }

}