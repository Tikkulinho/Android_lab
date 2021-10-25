import kotlin.random.Random

fun main(args: Array<String>) {
    val quiz = QuizController()
    quiz.readQuestions("quiz.txt")
    quiz.doQuiz(10);
}