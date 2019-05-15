package edu.washington.alkwan.quizdroid

class QuizManager {

    data class Question (val question: String, val options: Array<String>, val answer: Int)
    data class Quiz (val title: String, val description: String,
                     val questions: ArrayList<Question>, val totalQuestions: Int)

    val mathQuiz = Quiz ("Math",
        "Math is a subject that involves a lot of numbers. And sometimes letters. You calculate stuff. Yeah!",
        arrayListOf(Question("What is 2 + 2?", arrayOf("0", "4", "2", "20"), 1),
        Question("What is 8 * 2?", arrayOf("18", "22", "16", "15"), 2),
        Question("What is 782 * 26?", arrayOf("20,102", "40,562", "19,872", "20,332"), 3)),
        3)

    val physicsQuiz = Quiz ("Physics",
        "Physics is all about gravity and forces and movement or whatever. And light I guess? " +
                "I don't know, don't ask me. I never took Physics in college!",
        arrayListOf(
        Question("Who came up with String Theory?", arrayOf("Albert Einstein", "Thomas Jefferson", "Sir Isaac Newton",
            "Stephen Hawking"), 0),
        Question("What is lightning?", arrayOf("Something bright and scary", "An electric current",
            "Mother nature", "Who knows???"), 1),
        Question("What does the Large Hadron Collider prove the existence of?", arrayOf("Quarks and antiquarks",
            "Leptons and antileptons", "Gauge bosons", "Higgs boson"), 3)
    ), 3)

    val marvelQuiz = Quiz ("Marvel Superheroes",
        "Marvel superheroes are fictional characters that have superpowers, fight crime, and do " +
                "other cool stuff. My favorite Marvel superheroes are Ms. Marvel and Miles Morales!",
        arrayListOf(
        Question("What is Ms. Marvel's civilian identity?", arrayOf("Carol Danvers", "Doreen Green", "Kamala Khan",
            "Nico Minoru"), 2),
        Question("Who is the best Spider-Man?", arrayOf("Peter Parker", "Gwen Stacy", "Peni Parker", "Miles Morales"),
            3),
        Question("Which group is Marvel's best teen superhero team?", arrayOf("Runaways", "Young Avengers", "Champions",
            "New Mutants"), 1)
    ), 3)


    fun getQuiz(subject: String): Quiz {
        var thisQuiz = mathQuiz
        when(subject) {
            "Math" -> thisQuiz = mathQuiz
            "Marvel Superheroes" -> thisQuiz = marvelQuiz
            "Physics" -> thisQuiz = physicsQuiz
        }
        return thisQuiz
    }

}