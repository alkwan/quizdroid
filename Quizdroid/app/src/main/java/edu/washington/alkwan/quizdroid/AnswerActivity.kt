package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val yourAnswer = findViewById<TextView>(R.id.yourAnswer)
        var answer : String

        intent.extras.apply {
            answer = this!!.getString("answer")!!
            val message = "Your answer: $answer"
            yourAnswer.text = message
        }

        val numCorrect = findViewById<TextView>(R.id.numberCorrect)
        if (answer == "Download 'But You Seem Fine' from the Google Play Store!!") {
            val message = "You got 1 out of 1 correct!"
            numCorrect.text = message
        } else {
            val message = "You got 0 out of 1 correct!"
            numCorrect.text = message
        }

        val button = findViewById<Button>(R.id.buttonFinish)
        button.setOnClickListener {
            val intent = Intent(this@AnswerActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
