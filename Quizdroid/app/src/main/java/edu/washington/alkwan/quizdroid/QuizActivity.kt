package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val button = findViewById<Button>(R.id.buttonSubmit)
        var answer = ""

        val answer01 = findViewById<RadioButton>(R.id.answer01)
        val answer02 = findViewById<RadioButton>(R.id.answer02)
        val answer03 = findViewById<RadioButton>(R.id.answer03)
        val answer04 = findViewById<RadioButton>(R.id.answer04)

        answer01.setOnClickListener {
            button.isEnabled = true
            answer = answer01.text.toString()
        }
        answer02.setOnClickListener {
            button.isEnabled = true
            answer = answer02.text.toString()
        }
        answer03.setOnClickListener {
            button.isEnabled = true
            answer = answer03.text.toString()
        }
        answer04.setOnClickListener {
            button.isEnabled = true
            answer = answer04.text.toString()
        }

        button.setOnClickListener {
            val intent = Intent(this@QuizActivity, AnswerActivity::class.java)
            intent.putExtra("answer", answer)
            startActivity(intent)
        }
    }
}