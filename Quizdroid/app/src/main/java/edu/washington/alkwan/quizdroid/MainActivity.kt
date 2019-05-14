package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val TAG = "**Main**"
    companion object {
        val EXTRA_SUBJECT = "edu.washington.alkwan.quizdroid.subject"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mathButton = findViewById<Button>(R.id.mathButton)
        val physicsButton = findViewById<Button>(R.id.physicsButton)
        val marvelButton = findViewById<Button>(R.id.marvelButton)

        mathButton.setOnClickListener {
            Log.v(TAG, "Math button clicked!")
            val intent = Intent(this@MainActivity, AppActivity::class.java)
            intent.putExtra(EXTRA_SUBJECT,"Math")
            startActivity(intent)
        }

        physicsButton.setOnClickListener {
            Log.v(TAG, "Physics button clicked!")
            val intent = Intent(this@MainActivity, AppActivity::class.java)
            intent.putExtra(EXTRA_SUBJECT, "Physics")
            startActivity(intent)
        }

        marvelButton.setOnClickListener {
            Log.v(TAG, "Marvel button clicked!")
            val intent = Intent(this@MainActivity, AppActivity::class.java)
            intent.putExtra(EXTRA_SUBJECT, "Marvel Superheroes")
            startActivity(intent)
        }
    }
}
