package edu.washington.alkwan.quizdroid


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class AnswerFragment : Fragment() {
    private var answerListener: AnswerListener? = null
    val quizApp: QuizApp = QuizApp()

    interface AnswerListener {
        fun next()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quizApp.initData()
        val rootView = inflater.inflate(R.layout.fragment_answer, container, false)
        val num = arguments!!.getInt("numQuestion")
        val question = quizApp.getQuiz(arguments?.getString("subject")!!).questions[num]


        val yourAnswer = rootView.findViewById<TextView>(R.id.yourAnswer)
        yourAnswer.text = "Your answer: ${arguments?.getString("yourAnswer")}"

        val correctAnswer = rootView.findViewById<TextView>(R.id.correctAnswer)
        correctAnswer.text = "Correct answer: ${question.options[question.answer]}"

        val numCorrect = rootView.findViewById<TextView>(R.id.numberCorrect)
        numCorrect.text = "${arguments?.getInt("correctAnswers")} " +
                "out of ${num.plus(1)} correct!"

        val final = quizApp.getQuiz(arguments?.getString("subject")!!).numQuestions
        val nextButton = rootView.findViewById<Button>(R.id.buttonNext)

        if (final == num.plus(1)) {
            nextButton.text = "Finish"
        }

        nextButton.setOnClickListener {
            answerListener?.next()
        }

        // Inflate the layout for this fragment
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AnswerListener) {
            answerListener = context
        } else {
            throw error(context.toString() + " must implement AnswerListener interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        answerListener = null
    }
}
