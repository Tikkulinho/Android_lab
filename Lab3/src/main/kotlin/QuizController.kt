import java.io.File
import java.util.*
import java.util.Collections.shuffle

class QuizController {
    val questions = mutableListOf<Question>()

    fun readQuestions(filename: String){
        val qs = File(filename).readLines()
        for(line in 0..qs.size-5 step 5){
            val answers = mutableListOf<String>()
            answers.add(qs[line+1])
            answers.add(qs[line+2])
            answers.add(qs[line+3])
            answers.add(qs[line+4])
            questions.add(Question(qs[line],answers,qs[line+1]))
        }
    }
    fun doQuiz(num: Int): Unit{
        randomizeQuestions()
        var points = 0
        for(i in 0 until num){
            println("${i+1}. ${questions[i].text}")
            questions[i].answers.shuffle()
            for(j in 0..3){
                println(questions[i].answers[j])
            }
            println("Write your answer: ")
            val input = Scanner(System.`in`)
            var uans = input.nextLine()
            if(uans == questions[i].rightAns){
                println("Correct")
                points++
            }
            else{
                println("Wrong")
            }
        }
        println("You got $points points out of 10.")
    }
    fun randomizeQuestions(): Unit{
        questions.shuffle()
    }
}