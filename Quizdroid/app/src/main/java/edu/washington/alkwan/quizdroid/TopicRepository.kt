package edu.washington.alkwan.quizdroid

import android.app.Application

class TopicRepository: Application() {

    lateinit var quizManager: QuizManager
        private set

    companion object {
        lateinit var instance: TopicRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        quizManager = QuizManager()
    }

}