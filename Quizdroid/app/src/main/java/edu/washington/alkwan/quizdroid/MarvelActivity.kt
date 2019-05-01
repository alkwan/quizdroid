package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MarvelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel)

        val button = findViewById<Button>(R.id.marvelBegin)
        button.setOnClickListener {
            val intent = Intent(this@MarvelActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}