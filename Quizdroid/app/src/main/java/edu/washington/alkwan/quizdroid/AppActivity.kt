package edu.washington.alkwan.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log

class AppActivity : AppCompatActivity(), OverviewFragment.OnFragmentInteractionListener,
    QuizFragment.SubmitListener, AnswerFragment.AnswerListener {
    var numQuestion: Int = 0
    var correctAnswers: Int = 0
    var subject: String? = null
    var subjectIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        subject = intent.extras!!.getString(MainActivity.EXTRA_SUBJECT)
        subjectIndex = intent.extras!!.getInt(MainActivity.EXTRA_INDEX)
        val quizOverviewFragment = OverviewFragment()
        quizOverviewFragment.arguments = Bundle().apply {
            putInt("index", subjectIndex)
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, quizOverviewFragment, "OverviewFragment")
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun submit(answer: String?) {
        val question = TopicRepository.instance.quizManager.getQuiz(subjectIndex).questions[numQuestion]

        if (answer == question.options[question.answer]) {
            correctAnswers += 1
        }

        val quizAnswer = AnswerFragment()
        quizAnswer.arguments = Bundle().apply {
            putInt("index", subjectIndex)
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
        val total = TopicRepository.instance.quizManager.getQuiz(subjectIndex).totalQuestions

        if (total == numQuestion + 1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            numQuestion += 1
            val nextQuestion = QuizFragment()
            nextQuestion.arguments = Bundle().apply {
                putString("subject", subject)
                putInt("index", subjectIndex)
                putInt("numQuestion", numQuestion)
            }
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, nextQuestion)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    override fun onClickBegin(subject: String?, index: Int) {
        val quizFragment = QuizFragment()
        quizFragment.arguments = Bundle().apply {
            putString("subject", subject)
            putInt("index", index)
            putInt("numQuestion", numQuestion)
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, quizFragment)
        ft.addToBackStack(null)
        ft.commit()
    }

}