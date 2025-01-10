package com.example.drx

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SideEffectsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        try {
            setContentView(R.layout.side_effects_layout)
            setSupportActionBar(findViewById(R.id.toolbar))
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            findViewById<TextView>(R.id.toolbar_title).text = "Drug Side Effects"
            val inputTextView: TextInputEditText = findViewById(R.id.input_text_view)
            val searchButton: Button = findViewById(R.id.search)

            searchButton.setOnClickListener {
                findViewById<LinearLayout>(R.id.loading_layout).visibility = View.VISIBLE
                getDrugSideEffectsInfo(inputTextView.text.toString())
            }
        } catch (e: Exception) {
            TODO("Not yet implemented")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun getDrugSideEffectsInfo(searchString: String) {
        val updatedSearchString = "What are the side effects of $searchString?"
        val model: GeminiPro = GeminiPro()
        model.getResponse(updatedSearchString, object:ResponseCallback {
            override fun onResponse(response: String) {
                findViewById<LinearLayout>(R.id.loading_layout).visibility = View.GONE
                val sideEffectsTextView = findViewById<TextView>(R.id.drug_side_effects_info)
                sideEffectsTextView.text = "Side effects of " + searchString + " :\n\n" + response
                sideEffectsTextView.visibility = View.VISIBLE
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            }

            override fun onError(throwable: Throwable) {
                Log.d("Failed to get response: ", ""+throwable.message)
            }
        })
    }
}