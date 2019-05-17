package edu.washington.alkwan.quizdroid


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class AnswerFragment : Fragment() {
    private var listener: AnswerListener? = null

    interface AnswerListener {
        fun next()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_answer, container, false)
        val num = arguments!!.getInt("numQuestion")
        val question = TopicRepository.instance.quizManager.getQuiz(arguments?.getInt("index")!!).questions[num]

        val yourAnswer = rootView.findViewById<TextView>(R.id.yourAnswer)
        val yourString = "Your answer: ${arguments?.getString("yourAnswer")}"
        yourAnswer.text = yourString

        val correctAnswer = rootView.findViewById<TextView>(R.id.correctAnswer)
        val answerString = "Correct answer: ${question.options[question.answer]}"
        correctAnswer.text = answerString

        val numCorrect = rootView.findViewById<TextView>(R.id.numberCorrect)
        val correctString = "${arguments?.getInt("correctAnswers")} out of ${num.plus(1)} correct!"
        numCorrect.text = correctString

        val final = TopicRepository.instance.quizManager.getQuiz(arguments?.getInt("index")!!).totalQuestions
        val nextButton = rootView.findViewById<Button>(R.id.buttonNext)
        Log.v("QuizManager", "AnswerFragment: Total questions: $final")
        if (final == num.plus(1)) {
            val finish = "Finish"
            nextButton.text = finish
        }

        nextButton.setOnClickListener {
            listener?.next()
        }

        // Inflate the layout for this fragment
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AnswerListener) {
            listener = context
        } else {
            throw error(context.toString() + " must implement AnswerListener interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}