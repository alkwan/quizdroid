package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity;
import android.util.Log

import kotlinx.android.synthetic.main.activity_app.*

class AppActivity : AppCompatActivity(), OverviewFragment.OnFragmentInteractionListener,
      QuizFragment.SubmitListener, AnswerFragment.AnswerListener {

    private val OVERVIEW_FRAGMENT_TAG = "OverviewFragment"
    var numQuestion: Int = 0
    var correctAnswers: Int = 0
    var subject: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        subject = intent.extras!!.getString(MainActivity.EXTRA_SUBJECT)

        val quizOverviewFragment = OverviewFragment()
        quizOverviewFragment.arguments = Bundle().apply {
            putString("subject", subject)
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, quizOverviewFragment, OVERVIEW_FRAGMENT_TAG)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun submit(answer: String?) {
        var question = TopicRepository.instance.quizManager.getQuiz(subject!!).questions[numQuestion]
        if (answer == question.options[question.answer]) {
            correctAnswers += 1
        }

        val quizAnswer = AnswerFragment()
        quizAnswer.arguments = Bundle().apply {
            putString("subject", subject)
            putString("yourAnswer", answer)
            putInt("numQuestion", numQuestion)
            putInt("correctAnswers", correctAnswers)
        }
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, quizAnswer)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun next() {
        if (TopicRepository.instance.quizManager.getQuiz(subject!!).totalQuestions == numQuestion + 1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            numQuestion += 1
            val nextQuestion = QuizFragment()
            nextQuestion.arguments = Bundle().apply {
                putString("subject", subject)
                putInt("numQuestion", numQuestion)
            }
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, nextQuestion)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    override fun onClickBegin(subject: String?) {
        val quizFragment = QuizFragment()
        quizFragment.arguments = Bundle().apply {
            putString("subject", subject)
            putInt("numQuestion", numQuestion)
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, quizFragment)
        ft.addToBackStack(null)
        ft.commit()
    }

}
