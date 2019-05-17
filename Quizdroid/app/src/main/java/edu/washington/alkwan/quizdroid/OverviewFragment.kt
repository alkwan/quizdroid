package edu.washington.alkwan.quizdroid

import android.content.Context
import android.net.Uri
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
class OverviewFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_overview, container, false)

        val index = arguments?.getInt("index")
        val subject = arguments?.getString("subject")
        val quiz = TopicRepository.instance.quizManager.getQuiz(index!!)

        val topicOverview = rootView.findViewById<TextView>(R.id.topicOverview)
        topicOverview.text = "Topic Overview: ${quiz.title}"

        val topicSummary = rootView.findViewById<TextView>(R.id.summary)
        topicSummary.text = quiz.description

        val topicQuestions = rootView.findViewById<TextView>(R.id.numQuestions)
        topicQuestions.text = quiz.totalQuestions.toString()

        val startButton = rootView.findViewById<Button>(R.id.begin)
        startButton.setOnClickListener {
            listener?.onClickBegin(subject, index)
        }
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw error(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onClickBegin(subject: String?, index: Int)
    }
}