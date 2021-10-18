interface IDictionary {
    fun add(x :String): Boolean
    fun find(x :String): Boolean
    fun size(): Int
    companion object{
        const val dict_name = "dict.txt"
    }
}