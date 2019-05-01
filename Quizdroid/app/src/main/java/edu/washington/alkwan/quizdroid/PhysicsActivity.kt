package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class PhysicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics)

        val button = findViewById<Button>(R.id.physicsBegin)
        button.setOnClickListener {
            val intent = Intent(this@PhysicsActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}