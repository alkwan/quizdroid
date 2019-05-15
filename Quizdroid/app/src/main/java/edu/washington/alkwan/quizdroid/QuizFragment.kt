package edu.washington.alkwan.quizdroid


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class QuizFragment : Fragment() {
    private var listener: SubmitListener? = null

    interface SubmitListener {
        fun submit(answer: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_quiz, container, false)
        val subject = arguments?.getString("subject")!!
        val question = TopicRepository.instance.quizManager.getQuiz(subject).questions[arguments!!.getInt("numQuestion")]

        val questionContainer = rootView.findViewById<TextView>(R.id.questionName)
        questionContainer.text = question.question

        val options = question.options

        val optionOne = rootView.findViewById<RadioButton>(R.id.answer01)
        val optionTwo = rootView.findViewById<RadioButton>(R.id.answer02)
        val optionThree = rootView.findViewById<RadioButton>(R.id.answer03)
        val optionFour = rootView.findViewById<RadioButton>(R.id.answer04)

        optionOne.text = options[0]
        optionTwo.text = options[1]
        optionThree.text = options[2]
        optionFour.text = options[3]

        val submitButton = rootView.findViewById<Button>(R.id.buttonSubmit)
        val answerGroup = rootView.findViewById<RadioGroup>(R.id.answerGroup)

        optionOne.setOnClickListener {
            submitButton.isEnabled = true
        }
        optionTwo.setOnClickListener {
            submitButton.isEnabled = true
        }
        optionThree.setOnClickListener {

            submitButton.isEnabled = true
        }
        optionFour.setOnClickListener {
            submitButton.isEnabled = true
        }

        submitButton.setOnClickListener {
            if (answerGroup.checkedRadioButtonId != -1) {
                val answer = rootView.findViewById<RadioButton>(answerGroup.checkedRadioButtonId).text.toString()
                listener?.submit(answer)
            }
        }

        // Inflate the layout for this fragment
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is SubmitListener) {
            listener = context
        } else {
            throw error(context.toString() + " must implement SubmitListener interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}