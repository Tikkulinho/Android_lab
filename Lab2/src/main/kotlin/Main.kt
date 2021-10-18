import kotlin.random.Random

fun String.mg() = this.split(' ').map{it.first()}.joinToString("")
fun List<String>.join(separator: String):String = this.joinToString(separator)
fun List<String>.longest() = this.maxOf{it}

fun main(args: Array<String>) {
    //1
    //val dict: IDictionary = ListDictionary
    //val dict: IDictionary = TreeSetDictionary
    val p: DictionaryProvider = DictionaryProvider;
    val dict: IDictionary = p.createDictionary(DictionaryType.HASH_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

    //2
    val n = "John Smith"
    val m = n.mg()
    println("$n monogram is $m")
    val fruits = listOf("apple","pear","strawberry","melon")
    val fruit = fruits.join("#")
    println(fruit)
    print("The longest fruit: ")
    val l= fruits.longest()
    println(l)

    //3
    val valid_dates= mutableListOf<Date>()
    var s: Int = 0
    print("Invalid dates: ")
    while(s != 10){
        val year = Random.nextInt(1600,2021)
        val month = Random.nextInt(15 + 5) - 5
        val day = Random.nextInt(40 + 5 ) - 5
        val date = Date(year,month,day)
        if(date.is_valid()){
            valid_dates.add(date)
        }
        else{
            println(date)
        }
        s++;
    }
    println()
    print("Valid dates are: ")
    valid_dates.forEach{println(it)}
    println()
    print("Natural ordering is: ")
    valid_dates.sort()
    valid_dates.forEach{println(it)}
    println()
    print("Reverse order: ")
    valid_dates.reverse()
    valid_dates.forEach{println(it)}
    println()
    print("Custom ordering: ")
    val compare = {x :Date, y :Date -> compareValues(y,x)}
    valid_dates.sortWith(compare)
    valid_dates.forEach{println(it)}
}