import kotlin.concurrent.fixedRateTimer
import kotlin.math.sqrt
import kotlin.random.Random

fun prime(p: Int) :Boolean{
    for(i in 2..sqrt(p.toDouble()).toInt()){
        if(p%i == 0){
            return false
        }
    }
    return true
}

fun encode(s: StringBuilder) : StringBuilder {
    val ns:StringBuilder = StringBuilder("")
    for(c in s){
        if(c < 'A' || c > 'z'){
            ns.append(c)
            continue
        }
        if(c == 'A'){
            ns.append('Z')
            continue
        }
        if(c == 'a'){
            ns.append('z')
            continue
        }
        ns.append(c-1)
   }
    return ns
}

fun decode(s: StringBuilder) :StringBuilder{
    val ns:StringBuilder = StringBuilder("")
    for(c in s){
        if(c < 'A' || c > 'z'){
            ns.append(c)
            continue
        }
        if(c == 'Z'){
            ns.append('A')
            continue
        }
        if(c == 'z'){
            ns.append('a')
            continue
        }
        ns.append(c+1)
    }
    return ns;
}

fun messageCoding(msg: StringBuilder, func: (StringBuilder) -> StringBuilder): StringBuilder{
    return func(msg)
}

fun mode(x: Int):Int = x % 2

fun main(args: Array<String>) {
    //1
    val x = 2
    val y = 3
    println("$x + $y = ${x + y}")
    //2
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    for(day in daysOfWeek) {
        print("$day ")
    }
    println()
    daysOfWeek
        .filter{(it.startsWith ("T"))}
        .forEach{print("$it ")}
    println()
    daysOfWeek
        .filter{(it.contains ("e"))}
        .forEach{print("$it ")}
    println()
    daysOfWeek
        .filter{(it.length == 6)}
        .forEach{print("$it ")}
    //3
    println()
    for(num in 2..100){
        if(prime(num)){
            print("$num ")
        }
    }
    //4
    var word:StringBuilder = StringBuilder("When you see a good move, look for a better one.")
    println()
    word = encode(word)
    println(word)
    word = decode(word)
    println(word)
    println()
    word = messageCoding(word, ::encode)
    println(word)
    word = messageCoding(word, ::decode)
    println(word)
    //5
    println()
    val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
    for(num in numbers) {
        if(mode(num) == 0) {
            print("$num ")
        }
    }
    println()
    //6
    println(numbers.map { it * 2})
    println(daysOfWeek.map {it.uppercase()})
    println(daysOfWeek.map {it.first().lowercase()})
    println(daysOfWeek.map {it.length})
    println(daysOfWeek.map {it.length}.average())
    //7
    val dofwm = daysOfWeek.toMutableList()
    dofwm.removeIf {it.contains('n')}
    dofwm.forEach{print("$it ")}
    println()
    for((i, day) in dofwm.withIndex()) {
        println("Item at $i is $day")
    }
    dofwm.sort()
    println(dofwm)
    //8
    val ranarray = IntArray(10) { Random.nextInt(0, 100) }
    ranarray.forEach { println(it) }
    ranarray.sort()
    ranarray.forEach { print("$it ") }
    println()
    if(ranarray.any{mode(it) == 0}){
        println("True")
    }
    else{
        println("False")
    }
    if(ranarray.all{mode(it) == 0}){
        println("True")
    }
    else{
        println("False")
    }
    println()
    var szum = 0
    ranarray.forEach { szum += it }
    println(szum/10.0)

}