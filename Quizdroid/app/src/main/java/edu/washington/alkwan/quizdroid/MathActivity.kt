package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)

        // enable back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val button = findViewById<Button>(R.id.mathBegin)
        button.setOnClickListener {
            // start the quiz
            val intent = Intent(this@MathActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}