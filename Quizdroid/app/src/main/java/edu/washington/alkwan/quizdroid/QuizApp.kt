package edu.washington.alkwan.quizdroid

import android.app.Application

data class Quiz (val question: String, val options: Array<String>, val answer: Int) {}

data class Topic (val title: String, val description: String, val numQuestions: Int, val questions: Array<Quiz>) {}

val mathQuestions = arrayOf<Quiz>(
    Quiz("What is 2 + 2?", arrayOf("0", "4", "2", "20"), 1),
    Quiz("What is 8 * 2?", arrayOf("18", "22", "16", "15"), 2),
    Quiz("What is 782 * 26?", arrayOf("20,102", "40,562", "19,872", "20,332"), 3)
)

val mathSubject = Topic(
    "Math",
    "Math is a subject that involves a lot of numbers. And sometimes letters. You calculate stuff. Yeah!",
    3,
    mathQuestions
)

val physicsQuestions = arrayOf<Quiz>(
    Quiz("Who came up with String Theory?", arrayOf("Albert Einstein", "Thomas Jefferson", "Sir Isaac Newton",
        "Stephen Hawking"), 0),
    Quiz("What is lightning?", arrayOf("Something bright and scary", "An electric current",
        "Mother nature", "Who knows???"), 1),
    Quiz("What does the Large Hadron Collider prove the existence of?", arrayOf("Quarks and antiquarks",
        "Leptons and antileptons", "Gauge bosons", "Higgs boson"), 3)
)

val physicsSubject = Topic(
    "Physics",
    "Physics is all about gravity and forces and movement or whatever. And light I guess? " +
            "I don't know, don't ask me. I never took Physics in college!",
    3,
    physicsQuestions
)

val marvelQuestions = arrayOf<Quiz>(
    Quiz("What is Ms. Marvel's civilian identity?", arrayOf("Carol Danvers", "Doreen Green", "Kamala Khan",
        "Nico Minoru"), 2),
    Quiz("Who is the best Spider-Man?", arrayOf("Peter Parker", "Gwen Stacy", "Peni Parker", "Miles Morales"),
        3),
    Quiz("Which group is Marvel's best teen superhero team?", arrayOf("Runaways", "Young Avengers", "Champions",
        "New Mutants"), 1)
)

val marvelSubject = Topic(
    "Marvel Superheroes",
    "Marvel superheroes are fictional characters that have super powers, fight crime, " +
            "and do other cool stuff. My favorite Marvel superheroes are Ms. Marvel and Miles Morales!",
    3,
    marvelQuestions
)

interface TopicRepository {
    var mathQuiz: Topic
    var physicsQuiz: Topic
    var marvelQuiz: Topic

    fun initData() {
        mathQuiz = mathSubject
        physicsQuiz = physicsSubject
        marvelQuiz = marvelSubject
    }
}

class QuizApp() : Application(), TopicRepository {
    override lateinit var mathQuiz: Topic
    override lateinit var physicsQuiz: Topic
    override lateinit var marvelQuiz: Topic

    fun getQuiz(chosenQuiz: String): Topic {
        var thisQuiz = mathQuiz
        when(chosenQuiz) {
            "Math" -> thisQuiz = mathQuiz
            "Marvel Superheroes" -> thisQuiz = marvelQuiz
            "Physics" -> thisQuiz = physicsQuiz
        }
        return thisQuiz
    }

    override fun onCreate() {
        super.onCreate()
    }
}