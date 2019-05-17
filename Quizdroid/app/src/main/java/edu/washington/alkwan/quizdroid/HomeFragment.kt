package edu.washington.alkwan.quizdroid


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val mathButton = rootView.findViewById<Button>(R.id.mathButton)
        val physicsButton = rootView.findViewById<Button>(R.id.physicsButton)
        val marvelButton = rootView.findViewById<Button>(R.id.marvelButton)

        val subjectOne = TopicRepository.instance.quizManager.getQuiz(0).title
        mathButton.text = subjectOne
        mathButton.setOnClickListener {
            Log.v("QuizManager", "$subjectOne button clicked!")
            val intent = Intent(activity, AppActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_SUBJECT, subjectOne)
            intent.putExtra(MainActivity.EXTRA_INDEX, 0)
            startActivity(intent)
        }

        val subjectTwo = TopicRepository.instance.quizManager.getQuiz(1).title
        physicsButton.text = subjectTwo
        physicsButton.setOnClickListener {
            Log.v("QuizManager", "$subjectTwo button clicked!")
            val intent = Intent(activity, AppActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_SUBJECT, subjectTwo)
            intent.putExtra(MainActivity.EXTRA_INDEX, 1)
            startActivity(intent)
        }

        val subjectThree = TopicRepository.instance.quizManager.getQuiz(2).title
        marvelButton.text = subjectThree
        marvelButton.setOnClickListener {
            Log.v("QuizManager", "$subjectThree button clicked!")
            val intent = Intent(activity, AppActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_SUBJECT, subjectThree)
            intent.putExtra(MainActivity.EXTRA_INDEX, 2)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return rootView
    }


}
