package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val TAG = "**Main**"
    companion object {
        val EXTRA_SUBJECT = "edu.washington.alkwan.quizdroid.subject"
        val EXTRA_INDEX = "edu.washington.alkwan.quizdroid.index"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_menu))

        // read the JSON file
        TopicRepository.instance.quizManager.readJson(this)
        TopicRepository.instance.quizManager.readWriteJson(this)

        val mathButton = findViewById<Button>(R.id.mathButton)
        val physicsButton = findViewById<Button>(R.id.physicsButton)
        val marvelButton = findViewById<Button>(R.id.marvelButton)

        val subjectOne = TopicRepository.instance.quizManager.getQuiz(0).title
        mathButton.text = subjectOne
        mathButton.setOnClickListener {
            Log.v("QuizManager", "$subjectOne button clicked!")
            val intent = Intent(this, AppActivity::class.java)
            intent.putExtra(EXTRA_SUBJECT, subjectOne)
            intent.putExtra(EXTRA_INDEX, 0)
            startActivity(intent)
        }

        val subjectTwo = TopicRepository.instance.quizManager.getQuiz(1).title
        physicsButton.text = subjectTwo
        physicsButton.setOnClickListener {
            Log.v("QuizManager", "$subjectTwo button clicked!")
            val intent = Intent(this, AppActivity::class.java)
            intent.putExtra(EXTRA_SUBJECT, subjectTwo)
            intent.putExtra(EXTRA_INDEX, 1)
            startActivity(intent)
        }

        val subjectThree = TopicRepository.instance.quizManager.getQuiz(2).title
        marvelButton.text = subjectThree
        marvelButton.setOnClickListener {
            Log.v("QuizManager", "$subjectThree button clicked!")
            val intent = Intent(this, AppActivity::class.java)
            intent.putExtra(EXTRA_SUBJECT, subjectThree)
            intent.putExtra(EXTRA_INDEX, 2)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            Log.v("QuizManager", "Settings button pressed!")
            startActivity(Intent(this, SettingsActivity::class.java))
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
