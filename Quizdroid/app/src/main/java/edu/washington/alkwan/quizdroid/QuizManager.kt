package edu.washington.alkwan.quizdroid

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class QuizManager {

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var quizzes: Array<Quiz?>

    data class Question (val question: String, val options: Array<String?>, val answer: Int)
    data class Quiz (val title: String, val description: String,
                     val questions: ArrayList<Question>, val totalQuestions: Int)

    companion object {
        val TAG: String = "QuizManager"

        const val JSON_FILE_NAME = "questions.json"
        const val TITLE = "title"
        const val DESC = "desc"
        const val TEXT = "text"
        const val ANSWER = "answer"
        const val ANSWERS = "answers"

        private const val USER_PREF_KEY = "USER_PREFERENCES_KEY"
        private const val TIMESTAMP_KEY = "timestamp"
    }

    fun readWriteJson(context: Context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF_KEY, Context.MODE_PRIVATE)

        // Get current time stamp from SharedPreferences & print it
        val defaultErrorValue = -1L
        val timestamp = sharedPreferences.getLong("timestamp", defaultErrorValue)
        Log.i(TAG, "Current Shared preferences of time stamp = $timestamp")

        // Updated timestamp in SharedPreferences & print it
        sharedPreferences
            .edit()
            .putLong(TIMESTAMP_KEY, System.currentTimeMillis() + 1000)
            .apply()
        Log.i(TAG, "New shared preferences of time stamp = ${sharedPreferences.getLong("timestamp", defaultErrorValue)}")
    }

    fun readJson(context: Context) {
        // Note: to create assets folder to put files in it: https://stackoverflow.com/a/27673773/1991683

        val jsonString: String? = try {
            // grab file from assets folder & read it to a String
            val inputStream = context.assets.open(JSON_FILE_NAME)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            null
        }

        jsonString?.let {

            Log.i(TAG, jsonString)

            // Create json from string
            val jsonObjectArray = JSONArray(jsonString)
            quizzes = arrayOfNulls(jsonObjectArray.length())

            for (i in 0 until jsonObjectArray.length()) {
                val jsonObject = jsonObjectArray.getJSONObject(i)
                val title = jsonObject.get(TITLE) as String
                val desc = jsonObject.get(DESC) as String
                val questionJSONArray = jsonObject.getJSONArray("questions")
                val questionArray: ArrayList<Question> = arrayListOf()
                for (j in 0 until questionJSONArray.length()) {
                    val questionJSONObject = questionJSONArray.get(j) as JSONObject
                    val text = questionJSONObject.get(TEXT)
                    val answer = questionJSONObject.get(ANSWER).toString().toInt()
                    val answersArray = questionJSONObject.getJSONArray(ANSWERS)
                    val answers = arrayOfNulls<String>(answersArray.length())
                    for (k in 0 until answersArray.length()) {
                        answers[k] = answersArray[k].toString()
                    }
                    questionArray.add(Question(text.toString(), answers, answer))
                }
                val quiz = Quiz(title, desc, questionArray, questionArray.size)
                quizzes[i] = quiz
            }
        }
    }

    fun getQuiz(index: Int): Quiz {
        return quizzes[index]!!
    }
}